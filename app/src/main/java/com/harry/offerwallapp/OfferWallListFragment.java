package com.harry.offerwallapp;

import static com.harry.offerwallapp.utils.Constant.FIRST_PAGE;
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

    private RecyclerViewAdapter recyclerViewAdapter;
    private int currentPage = FIRST_PAGE;

    public OfferWallListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle("Offers List");
        initViewModel(FragmentOfferWallListBinding.bind(view));
    }

    @NonNull
    @Override
    protected FragmentOfferWallListBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentOfferWallListBinding.inflate(inflater, container, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offer_wall_list, container, false);
    }

    private void initViewModel(FragmentOfferWallListBinding binding) {
        OfferWallViewModel offerWallViewModel = new ViewModelProvider(this).get(OfferWallViewModel.class);
        offerWallViewModel.getLiveData(currentPage).observe(getViewLifecycleOwner(), new Observer<OfferResponse>() {
            @Override
            public void onChanged(OfferResponse offerResponse) {
                if (offerResponse != null) {
                    recyclerViewAdapter = new RecyclerViewAdapter();
                    recyclerViewAdapter.setOfferResponse(offerResponse);
                    binding.rvOfferData.setHasFixedSize(true);
                    binding.rvOfferData.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.rvOfferData.setAdapter(recyclerViewAdapter);
                    binding.rvOfferData.setVisibility(View.VISIBLE);
                }

                RecyclerViewPaginator recyclerViewPaginator = new RecyclerViewPaginator(binding.rvOfferData) {
                    @Override
                    public boolean isLastPage() {
                        return currentPage == offerResponse.getPages();
                    }
                    @Override
                    public void loadMore(int _currentPage) {
                        currentPage = _currentPage;
                        offerWallViewModel.getLiveData(Math.toIntExact(_currentPage));
                    }
                };
                binding.rvOfferData.addOnScrollListener(recyclerViewPaginator);
            }
        });

        offerWallViewModel.getNetworkStateMutableLiveData().observe(getViewLifecycleOwner(), new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                if (networkState == NetworkState.Companion.getLOADING()) {
                    binding.pbLoadingItems.setVisibility(View.VISIBLE);
                } else if (networkState == NetworkState.Companion.getERROR()) {

                    Toast.makeText(getContext(), "Something went wrong, please check the parameters are filled perfectly", Toast.LENGTH_SHORT).show();
                    if (!CheckInternetConnection.isInternetAvailable(getContext())) {
                        Toast.makeText(getContext(), "No Internet Connection Available", Toast.LENGTH_SHORT).show();
                        binding.txtNoConnection.setText("No Internet Connection Available");
                        binding.txtNoConnection.setVisibility(View.VISIBLE);
                    }

                    binding.pbLoadingItems.setVisibility(View.GONE);
                } else {
                    binding.pbLoadingItems.setVisibility(View.GONE);
                }
            }
        });
    }
}