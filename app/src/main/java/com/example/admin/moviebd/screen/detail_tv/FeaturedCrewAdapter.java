package com.example.admin.moviebd.screen.detail_tv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.FeaturedCrew;
import com.example.admin.moviebd.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FeaturedCrewAdapter extends RecyclerView.Adapter<FeaturedCrewAdapter.ViewHolder> {
    private List<FeaturedCrew> mCreateds;
    private Context mContext;

    public FeaturedCrewAdapter(List<FeaturedCrew> mCreateds, Context mContext) {
        this.mCreateds = mCreateds;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeaturedCrew mCreated = mCreateds.get(position);
        holder.bindData(mCreated);
    }

    @Override
    public int getItemCount() {
        return mCreateds != null ? mCreateds.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageCreated;
        private TextView mTextNameCreated;

        ViewHolder(View itemView) {
            super(itemView);
            mImageCreated = itemView.findViewById(R.id.image_movie);
            mTextNameCreated = itemView.findViewById(R.id.text_title);
        }

        public void bindData(FeaturedCrew mCreated) {
            mTextNameCreated.setText(mCreated.getName());
            Picasso.get().load(String.format(Constant.BaseApiUrl.IMAGE_URL, mCreated.getPathImage()))
                    .placeholder(R.drawable.image_no_image)
                    .error(R.drawable.image_error)
                    .into(mImageCreated);
        }
    }
}
