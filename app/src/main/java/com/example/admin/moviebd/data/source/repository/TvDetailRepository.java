package com.example.admin.moviebd.data.source.repository;

import com.example.admin.moviebd.data.source.BaseDataSource;
import com.example.admin.moviebd.data.source.remote.MovieInforRemoteDataSource;
import com.example.admin.moviebd.data.source.remote.TvInforRemoteDataSource;

public class TvDetailRepository {
    private static TvDetailRepository sInstance;
    private TvInforRemoteDataSource mTvInforRemoteDataSource;

    private TvDetailRepository(TvInforRemoteDataSource tvInforRemoteDataSource) {
        this.mTvInforRemoteDataSource = tvInforRemoteDataSource;
    }

    public static TvDetailRepository getInstance(TvInforRemoteDataSource tvInforRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new TvDetailRepository(tvInforRemoteDataSource);
        }
        return sInstance;
    }

    public void getTvInforFromApi(String url, BaseDataSource.Callback callback) {
        mTvInforRemoteDataSource.getTvInfor(url, callback);
    }

    public void getTrailerFromApi(String url, BaseDataSource.Callback callback) {
        mTvInforRemoteDataSource.getTrailer(url, callback);
    }

    public void getRecommendationFromApi(String url, BaseDataSource.Callback callback) {
        mTvInforRemoteDataSource.getRecommendation(url, callback);
    }

    public void getGenresFromApi(String url, BaseDataSource.Callback callback) {
        mTvInforRemoteDataSource.getGenres(url, callback);
    }

    public void getFeaturedCrewFromApi(String url, BaseDataSource.Callback callback) {
        mTvInforRemoteDataSource.getFeaturedCrew(url, callback);
    }

    public void getSeasonFromApi(String url, BaseDataSource.Callback callback) {
        mTvInforRemoteDataSource.getSeason(url, callback);
    }
}
