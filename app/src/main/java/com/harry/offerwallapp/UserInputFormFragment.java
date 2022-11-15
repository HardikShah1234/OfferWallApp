package com.harry.offerwallapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
        bind(getBinding());
    }

    private void bind(FragmentUserInputFormBinding binding) {

        binding.btnFetchOffer.setOnClickListener(view -> {
            isAllFieldChecked = validateAllFields(binding);
            if (isAllFieldChecked) {

                NavController navController = Navigation.findNavController(view);
                Bundle bundle = new Bundle();
                bundle.putString("appid", binding.etAppId.getText().toString());
                bundle.putString("uid", binding.etUserId.getText().toString());
                bundle.putString("token", binding.etToken.getText().toString());
                navController.navigate(R.id.action_userInputFormFragment_to_offerWallListFragment, bundle);
            }
        });
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
}