package com.harry.offerwallapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.harry.offerwallapp.base.BaseFragment;
import com.harry.offerwallapp.databinding.FragmentUserInputFormBinding;
import com.harry.offerwallapp.viewModel.OfferWallViewModel;

import java.util.Map;
import java.util.Objects;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserInputFormFragment extends BaseFragment<FragmentUserInputFormBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected FragmentUserInputFormBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentUserInputFormBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle("Input Form");
        bind(FragmentUserInputFormBinding.bind(view));
    }

    private void bind(FragmentUserInputFormBinding binding) {
        binding.btnFetchOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("appid", binding.etAppId.getText().toString());
                bundle.putString("uid",binding.etUserId.getText().toString());
                bundle.putString("token", binding.etToken.getText().toString());
                OfferWallListFragment offerWallListFragment = new OfferWallListFragment();
                offerWallListFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup) requireView().getParent()).getId(), offerWallListFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_input_form, container, false);
    }
}