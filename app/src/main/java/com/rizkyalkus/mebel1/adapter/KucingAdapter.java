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
import com.rizkyalkus.mebel1.model.Ikan;
import com.rizkyalkus.mebel1.model.Kucing;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class KucingAdapter extends RecyclerView.Adapter<KucingAdapter.KucingViewHolder> {

    private List<Kucing> mKucing = new ArrayList<>();
    private Context mContext;

    public KucingAdapter(List<Kucing> mKucing, Context mContext) {
        this.mKucing = mKucing;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public KucingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        return new KucingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KucingViewHolder holder, int position) {
        holder.mName.setText(mKucing.get(position).getNama());

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mKucing.get(position).getImageUrl())
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mKucing.size();
    }


    public static class KucingViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mImage;
        private TextView mName;

        public KucingViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }
}
