package com.rizkyalkus.mebel1.repositories;

import androidx.lifecycle.MutableLiveData;

import com.rizkyalkus.mebel1.model.Anjing;
import com.rizkyalkus.mebel1.model.Ikan;

import java.util.ArrayList;
import java.util.List;

public class AnjingRepository {

    private static AnjingRepository instance;
    private ArrayList<Anjing> dataSet = new ArrayList<>();

    public static AnjingRepository getInstance(){
        if (instance == null){
            instance = new AnjingRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Anjing>> getAnjing(){
        setAnjing();
        MutableLiveData<List<Anjing>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setAnjing(){
        dataSet.add(
                new Anjing("Helder",
                        "https://anjingdijual.com/files/jenis-anjing/foto/herder-german-shepherd/german-shepherd-dog.jpg")
        );

    }
}
