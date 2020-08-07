package com.rizkyalkus.mebel1.viewmodels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rizkyalkus.mebel1.model.Ikan;
import com.rizkyalkus.mebel1.repositories.IkanRepository;

import java.util.List;

public class IkanViewModel extends ViewModel {

    private MutableLiveData<List<Ikan>> mIkan;
    private IkanRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if (mIkan != null){
            return;
        }
        mRepo = IkanRepository.getInstance();
        mIkan = mRepo.getIkan();
    }

    @SuppressLint("StaticFieldLeak")
    public void addNewValue(final Ikan ikan){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Ikan> currentIkan = mIkan.getValue();
                if (currentIkan != null) {
                    currentIkan.add(ikan);
                }
                mIkan.postValue(currentIkan);
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

    public LiveData<List<Ikan>> getIkan(){
        return mIkan;
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
