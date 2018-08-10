package com.example.admin.moviebd.screen.detail_tv;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.MovieRecommendation;
import com.example.admin.moviebd.data.model.Recommendation;
import com.example.admin.moviebd.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ViewHolder> {
    private List<Recommendation> mRecommendations;
    private ItemClickTvRecommendation mItemClickTvRecommendation;
    private Context mContext;

    public RecommendationAdapter(List<Recommendation> mRecommendations,
                                 Context context, ItemClickTvRecommendation clickTvRecommendation) {
        this.mRecommendations = mRecommendations;
        this.mContext = context;
        this.mItemClickTvRecommendation = clickTvRecommendation;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommendation, parent,
                false);
        return new ViewHolder(view, mRecommendations, mItemClickTvRecommendation);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recommendation mRecommendation = mRecommendations.get(position);
        holder.bindData(mRecommendation);

//        //Khi click vao item trên RecycleView
//        holder.setItemClickListener(new ItemRecommendationClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                Intent intent = new Intent(mContext, TvDetailActivity.class);
//                intent.putExtra(Constant.BaseApiUrl.ID_TV, mRecommendations.get(position).getId());
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mRecommendations != null ? mRecommendations.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImage;
        private TextView mTextName, mTextDate, mTextVote;
        private List<Recommendation> mRecommendations;
        private ItemClickTvRecommendation mItemClickTvRecommendation;


        ViewHolder(View itemView, List<Recommendation> recommendations, ItemClickTvRecommendation itemClickListener) {
            super(itemView);
            this.mRecommendations = recommendations;
            this.mItemClickTvRecommendation = itemClickListener;
            mImage = itemView.findViewById(R.id.image_recommendation);
            mTextDate = itemView.findViewById(R.id.text_date);
            mTextVote = itemView.findViewById(R.id.text_vote);
            mTextName = itemView.findViewById(R.id.text_recommendations_name);

            itemView.setOnClickListener(this);
        }

        public void bindData(Recommendation mRecommendation) {
            mTextName.setText(mRecommendation.getName());
            mTextDate.setText(mRecommendation.getDate());
            mTextVote.setText(String.valueOf(mRecommendation.getVote()));
            Picasso.get().load(String.format(Constant.BaseApiUrl.IMAGE_URL, mRecommendation.getPathImage()))
                    .placeholder(R.drawable.image_no_image)
                    .error(R.drawable.image_error)
                    .into(mImage);
        }

        @Override
        public void onClick(View view) {
            mItemClickTvRecommendation.onItemClick(mRecommendations.get(getLayoutPosition()).getId());
        }
    }

    public interface ItemClickTvRecommendation {
        void onItemClick(int idTv);
    }
}
