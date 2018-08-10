package com.example.admin.moviebd.data.model;

import org.json.JSONObject;

import java.io.Serializable;

public class FeaturedCrew implements Serializable {
    private int mId;
    private String mName, mPathImage;

    public FeaturedCrew(int id, String name, String pathImage) {
        mId = id;
        mName = name;
        mPathImage = pathImage;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPathImage() {
        return mPathImage;
    }

    public void setPathImage(String pathImage) {
        mPathImage = pathImage;
    }

    public FeaturedCrew(JSONObject jsonObject) {
        try {
            mId = jsonObject.optInt(NameParseUrl.ID);
            mName = jsonObject.optString(NameParseUrl.NAME);
            mPathImage = jsonObject.optString(NameParseUrl.PATH_IMAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @interface NameParseUrl {
        String CREATED_BY = "created_by";
        String ID = "id";
        String NAME = "name";
        String PATH_IMAGE = "profile_path";
    }
}
