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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IkanAdapter extends RecyclerView.Adapter<IkanAdapter.IkanViewHolder> {

    private List<Ikan> mIkan = new ArrayList<>();
    private Context mContext;

    public IkanAdapter(List<Ikan> mIkan, Context mContext) {
        this.mIkan = mIkan;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public IkanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        return new IkanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IkanViewHolder holder, int position) {
        holder.mName.setText(mIkan.get(position).getNama());

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mIkan.get(position).getImageUrl())
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mIkan.size();
    }


    public static class IkanViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mImage;
        private TextView mName;

        public IkanViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }
}
