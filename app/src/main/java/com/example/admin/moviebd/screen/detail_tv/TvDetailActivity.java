package com.example.admin.moviebd.screen.detail_tv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.FeaturedCrew;
import com.example.admin.moviebd.data.model.Genres;
import com.example.admin.moviebd.data.model.Recommendation;
import com.example.admin.moviebd.data.model.Season;
import com.example.admin.moviebd.data.model.TV;
import com.example.admin.moviebd.data.model.Trailer;
import com.example.admin.moviebd.screen.detail_movie.MovieDetailsAtivity;
import com.example.admin.moviebd.screen.detail_movie.TrailerFragment;
import com.example.admin.moviebd.screen.trailer.TrailerActivity;
import com.example.admin.moviebd.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TvDetailActivity extends AppCompatActivity implements TvDetailContract.View,
        TrailerAdapter.ItemTrailerClick, RecommendationAdapter.ItemClickTvRecommendation {
    private int mIdTv;
    private LinearLayout mLayoutLoading;
    private ImageView mImageVideo, mImageBackground;
    private TextView mTextNameVideo, mTextDateVideo,
            mTextVoteVideo, mTextGenresVideo, mTextOverViewVideo;
    private RecyclerView mRecyclerCreated,
            mRecyclerSeason, mRecyclerRecommendations, mRecyclerTrailer;
    private DetailTvPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        init();
        setLayoutRecycler();
        mIdTv = getIntent().getIntExtra(Constant.BaseApiUrl.ID_TV, 1418);
        mPresenter = new DetailTvPresenter(this,
                Injection.getInstance(this).getTvDetailRepository());
    }

    @Override
    protected void onStart() {
        super.onStart();
        getTvDetail();
    }

    private void getTvDetail() {
        mPresenter.getTvInformationFromApi(String.format(Constant.FINAL_API_TV_DETAIL, mIdTv));
        mPresenter.getGenresFromApi(String.format(Constant.FINAL_API_TV_DETAIL, mIdTv));
        mPresenter.getFeaturedCrewFromApi(String.format(Constant.FINAL_API_TV_DETAIL, mIdTv));
        mPresenter.getSeasonFromApi(String.format(Constant.FINAL_API_TV_DETAIL, mIdTv));
        mPresenter.getTrailerFromApi(String.format(Constant.FINAL_API_TV_VIDEO, mIdTv));
        mPresenter.getRecommendationFromApi(String.format(Constant.FINAL_API_TV_RECOMMENTDATIONS, mIdTv));
    }

    private void init() {
        mLayoutLoading = (LinearLayout) findViewById(R.id.dialog_loading);
        mTextNameVideo = (TextView) findViewById(R.id.text_tv_name);
        mTextDateVideo = (TextView) findViewById(R.id.text_date);
        mTextVoteVideo = (TextView) findViewById(R.id.text_vote);
        mTextGenresVideo = (TextView) findViewById(R.id.text_genres);
        mTextOverViewVideo = (TextView) findViewById(R.id.text_overview);
        mImageVideo = (ImageView) findViewById(R.id.image_video);
        mImageBackground = (ImageView) findViewById(R.id.image_background);
        mRecyclerCreated = (RecyclerView) findViewById(R.id.recycler_created);
        mRecyclerSeason = (RecyclerView) findViewById(R.id.recycler_season);
        mRecyclerRecommendations = (RecyclerView) findViewById(R.id.recycler_recommendations);
        mRecyclerTrailer = (RecyclerView) findViewById(R.id.recycler_trailer);
    }

    private void setLayoutRecycler() {
        mRecyclerSeason.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerCreated.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerRecommendations.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerTrailer.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onStartLoading() {
        mLayoutLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessTvInfor(TV tv) {
        mTextNameVideo.setText(tv.getName());
        mTextDateVideo.setText(tv.getDate());
        mTextVoteVideo.setText(String.valueOf(tv.getVote()));
        mTextOverViewVideo.setText(tv.getOverview());
        Picasso.get()
                .load(String.format(Constant.BaseApiUrl.IMAGE_URL, tv.getPathImage()))
                .placeholder(R.drawable.image_no_image)
                .error(R.drawable.image_error)
                .into(mImageVideo);
        Picasso.get()
                .load(String.format(Constant.BaseApiUrl.IMAGE_URL, tv.getPathImage()))
                .placeholder(R.drawable.image_no_image)
                .error(R.drawable.image_error)
                .resize(mImageBackground.getWidth(), mImageBackground.getHeight())
                .into(mImageBackground);
    }

    @Override
    public void onSuccessGenres(List<Genres> genres) {
        String nameGenres = "";
        for (int i = 0; i < genres.size(); i++) {
            nameGenres = nameGenres + genres.get(i).getName() + "\n";
        }
        mTextGenresVideo.setText(nameGenres);
    }

    @Override
    public void onSuccessFeaturedCrew(List<FeaturedCrew> crews) {
        mRecyclerCreated.setAdapter(new FeaturedCrewAdapter(crews, this));
    }

    @Override
    public void onSuccessSeason(List<Season> seasons) {
        mRecyclerSeason.setAdapter(new SeasonAdapter(seasons, this));
    }

    @Override
    public void onSuccessTrailer(List<Trailer> trailers) {
        mRecyclerTrailer.setAdapter(new TrailerAdapter(trailers, this, this));
    }

    @Override
    public void onSuccessRecommendation(List<Recommendation> recommendations) {
        mRecyclerRecommendations.setAdapter(new RecommendationAdapter(recommendations, this, this));
    }

    @Override
    public void onFailed(Exception e) {
        getTvDetail();
    }

    @Override
    public void onDismissLoading() {
        mLayoutLoading.setVisibility(View.GONE);
    }

    @Override
    public void onClickTrailer(String idTrailer) {
        TrailerFragment.getTrailerIntent(this, idTrailer);
    }

    @Override
    public void onItemClick(int idTv) {
        getTvDetailIntent(this, idTv);
    }

    public static Intent getTvDetailIntent(Context context, int idTv) {
        Intent intent = new Intent(context, TvDetailActivity.class);
        intent.putExtra(Constant.BaseApiUrl.ID_TV, idTv);
        return intent;
    }
}
