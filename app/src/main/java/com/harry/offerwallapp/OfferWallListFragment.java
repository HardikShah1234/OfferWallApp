package com.harry.offerwallapp;

import static com.harry.offerwallapp.utils.Constant.FIRST_PAGE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.harry.offerwallapp.adapter.RecyclerViewAdapter;
import com.harry.offerwallapp.adapter.RecyclerViewPaginator;
import com.harry.offerwallapp.base.BaseFragment;
import com.harry.offerwallapp.databinding.FragmentOfferWallListBinding;
import com.harry.offerwallapp.model.OfferResponse;
import com.harry.offerwallapp.utils.CheckInternetConnection;
import com.harry.offerwallapp.utils.NetworkState;
import com.harry.offerwallapp.viewModel.OfferWallViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OfferWallListFragment extends BaseFragment<FragmentOfferWallListBinding> {

    private int currentPage = FIRST_PAGE;

    public OfferWallListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle("Offers List");
        initViewModel(getBinding());
    }

    @NonNull
    @Override
    protected FragmentOfferWallListBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentOfferWallListBinding.inflate(inflater, container, false);
    }


    @SuppressLint("SetTextI18n")
    private void initViewModel(FragmentOfferWallListBinding binding) {
        OfferWallViewModel offerWallViewModel = new ViewModelProvider(this).get(OfferWallViewModel.class);
        final boolean[] isLastPage = {false};

        currentPage = 1;
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter();
        binding.rvOfferData.setHasFixedSize(true);
        binding.rvOfferData.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvOfferData.setAdapter(recyclerViewAdapter);
        binding.rvOfferData.setVisibility(View.VISIBLE);

        Bundle args = requireArguments();
        String appId = args.getString("appid");
        String userId = args.getString("uid");
        String token = args.getString("token");

        offerWallViewModel.getLiveData(currentPage, appId, userId, token).observe(getViewLifecycleOwner(), offerResponse -> {
            isLastPage[0] = currentPage == offerResponse.getPages();
            recyclerViewAdapter.addOffers(offerResponse.getOffers());
        });

        RecyclerViewPaginator recyclerViewPaginator = new RecyclerViewPaginator(binding.rvOfferData) {
            @Override
            public boolean isLastPage() {
                return isLastPage[0];
            }

            @Override
            public void loadMore(int _currentPage) {
                currentPage = _currentPage;
                offerWallViewModel.loadData(Math.toIntExact(_currentPage), appId, userId, token);
            }
        };
        binding.rvOfferData.addOnScrollListener(recyclerViewPaginator);




        offerWallViewModel.getNetworkStateMutableLiveData().observe(getViewLifecycleOwner(), networkState -> {
            if (networkState == NetworkState.Companion.getLOADING()) {
                binding.pbLoadingItems.setVisibility(View.VISIBLE);
            } else if (networkState == NetworkState.Companion.getERROR()) {

                Toast.makeText(getContext(), "Something went wrong, please check the parameters are filled perfectly", Toast.LENGTH_SHORT).show();
                if (!CheckInternetConnection.isInternetAvailable(requireContext())) {
                    Toast.makeText(getContext(), "No Internet Connection Available", Toast.LENGTH_SHORT).show();
                    binding.txtNoConnection.setText("No Internet Connection Available");
                    binding.txtNoConnection.setVisibility(View.VISIBLE);
                }

                binding.pbLoadingItems.setVisibility(View.GONE);
            } else {
                binding.pbLoadingItems.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        requireActivity().getViewModelStore().clear();
        super.onDestroyView();
    }
}