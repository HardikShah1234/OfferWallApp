package com.harry.offerwallapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.harry.offerwallapp.base.BaseFragment;
import com.harry.offerwallapp.databinding.FragmentUserInputFormBinding;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserInputFormFragment extends BaseFragment<FragmentUserInputFormBinding> {

    boolean isAllFieldChecked = false;

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
                isAllFieldChecked = validateAllFields(binding);
                if (isAllFieldChecked) {
                    Bundle bundle = new Bundle();
                    bundle.putString("appid", binding.etAppId.getText().toString());
                    bundle.putString("uid", binding.etUserId.getText().toString());
                    bundle.putString("token", binding.etToken.getText().toString());
                    OfferWallListFragment offerWallListFragment = new OfferWallListFragment();
                    offerWallListFragment.setArguments(bundle);
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(((ViewGroup) requireView().getParent()).getId(), offerWallListFragment,"offerListFragment")
                            .addToBackStack("offerListFragment")
                            .commit();
                }
            }
        });
        binding.etAppId.setText("1246");
        binding.etUserId.setText("superman");
        binding.etToken.setText("82085b8b7b31b3e80beefdc0430e2315f67cd3e1");
    }

    private boolean validateAllFields(FragmentUserInputFormBinding binding) {
        if (binding.etAppId.length() == 0 && !binding.etAppId.getText().toString().trim().equals("")) {
            binding.etUserId.setError(String.valueOf(R.string.required_string_error));
            return false;
        }
        if (binding.etToken.length() == 0 && !binding.etToken.getText().toString().trim().equals("")) {
            binding.etToken.setError(String.valueOf(R.string.required_string_error));
            return false;
        }

        if (binding.etUserId.length() == 0 && !binding.etUserId.getText().toString().trim().equals("")) {
            binding.etUserId.setError(String.valueOf(R.string.required_string_error));
            return false;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_input_form, container, false);
    }
}