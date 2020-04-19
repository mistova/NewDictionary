package com.work.newdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Communicate{

    Button src, hist, opt;

    FragmentManager manager;

    char frgmMng = 'd';

    LinearLayout l;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        src = findViewById(R.id.search_button);
        hist = findViewById(R.id.hist_button);
        opt = findViewById(R.id.options_button);
        l = findViewById(R.id.linearLayout2);

        src.setOnClickListener(this);
        hist.setOnClickListener(this);
        opt.setOnClickListener(this);

        int clr = sharedPref.getInt("action_color", -16642494);
        actionBar(clr);
        clr = sharedPref.getInt("color", -1);
        background(clr);

        manager = getSupportFragmentManager();
        FragmentDict fragmentDict = new FragmentDict();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_layout, fragmentDict, "fragmentDict");
        transaction.commit();
    }

    public void onClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (view == src) {
            if (frgmMng != 'd') {
                FragmentDict fragmentDict = new FragmentDict();
                transaction.replace(R.id.frame_layout, fragmentDict, "fragmentDict");
                transaction.addToBackStack(null);
                transaction.commit();
                frgmMng = 'd';
            }
        } else if (view == hist) {
            if (frgmMng != 'h') {
                FragmentHist fragmentHist = new FragmentHist();
                transaction.replace(R.id.frame_layout, fragmentHist, "fragmentHist");
                transaction.addToBackStack(null);
                transaction.commit();
                frgmMng = 'h';
            }
        } else if (view == opt) {
            if (frgmMng != 'o') {
                FragmentOptions fragmentOptions = new FragmentOptions();
                transaction.replace(R.id.frame_layout, fragmentOptions, "fragmentOptions");
                transaction.addToBackStack(null);
                transaction.commit();
                frgmMng = 'o';
            }
        }
    }

    @Override
    public void respond(String word) {
        Bundle bundle = new Bundle();
        bundle.putString("word", word);
        bundle.putBoolean("cnt", true);
        FragmentTransaction transaction = manager.beginTransaction();
        FragmentDict fragmentDict = new FragmentDict();
        fragmentDict.setArguments(bundle);
        transaction.replace(R.id.frame_layout, fragmentDict, "fragmentDict");
        transaction.addToBackStack(null);
        transaction.commit();
        frgmMng = 'd';
    }

    @Override
    public void background(int clr) {
        editor.putInt("color", clr);
        editor.commit();
        View view = this.getWindow().getDecorView();  // get Background
        view.setBackgroundColor(clr);
        l.setBackgroundColor(clr);
    }
    public void actionBar(int clr) {
        editor.putInt("action_color", clr);
        editor.commit();
        getSupportActionBar().setBackgroundDrawable( new ColorDrawable(clr));
        src.setBackgroundColor(clr);
        hist.setBackgroundColor(clr);
        opt.setBackgroundColor(clr);
    }
    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
