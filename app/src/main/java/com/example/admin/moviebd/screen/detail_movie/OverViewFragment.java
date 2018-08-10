package com.example.admin.moviebd.screen.detail_movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.moviebd.Injection;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.MovieInformation;
import com.example.admin.moviebd.utils.Constant;

public class OverViewFragment extends Fragment {
    private TextView mTextOverView;
    private String mOverView;

    public OverViewFragment(String overView) {
        mOverView = overView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_overview, container, false);
        return view;
    }

    @Override
    public void onStart() {
        init();
        super.onStart();
    }

    private void init() {
        mTextOverView = (TextView) getView().findViewById(R.id.text_overview);
        mTextOverView.setText(mOverView);
    }
}
