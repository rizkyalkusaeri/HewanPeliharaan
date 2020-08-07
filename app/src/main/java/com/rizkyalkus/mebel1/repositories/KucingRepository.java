package com.rizkyalkus.mebel1.repositories;

import androidx.lifecycle.MutableLiveData;

import com.rizkyalkus.mebel1.model.Anjing;
import com.rizkyalkus.mebel1.model.Kucing;

import java.util.ArrayList;
import java.util.List;

public class KucingRepository {

    private static KucingRepository instance;
    private ArrayList<Kucing> dataSet = new ArrayList<>();

    public static KucingRepository getInstance(){
        if (instance == null){
            instance = new KucingRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Kucing>> getKucing(){
        setKucing();
        MutableLiveData<List<Kucing>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setKucing(){
        dataSet.add(
                new Kucing("Turkish Angora",
                        "https://masandy.com/wp-content/uploads/2020/01/Kucing-Anggora.jpeg")
        );

    }
}
