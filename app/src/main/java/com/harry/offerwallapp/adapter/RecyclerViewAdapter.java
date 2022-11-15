package com.harry.offerwallapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.harry.offerwallapp.databinding.ItemOfferListBinding;
import com.harry.offerwallapp.model.Offer;
import com.harry.offerwallapp.model.OfferResponse;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private final ArrayList<Offer> list = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void addOffers(List<Offer> offers) {
        list.addAll(offers);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOfferListBinding binding = ItemOfferListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.CustomViewHolder holder, int position) {
        Offer offer = list.get(position);
        holder.binding.txtOfferTitle.setText(offer.getTitle());
        Glide.with(holder.binding.cvIvImagePoster).load(offer.getThumbnail().getHires()).into(holder.binding.cvIvImagePoster);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        private final ItemOfferListBinding binding;

        public CustomViewHolder(@NonNull ItemOfferListBinding binding) {
            super((View) binding.getRoot());
            this.binding = binding;
        }
    }
}
