package com.harry.offerwallapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.harry.offerwallapp.databinding.ItemOfferListBinding;
import com.harry.offerwallapp.model.OfferResponse;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private OfferResponse offerResponse;

    public void setOfferResponse(OfferResponse offerResponse) {
        this.offerResponse = offerResponse;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOfferListBinding binding = ItemOfferListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.CustomViewHolder holder, int position) {
        holder.binding.txtOfferTitle.setText(
                offerResponse.
                        getOffers().get(position).getTitle());
        Glide.with(holder.binding.cvIvImagePoster)
                .load(offerResponse.getOffers().
                        get(position).getThumbnail().getHires())
                .into(holder.binding.cvIvImagePoster);
    }

    @Override
    public int getItemCount() {
        if (offerResponse == null) {
            return 0;
        } else {
            return offerResponse.getCount();
        }
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        private final ItemOfferListBinding binding;

        public CustomViewHolder(@NonNull ItemOfferListBinding binding) {
            super((View) binding.getRoot());
            this.binding = binding;
        }
    }
}
