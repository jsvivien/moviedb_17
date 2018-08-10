package com.example.admin.moviebd.data.source;

import com.example.admin.moviebd.data.model.FeaturedCrew;
import com.example.admin.moviebd.data.model.Genres;
import com.example.admin.moviebd.data.model.MovieInformation;
import com.example.admin.moviebd.data.model.MovieRecommendation;
import com.example.admin.moviebd.data.model.ProductionCompany;
import com.example.admin.moviebd.data.model.Recommendation;
import com.example.admin.moviebd.data.model.Season;
import com.example.admin.moviebd.data.model.TV;
import com.example.admin.moviebd.data.model.Trailer;

import java.util.List;

public interface TVDetailDataSource extends BaseDataSource {
    void getTvInfor(String urlType, Callback<TV> callback);

    void getGenres(String urlType, Callback<List<Genres>> callback);

    void getFeaturedCrew(String urlType, Callback<List<FeaturedCrew>> callback);

    void getSeason(String urlType, Callback<List<Season>> callback);

    void getTrailer(String urlType, Callback<List<Trailer>> callback);

    void getRecommendation(String urlType, Callback<List<Recommendation>> callback);
}
