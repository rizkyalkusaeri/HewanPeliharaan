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
import com.rizkyalkus.mebel1.model.Anjing;
import com.rizkyalkus.mebel1.model.Ikan;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnjingAdapter extends RecyclerView.Adapter<AnjingAdapter.AnjingViewHolder> {

    private List<Anjing> mAnjing = new ArrayList<>();
    private Context mContext;

    public AnjingAdapter(List<Anjing> mAnjing, Context mContext) {
        this.mAnjing = mAnjing;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AnjingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        return new AnjingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnjingViewHolder holder, int position) {
        holder.mName.setText(mAnjing.get(position).getNama());

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mAnjing.get(position).getImageUrl())
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mAnjing.size();
    }


    public static class AnjingViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mImage;
        private TextView mName;

        public AnjingViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }
}
