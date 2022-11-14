package com.harry.offerwallapp.viewModel;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class OfferViewModelFactory implements ViewModelProvider.Factory {
    private int page;
    SavedStateHandle savedStateHandle;

    public OfferViewModelFactory(int page, SavedStateHandle savedStateHandle) {
        this.page = page;
        this.savedStateHandle = savedStateHandle;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new OfferWallViewModel(savedStateHandle);
    }
}
