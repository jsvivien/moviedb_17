package com.example.admin.moviebd.data.model;

import org.json.JSONObject;

public class Season {
    private int mId;
    private String mName;
    private String mPathImage;

    public Season(int id, String name, String pathImage) {
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

    public Season(JSONObject jsonObject) {
        try {
            mId = jsonObject.optInt(NameParseUrl.ID, 0);
            mName = jsonObject.optString(NameParseUrl.NAME, null);
            mPathImage = jsonObject.optString(NameParseUrl.PATH_IMAGE, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @interface NameParseUrl {
        String SEASONS = "seasons";
        String ID = "id";
        String NAME = "name";
        String PATH_IMAGE = "poster_path";
    }
}
