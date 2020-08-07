package com.rizkyalkus.mebel1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rizkyalkus.mebel1.R;
import com.rizkyalkus.mebel1.adapter.AnjingAdapter;
import com.rizkyalkus.mebel1.adapter.KucingAdapter;
import com.rizkyalkus.mebel1.model.Anjing;
import com.rizkyalkus.mebel1.model.Kucing;
import com.rizkyalkus.mebel1.viewmodels.AnjingViewModel;
import com.rizkyalkus.mebel1.viewmodels.BurungViewModel;
import com.rizkyalkus.mebel1.viewmodels.KucingViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KucingFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private KucingAdapter mAdapter;
    private ProgressBar mProgressBar;
    private KucingViewModel mKucingViewModel;

    public KucingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kucing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton mFab = view.findViewById(R.id.fab);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);

        mKucingViewModel = ViewModelProviders.of(this).get(KucingViewModel.class);

        mKucingViewModel.init();

        mKucingViewModel.getKucing().observe(getViewLifecycleOwner(), new Observer<List<Kucing>>() {
            @Override
            public void onChanged(List<Kucing> kucings) {
                mAdapter.notifyDataSetChanged();
            }
        });

        mKucingViewModel.getIsUpdating().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    showProgressBar();
                }
                else {
                    hideProgressBar();
                    mRecyclerView.smoothScrollToPosition(mKucingViewModel.getKucing().getValue().size()-1);
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKucingViewModel.addNewValue(
                        new Kucing(
                                "Ragdoll",
                                "https://upload.wikimedia.org/wikipedia/commons/6/64/Ragdoll_from_Gatil_Ragbelas.jpg"
                        )
                );
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        mAdapter = new KucingAdapter(mKucingViewModel.getKucing().getValue(), getContext());
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
}

