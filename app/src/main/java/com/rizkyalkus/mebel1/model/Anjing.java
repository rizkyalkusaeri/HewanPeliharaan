package com.rizkyalkus.mebel1.model;

public class Anjing {

    private String nama;
    private String imageUrl;

    public Anjing(String nama, String imageUrl) {
        this.nama = nama;
        this.imageUrl = imageUrl;
    }

    public Anjing(){

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
