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
import com.example.admin.moviebd.data.model.Season;
import com.example.admin.moviebd.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.ViewHolder> {
    private List<Season> mSeasons;
    private Context mContext;

    public SeasonAdapter(List<Season> mSeasons, Context mContext) {
        this.mSeasons = mSeasons;
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
        Season season = mSeasons.get(position);
        holder.bindData(season);
    }

    @Override
    public int getItemCount() {
        return mSeasons != null ? mSeasons.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageMovie;
        private TextView mTextTitleMovie;

        ViewHolder(View itemView) {
            super(itemView);
            mImageMovie = itemView.findViewById(R.id.image_movie);
            mTextTitleMovie = itemView.findViewById(R.id.text_title);
        }

        public void bindData(Season season) {
            mTextTitleMovie.setText(season.getName());
            Picasso.get().load(String.format(Constant.BaseApiUrl.IMAGE_URL, season.getPathImage()))
                    .placeholder(R.drawable.image_no_image)
                    .error(R.drawable.image_error)
                    .into(mImageMovie);
        }
    }
}
