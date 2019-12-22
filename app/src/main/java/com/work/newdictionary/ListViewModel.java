package com.work.newdictionary;

public class ListViewModel {

    private String str;

    public ListViewModel(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}