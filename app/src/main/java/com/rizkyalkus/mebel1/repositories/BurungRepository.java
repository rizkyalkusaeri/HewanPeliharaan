package com.rizkyalkus.mebel1.repositories;

import androidx.lifecycle.MutableLiveData;

import com.rizkyalkus.mebel1.model.Burung;

import java.util.ArrayList;
import java.util.List;

public class BurungRepository {

    private static BurungRepository instance;
    private ArrayList<Burung> dataSet = new ArrayList<>();

    public static BurungRepository getInstance(){
        if (instance == null){
            instance = new BurungRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Burung>> getBurung(){
        setBurung();
        MutableLiveData<List<Burung>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setBurung(){
        dataSet.add(
                new Burung("Kakatua",
                        "https://cdn2.tstatic.net/tribunnews/foto/bank/images/kakak-tua-jambul_20141102_124516.jpg")
        );

    }
}
