package com.example.admin.moviebd.data.model;

import org.json.JSONObject;

import java.io.Serializable;

public class TV implements Serializable {
    private int mId;
    private double mVote;
    private String mName, mDate, mOverview, mPathImage;

    public TV(int id, String name, String pathImage, String date, double vote, String overview) {
        mId = id;
        mVote = vote;
        mName = name;
        mDate = date;
        mOverview = overview;
        mPathImage = pathImage;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public double getVote() {
        return mVote;
    }

    public void setVote(double vote) {
        mVote = vote;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getPathImage() {
        return mPathImage;
    }

    public void setPathImage(String pathImage) {
        mPathImage = pathImage;
    }

    public TV(JSONObject jsonObject) {
        try {
            mId = jsonObject.optInt(TV.NameParseUrl.ID, 0);
            mName = jsonObject.optString(NameParseUrl.NAME, null);
            mPathImage = jsonObject.optString(NameParseUrl.PATH_IMAGE, null);
            mDate = jsonObject.optString(NameParseUrl.DATE, null);
            mVote = jsonObject.optDouble(NameParseUrl.VOTE, 0);
            mOverview = jsonObject.optString(NameParseUrl.OVER_VIEW, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @interface NameParseUrl {
        String ID = "id";
        String PATH_IMAGE = "poster_path";
        String NAME = "name";
        String DATE = "first_air_date";
        String VOTE = "vote_average";
        String OVER_VIEW = "overview";
        String RESULTS = "results";
    }
}
