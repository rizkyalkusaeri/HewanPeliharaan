package com.rizkyalkus.mebel1.viewmodels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rizkyalkus.mebel1.model.Anjing;
import com.rizkyalkus.mebel1.model.Ikan;
import com.rizkyalkus.mebel1.repositories.AnjingRepository;
import com.rizkyalkus.mebel1.repositories.IkanRepository;

import java.util.List;

public class AnjingViewModel extends ViewModel {

    private MutableLiveData<List<Anjing>> mAnjing;
    private AnjingRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if (mAnjing != null){
            return;
        }
        mRepo = AnjingRepository.getInstance();
        mAnjing = mRepo.getAnjing();
    }

    @SuppressLint("StaticFieldLeak")
    public void addNewValue(final Anjing anjing){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Anjing> currentAnjing = mAnjing.getValue();
                if (currentAnjing != null) {
                    currentAnjing.add(anjing);
                }
                mAnjing.postValue(currentAnjing);
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

    public LiveData<List<Anjing>> getAnjing(){
        return mAnjing;
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }

    public void onDestroy(){
        onCleared();
    }
}
