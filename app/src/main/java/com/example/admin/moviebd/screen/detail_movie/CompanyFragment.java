package com.example.admin.moviebd.screen.detail_movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.ProductionCompany;

import java.util.List;

public class CompanyFragment extends Fragment {
    private List<ProductionCompany> mCompanies;

    public CompanyFragment(List<ProductionCompany> companies) {
        mCompanies = companies;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setRecyclerCompany();
    }

    private void setRecyclerCompany() {
        RecyclerView mRecyclerCompany = (RecyclerView) getView().findViewById(R.id.recycler_company);
        mRecyclerCompany.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerCompany.setAdapter(new MovieCompanyAdapter(mCompanies, getContext()));
    }
}
