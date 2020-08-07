package com.rizkyalkus.mebel1.viewmodels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rizkyalkus.mebel1.model.Anjing;
import com.rizkyalkus.mebel1.model.Kucing;
import com.rizkyalkus.mebel1.repositories.AnjingRepository;
import com.rizkyalkus.mebel1.repositories.KucingRepository;

import java.util.List;

public class KucingViewModel extends ViewModel {

    private MutableLiveData<List<Kucing>> mKucing;
    private KucingRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if (mKucing != null){
            return;
        }
        mRepo = KucingRepository.getInstance();
        mKucing = mRepo.getKucing();
    }

    @SuppressLint("StaticFieldLeak")
    public void addNewValue(final Kucing kucing){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Kucing> currentKucing = mKucing.getValue();
                if (currentKucing != null) {
                    currentKucing.add(kucing);
                }
                mKucing.postValue(currentKucing);
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

    public LiveData<List<Kucing>> getKucing(){
        return mKucing;
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
