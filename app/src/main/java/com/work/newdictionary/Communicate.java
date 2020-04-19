package com.work.newdictionary;

import android.app.Activity;

public interface Communicate {
    public void respond(String word);
    public void background(int clr);
    public void actionBar(int clr);
    public void hideKeyboard(Activity activity);
}
