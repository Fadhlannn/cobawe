package com.example.sabkasbfa.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sabkasbfa.R;
import com.example.sabkasbfa.ui.models.PopularModels;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private Context context;
    private List<PopularModels> popularModelsList;

    public PopularAdapter(Context context, List<PopularModels> popularModelsList) {
        this.context = context;
        this.popularModelsList = popularModelsList;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        String imageUrl = popularModelsList.get(position).getImg_url();
        Log.d("ImageURL", "URL Image: " + imageUrl);
        Glide.with(context).load(popularModelsList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(popularModelsList.get(position).getName());
        holder.description.setText(popularModelsList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return popularModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_image);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_desc);
        }
    }
}
