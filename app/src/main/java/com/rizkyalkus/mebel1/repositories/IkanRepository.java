package com.rizkyalkus.mebel1.repositories;

import androidx.lifecycle.MutableLiveData;

import com.rizkyalkus.mebel1.model.Ikan;

import java.util.ArrayList;
import java.util.List;

public class IkanRepository {

    private static IkanRepository instance;
    private ArrayList<Ikan> dataSet = new ArrayList<>();

    public static IkanRepository getInstance(){
        if (instance == null){
            instance = new IkanRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Ikan>> getIkan(){
        setIkan();
        MutableLiveData<List<Ikan>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setIkan(){
        dataSet.add(
                new Ikan("Ikan Mas",
                        "https://assets.kompasiana.com/items/album/2020/02/14/goldfish-537832-640-5e46287f097f360cd66ac862.jpg?t=o&v=740&x=416")
        );
        dataSet.add(
                new Ikan("Ikan Gabus",
                        "https://www.covesia.com/assets/foto/berita/20191012114542.jpg")
        );

    }
}
