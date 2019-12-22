package com.work.newdictionary;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModel {
    @SerializedName("arraylist")
    private ArrayList<String> arrayList;

    public ResponseModel(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }
    public ArrayList<String> getRes() {
        return arrayList;
    }
}