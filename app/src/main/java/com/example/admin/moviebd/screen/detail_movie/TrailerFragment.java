package com.example.admin.moviebd.screen.detail_movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Trailer;
import com.example.admin.moviebd.screen.detail_tv.TrailerAdapter;
import com.example.admin.moviebd.screen.trailer.TrailerActivity;
import com.example.admin.moviebd.utils.Constant;

import java.util.List;

public class TrailerFragment extends Fragment implements TrailerAdapter.ItemTrailerClick {
    private List<Trailer> mTrailers;

    public TrailerFragment(List<Trailer> trailers) {
        mTrailers = trailers;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trailer, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setRecyclerTrailer();
    }

    private void setRecyclerTrailer() {
        RecyclerView mRecyclerTrailer = (RecyclerView) getView().findViewById(R.id.recycler_trailer);
        mRecyclerTrailer.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerTrailer.setAdapter(new TrailerAdapter(mTrailers, getContext(), this));
    }

    @Override
    public void onClickTrailer(String idTrailer) {
        getTrailerIntent(getContext(), idTrailer);
    }

    public static Intent getTrailerIntent(Context context, String idTrailer) {
        Intent intent = new Intent(context, TrailerActivity.class);
        intent.putExtra(Constant.BaseApiUrl.ID_MOVIE, idTrailer);
        return intent;
    }
}
