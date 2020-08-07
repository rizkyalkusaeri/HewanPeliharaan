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
import com.rizkyalkus.mebel1.adapter.IkanAdapter;
import com.rizkyalkus.mebel1.model.Ikan;
import com.rizkyalkus.mebel1.viewmodels.IkanViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IkanFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private IkanAdapter mAdapter;
    private ProgressBar mProgressBar;
    private IkanViewModel mMainActivityViewModel;

    public IkanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FloatingActionButton mFab = view.findViewById(R.id.fab);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);

        mMainActivityViewModel = ViewModelProviders.of(this).get(IkanViewModel.class);

        mMainActivityViewModel.init();

        mMainActivityViewModel.getIkan().observe(getViewLifecycleOwner(), new Observer<List<Ikan>>() {
            @Override
            public void onChanged(List<Ikan> ikans) {
                mAdapter.notifyDataSetChanged();
            }
        });

        mMainActivityViewModel.getIsUpdating().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    showProgressBar();
                }
                else {
                    hideProgressBar();
                    mRecyclerView.smoothScrollToPosition(mMainActivityViewModel.getIkan().getValue().size()-1);
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivityViewModel.addNewValue(
                        new Ikan(
                                "Ikan Paus",
                                "https://thegorbalsla.com/wp-content/uploads/2019/08/ikan-paus.jpg"
                        )
                );
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        mAdapter = new IkanAdapter(mMainActivityViewModel.getIkan().getValue(), getContext());
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ikan, container,false);
    }
}
