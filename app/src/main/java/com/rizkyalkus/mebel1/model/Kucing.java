package com.rizkyalkus.mebel1.model;

public class Kucing {

    private String nama;
    private String imageUrl;

    public Kucing(String nama, String imageUrl) {
        this.nama = nama;
        this.imageUrl = imageUrl;
    }

    public Kucing(){

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
