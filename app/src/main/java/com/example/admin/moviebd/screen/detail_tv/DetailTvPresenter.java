package com.example.admin.moviebd.screen.detail_tv;

import com.example.admin.moviebd.data.model.FeaturedCrew;
import com.example.admin.moviebd.data.model.Genres;
import com.example.admin.moviebd.data.model.Recommendation;
import com.example.admin.moviebd.data.model.Season;
import com.example.admin.moviebd.data.model.TV;
import com.example.admin.moviebd.data.model.Trailer;
import com.example.admin.moviebd.data.source.TVDetailDataSource;
import com.example.admin.moviebd.data.source.repository.TvDetailRepository;

import java.util.List;

public class DetailTvPresenter implements TvDetailContract.Presenter {
    private TvDetailContract.View mView;
    private TvDetailRepository mRepository;

    public DetailTvPresenter(TvDetailContract.View view, TvDetailRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void getTvInformationFromApi(String url) {
        mRepository.getTvInforFromApi(url, new TVDetailDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccessTvInfor((TV) data);
            }

            @Override
            public void onGetFailure(Exception exeption) {
                mView.onFailed(exeption);
            }

            @Override
            public void onComplete() {
                mView.onDismissLoading();
            }
        });
    }

    @Override
    public void getGenresFromApi(String url) {
        mRepository.getGenresFromApi(url, new TVDetailDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccessGenres((List<Genres>) data);
            }

            @Override
            public void onGetFailure(Exception exeption) {
                mView.onFailed(exeption);
            }

            @Override
            public void onComplete() {
                mView.onDismissLoading();
            }
        });
    }

    @Override
    public void getFeaturedCrewFromApi(String url) {
        mRepository.getFeaturedCrewFromApi(url, new TVDetailDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccessFeaturedCrew((List<FeaturedCrew>) data);
            }

            @Override
            public void onGetFailure(Exception exeption) {
                mView.onFailed(exeption);
            }

            @Override
            public void onComplete() {
                mView.onDismissLoading();
            }
        });
    }

    @Override
    public void getSeasonFromApi(String url) {
        mRepository.getSeasonFromApi(url, new TVDetailDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccessSeason((List<Season>) data);
            }

            @Override
            public void onGetFailure(Exception exeption) {
                mView.onFailed(exeption);
            }

            @Override
            public void onComplete() {
                mView.onDismissLoading();
            }
        });
    }

    @Override
    public void getTrailerFromApi(String url) {
        mRepository.getTrailerFromApi(url, new TVDetailDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccessTrailer((List<Trailer>) data);
            }

            @Override
            public void onGetFailure(Exception exeption) {
                mView.onFailed(exeption);
            }

            @Override
            public void onComplete() {
                mView.onDismissLoading();
            }
        });
    }

    @Override
    public void getRecommendationFromApi(String url) {
        mRepository.getRecommendationFromApi(url, new TVDetailDataSource.Callback() {
            @Override
            public void onStartLoading() {
                mView.onStartLoading();
            }

            @Override
            public void onGetSuccess(Object data) {
                mView.onSuccessRecommendation((List<Recommendation>) data);
            }

            @Override
            public void onGetFailure(Exception exeption) {
                mView.onFailed(exeption);
            }

            @Override
            public void onComplete() {
                mView.onDismissLoading();
            }
        });
    }
}
