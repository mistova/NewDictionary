package com.work.newdictionary;

import android.os.Bundle;

import com.google.gson.annotations.SerializedName;

public class ResponseModelId {
    @SerializedName("id")
    private int id;

    public ResponseModelId(int id) {
        this.id = id;
    }
    public Bundle getId() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        return bundle;
    }
}
