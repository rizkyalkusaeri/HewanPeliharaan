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
import com.rizkyalkus.mebel1.adapter.BurungAdapter;
import com.rizkyalkus.mebel1.model.Burung;
import com.rizkyalkus.mebel1.viewmodels.BurungViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BurungFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private BurungAdapter mAdapter;
    private ProgressBar mProgressBar;
    private BurungViewModel mBurungViewModel;


    public BurungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_burung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton mFab = view.findViewById(R.id.fab);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);

        mBurungViewModel = ViewModelProviders.of(this).get(BurungViewModel.class);

        mBurungViewModel.init();

        mBurungViewModel.getBurung().observe(getViewLifecycleOwner(), new Observer<List<Burung>>() {
            @Override
            public void onChanged(List<Burung> burungs) {
                mAdapter.notifyDataSetChanged();
            }
        });

        mBurungViewModel.getIsUpdating().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    showProgressBar();
                }
                else {
                    hideProgressBar();
                    mRecyclerView.smoothScrollToPosition(mBurungViewModel.getBurung().getValue().size()-1);
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBurungViewModel.addNewValue(
                        new Burung(
                                "Cendrawasih",
                                "https://www.indovoices.com/wp-content/uploads/2018/08/burung-cendrawasih_1-700x375.jpg"
                        )
                );
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        mAdapter = new BurungAdapter(mBurungViewModel.getBurung().getValue(), getContext());
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

