package com.example.admin.moviebd.data.source.remote;

import com.example.admin.moviebd.data.model.FeaturedCrew;
import com.example.admin.moviebd.data.model.Genres;
import com.example.admin.moviebd.data.model.Recommendation;
import com.example.admin.moviebd.data.model.Season;
import com.example.admin.moviebd.data.model.TV;
import com.example.admin.moviebd.data.model.Trailer;
import com.example.admin.moviebd.data.source.TVDetailDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TvInforRemoteDataSource implements TVDetailDataSource {
    private static TvInforRemoteDataSource sInstance;

    private TvInforRemoteDataSource() {

    }

    public static TvInforRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new TvInforRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getTvInfor(String urlType, final Callback<TV> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    TV tv = new TV(jsonObject);

                    callback.onGetSuccess(tv);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }


            @Override
            public void onGetFailure(Exception exeption) {
                callback.onGetFailure(exeption);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(urlType);
    }

    @Override
    public void getGenres(String urlType, final Callback<List<Genres>> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<Genres> genres = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(Genres.NameParseUrl.GENRES);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objectGenres = jsonArray.getJSONObject(i);
                        genres.add(new Genres(objectGenres));
                    }
                    callback.onGetSuccess(genres);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }


            @Override
            public void onGetFailure(Exception exeption) {
                callback.onGetFailure(exeption);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(urlType);
    }

    @Override
    public void getFeaturedCrew(String urlType, final Callback<List<FeaturedCrew>> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<FeaturedCrew> crews = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(FeaturedCrew.NameParseUrl.CREATED_BY);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objectCrew = jsonArray.getJSONObject(i);
                        crews.add(new FeaturedCrew(objectCrew));
                    }
                    callback.onGetSuccess(crews);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }

            @Override
            public void onGetFailure(Exception exeption) {
                callback.onGetFailure(exeption);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(urlType);
    }

    @Override
    public void getSeason(String urlType, final Callback<List<Season>> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<Season> seasons = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(Season.NameParseUrl.SEASONS);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objectSeason = jsonArray.getJSONObject(i);
                        seasons.add(new Season(objectSeason));
                    }
                    callback.onGetSuccess(seasons);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }

            @Override
            public void onGetFailure(Exception exeption) {
                callback.onGetFailure(exeption);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(urlType);
    }

    @Override
    public void getTrailer(String urlType, final Callback<List<Trailer>> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<Trailer> trailers = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(Trailer.NameParseUrl.RESULT);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Trailer trailer = new Trailer(jsonArray.getJSONObject(i));
                        trailers.add(trailer);
                    }

                    callback.onGetSuccess(trailers);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }

            @Override
            public void onGetFailure(Exception exeption) {
                callback.onGetFailure(exeption);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(urlType);
    }

    @Override
    public void getRecommendation(String urlType, final Callback<List<Recommendation>> callback) {
        new LoadDataAsyntask(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                List<Recommendation> recommendations = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray(Recommendation.NameParseUrl.RESULT);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Recommendation recommendation = new Recommendation(jsonArray.getJSONObject(i));
                        recommendations.add(recommendation);
                    }

                    callback.onGetSuccess(recommendations);
                } catch (JSONException e) {
                    callback.onGetFailure(e);
                }
            }

            @Override
            public void onGetFailure(Exception exeption) {
                callback.onGetFailure(exeption);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(urlType);
    }
}
