package com.example.admin.moviebd;

import com.example.admin.moviebd.data.source.remote.SearchResultRemoteDataSource;
import com.example.admin.moviebd.data.source.repository.MovieRepository;
import com.example.admin.moviebd.data.source.remote.MovieRemoteDataSource;
import com.example.admin.moviebd.data.source.repository.SearchResultRepository;

public class Injection {
    private static Injection sInstance;

    public Injection() {

    }

    public static Injection getInstance() {
        if (sInstance == null) {
            sInstance = new Injection();
        }
        return sInstance;
    }

    public MovieRepository getMovieRepository() {
        return MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance()
        );
    }

    public SearchResultRepository getSearchResultRepository() {
        return SearchResultRepository.getInstance(
                SearchResultRemoteDataSource.getInstance()
        );
    }
}
