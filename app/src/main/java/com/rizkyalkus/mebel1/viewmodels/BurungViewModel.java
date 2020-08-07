package com.rizkyalkus.mebel1.viewmodels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rizkyalkus.mebel1.model.Anjing;
import com.rizkyalkus.mebel1.model.Burung;
import com.rizkyalkus.mebel1.repositories.AnjingRepository;
import com.rizkyalkus.mebel1.repositories.BurungRepository;

import java.util.List;

public class BurungViewModel extends ViewModel {

    private MutableLiveData<List<Burung>> mBurung;
    private BurungRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if (mBurung != null){
            return;
        }
        mRepo = BurungRepository.getInstance();
        mBurung = mRepo.getBurung();
    }

    @SuppressLint("StaticFieldLeak")
    public void addNewValue(final Burung burung){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Burung> currentBurung = mBurung.getValue();
                if (currentBurung != null) {
                    currentBurung.add(burung);
                }
                mBurung.postValue(currentBurung);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<Burung>> getBurung(){
        return mBurung;
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
