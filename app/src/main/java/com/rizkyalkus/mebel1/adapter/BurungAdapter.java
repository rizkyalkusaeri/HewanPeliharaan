package com.rizkyalkus.mebel1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rizkyalkus.mebel1.R;
import com.rizkyalkus.mebel1.model.Burung;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BurungAdapter extends RecyclerView.Adapter<BurungAdapter.BurungViewHolder> {

    private List<Burung> mBurung = new ArrayList<>();
    private Context mContext;

    public BurungAdapter(List<Burung> mBurung, Context mContext) {
        this.mBurung = mBurung;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BurungViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        return new BurungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BurungViewHolder holder, int position) {
        holder.mName.setText(mBurung.get(position).getNama());

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mBurung.get(position).getImageUrl())
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mBurung.size();
    }


    public static class BurungViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mImage;
        private TextView mName;

        public BurungViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }
}
