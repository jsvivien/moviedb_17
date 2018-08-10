package com.example.admin.moviebd.screen.detail_tv;

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

public interface TvDetailContract {
    interface View {
        void onStartLoading();

        void onSuccessTvInfor(TV tv);

        void onSuccessGenres(List<Genres> genres);

        void onSuccessFeaturedCrew(List<FeaturedCrew> crews);

        void onSuccessSeason(List<Season> seasons);

        void onSuccessTrailer(List<Trailer> trailers);

        void onSuccessRecommendation(List<Recommendation> recommendations);

        void onFailed(Exception e);

        void onDismissLoading();
    }

    interface Presenter {
        void getTvInformationFromApi(String url);

        void getGenresFromApi(String url);

        void getFeaturedCrewFromApi(String url);

        void getSeasonFromApi(String url);

        void getTrailerFromApi(String url);

        void getRecommendationFromApi(String url);
    }
}
