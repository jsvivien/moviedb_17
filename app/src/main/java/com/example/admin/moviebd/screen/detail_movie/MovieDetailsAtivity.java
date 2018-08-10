package com.example.admin.moviebd.screen.detail_movie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Genres;
import com.example.admin.moviebd.data.model.MovieInformation;
import com.example.admin.moviebd.data.model.MovieRecommendation;
import com.example.admin.moviebd.data.model.ProductionCompany;
import com.example.admin.moviebd.data.model.Trailer;
import com.example.admin.moviebd.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsAtivity extends AppCompatActivity
        implements MovieDetailContract.View, MovieRecommendationAdapter.ItemClickMovieRecommendation {
    private static final int SPANCOUNT = 2;
    private Toolbar toolbar;
    private int mIdMovie;
    private LinearLayout mLayoutLoading;
    private ImageView mImageVideo, mImageBackground;
    private TextView mTextNameVideo, mTextDateVideo, mTextGenres,
            mTextVoteVideo;
    private RecyclerView mRecyclerRecommendations;
    private MovieDetailPresenter mDetailPresenter;
    private String mOverView;
    private List<Trailer> mTrailers;
    private List<ProductionCompany> mCompanies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details_ativity);
        mIdMovie = getIntent().getIntExtra(Constant.BaseApiUrl.ID_MOVIE, 353081);
        mDetailPresenter = new MovieDetailPresenter(this,
                Injection.getInstance(this).getMovieDetailRepository());
        init();
        setTab();
        getMovieDetail();
    }

    private void setTab() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_overview:
                    setFragment(new OverViewFragment(mOverView));
                    return true;
                case R.id.navigation_trailer:
                    setFragment(new TrailerFragment(mTrailers));
                    return true;
                case R.id.navigation_company:
                    setFragment(new CompanyFragment(mCompanies));
                    return true;
            }
            return false;
        }
    };

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_tab, fragment);
        fragmentTransaction.commit();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mLayoutLoading = (LinearLayout) findViewById(R.id.dialog_loading);
        mTextGenres = (TextView) findViewById(R.id.text_genres);
        mLayoutLoading = (LinearLayout) findViewById(R.id.dialog_loading);
        mTextNameVideo = (TextView) findViewById(R.id.text_movie_name);
        mTextDateVideo = (TextView) findViewById(R.id.text_date);
        mTextVoteVideo = (TextView) findViewById(R.id.text_vote);
        mImageVideo = (ImageView) findViewById(R.id.image_movie);
        mImageBackground = (ImageView) findViewById(R.id.image_background);
        mRecyclerRecommendations = (RecyclerView) findViewById(R.id.recycler_recommendations);
    }

    private void getMovieDetail() {
        mDetailPresenter.getMovieInformationFromApi(String.format(Constant.FINAL_API_MOVIE_DETAIL, mIdMovie));
        mDetailPresenter.getGenresFromApi(String.format(Constant.FINAL_API_MOVIE_DETAIL, mIdMovie));
        mDetailPresenter.getCompanyFromApi(String.format(Constant.FINAL_API_MOVIE_DETAIL, mIdMovie));
        mDetailPresenter.getTrailerFromApi(String.format(Constant.FINAL_API_MOVIE_VIDEO, mIdMovie));
        mDetailPresenter.getRecommendationFromApi(String.format(Constant.FINAL_API_MOVIE_RECOMMENTDATIONS, mIdMovie));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_details_ativity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStartLoading() {
        mLayoutLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessMovieInfor(MovieInformation movie) {
        mTextNameVideo.setText(movie.getTitle());
        mTextDateVideo.setText(movie.getDate());
        mOverView = movie.getOverview();
        mTextVoteVideo.setText(String.valueOf(movie.getVote()));
        Picasso.get()
                .load(String.format(Constant.BaseApiUrl.IMAGE_URL, movie.getPathImage()))
                .placeholder(R.drawable.image_no_image)
                .error(R.drawable.image_error)
                .into(mImageVideo);
        Picasso.get()
                .load(String.format(Constant.BaseApiUrl.IMAGE_URL, movie.getPathImage()))
                .placeholder(R.drawable.image_no_image)
                .error(R.drawable.image_error)
                .resize(mImageBackground.getWidth(), mImageBackground.getHeight())
                .into(mImageBackground);
        setFragment(new OverViewFragment(mOverView));
    }

    @Override
    public void onSuccessGenres(List<Genres> genres) {
        String nameGenres = "";
        for (int i = 0; i < genres.size(); i++) {
            nameGenres = nameGenres + genres.get(i).getName() + "\n";
        }
        mTextGenres.setText(nameGenres);
    }

    @Override
    public void onSuccessCompany(List<ProductionCompany> companies) {
        mCompanies = new ArrayList<>(companies);
    }

    @Override
    public void onSuccessTrailer(List<Trailer> trailers) {
        mTrailers = new ArrayList<>(trailers);
    }

    @Override
    public void onSuccessRecommendation(List<MovieRecommendation> recommendations) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), SPANCOUNT);
        mRecyclerRecommendations.setLayoutManager(layoutManager);
        mRecyclerRecommendations.setAdapter(new MovieRecommendationAdapter
                (recommendations, this, this));
    }

    @Override
    public void onFailed(Exception e) {
        getMovieDetail();
    }

    @Override
    public void onDismissLoading() {
        mLayoutLoading.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int idMovie) {
        getMoviDetailIntent(this, idMovie);
    }

    public static Intent getMoviDetailIntent(Context context, int idMovie) {
        Intent intent = new Intent(context, MovieDetailsAtivity.class);
        intent.putExtra(Constant.BaseApiUrl.ID_MOVIE, idMovie);
        return intent;
    }
}
