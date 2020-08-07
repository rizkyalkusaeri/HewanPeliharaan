package com.rizkyalkus.mebel1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rizkyalkus.mebel1.R;
import com.rizkyalkus.mebel1.adapter.AnjingAdapter;
import com.rizkyalkus.mebel1.model.Anjing;
import com.rizkyalkus.mebel1.viewmodels.AnjingViewModel;

import java.util.List;


public class AnjingFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private AnjingAdapter mAdapter;
    private ProgressBar mProgressBar;
    private AnjingViewModel mAnjingViewModel;

    public AnjingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anjing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton mFab = view.findViewById(R.id.fab);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);

        mAnjingViewModel = ViewModelProviders.of(this).get(AnjingViewModel.class);

        mAnjingViewModel.init();

        mAnjingViewModel.getAnjing().observe(getViewLifecycleOwner(), new Observer<List<Anjing>>() {
            @Override
            public void onChanged(List<Anjing> anjings) {
                mAdapter.notifyDataSetChanged();
            }
        });

        mAnjingViewModel.getIsUpdating().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    showProgressBar();
                }
                else {
                    hideProgressBar();
                    mRecyclerView.smoothScrollToPosition(mAnjingViewModel.getAnjing().getValue().size()-1);
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnjingViewModel.addNewValue(
                        new Anjing(
                                "Pitbull",
                                "https://asset-a.grid.id/crop/0x0:0x0/700x465/photo/2018/12/18/496995966.jpg"
                        )
                );
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        mAdapter = new AnjingAdapter(mAnjingViewModel.getAnjing().getValue(), getContext());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAnjingViewModel.onDestroy();
    }
}


