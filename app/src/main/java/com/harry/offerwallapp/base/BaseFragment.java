package com.harry.offerwallapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BaseFragment<B extends ViewBinding> extends Fragment {
    private B _viewBinding;

    @NotNull
    protected final B getBinding() {
        return this._viewBinding;
    }

    @NotNull
    protected abstract B initBinding(@NotNull LayoutInflater inflater, @Nullable ViewGroup container);

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this._viewBinding = this.initBinding(inflater, container);
        return this.getBinding().getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this._viewBinding = (B) null;
    }
}
