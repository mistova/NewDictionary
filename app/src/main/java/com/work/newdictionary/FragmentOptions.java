package com.work.newdictionary;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

public class FragmentOptions extends Fragment implements View.OnClickListener {

    Drawable drawable;
    View view;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public static DataSource dataSource;

    Button button []= new Button[20];

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_options, container, false);
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        int clr = sharedPref.getInt("color", -13224394);
        view.setBackgroundColor(clr);

        findViews(view);
        for(int i = 0;i<20;i++)
            button[i].setOnClickListener(this);

        dataSource = new DataSource(getContext());
        dataSource.open();

        return view;
    }

    @Override
    public void onClick(View v) {
        Communicate c = (Communicate) getActivity();
        drawable = v.getBackground();
        int clr = ((ColorDrawable) drawable).getColor();
        if((v == button[0]) || (v == button[5]) || (v == button[10]) || (v == button[15])){
            view.setBackgroundColor(clr);
            reqId(clr);
            editor.putInt("color", clr);
            editor.commit();
            c.background(clr);
        }
        else{
            editor.putInt("action_color", clr);
            editor.commit();
            reqId(clr);
            c.actionBar(clr);
        }
    }
    private void reqId(int clr) {
        AndroidNetworking.get("http://kolayogrenci.com:1567/colors")
                .addQueryParameter("user_id", Integer.toString(dataSource.getId()))
                .addQueryParameter("color", Integer.toString(clr))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String  response) {
                        Log.i("FragDicID",response);
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("FragDicID", error.getMessage());
                    }
                });
    }

    private void findViews(View view) {
        int id;
        for(int i = 0; i<20; i++){
            id = getResources().getIdentifier("btn" + (i + 1), "id", getActivity().getPackageName());
            button[i] = view.findViewById(id);
        }
    }
}
