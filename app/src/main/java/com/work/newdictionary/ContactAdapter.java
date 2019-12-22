package com.work.newdictionary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ContactAdapter extends ArrayAdapter<ListViewModel> {
    private Context context;
    private ArrayList<ListViewModel> lvModel;
    private View view;
    private int resource;

    public ContactAdapter(Context context, int resource, ArrayList<ListViewModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.lvModel = objects;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return lvModel.size();
    }

    @Override
    public ListViewModel getItem(int position) {
        return lvModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(resource, parent,false);
        TextView str = view.findViewById(R.id.spc_text1);
        TextView btn = view.findViewById(R.id.special_btn);
        String clr [] = {"FF00BFA5", "FFC51162", "FF64DD17", "FFAEEA00", "FFFFD600", "FFFFAB00", "FFFF6D00", "FFDD2C00", "FF6200EA", "FF6200EA"
                        ,"FFAA00FF", "FF44D600", "FF77AB00", "FF116D00", "FF552C00", "FF2962FF", "FFFFD622", "FFFFAB55", "FFFF6D88", "FFDD2Cbb"};
        Random rand = new Random();
        btn.setBackgroundColor(Color.parseColor("#" + clr[rand.nextInt(20)]));

        ListViewModel lvModel = getItem(position);
        str.setText(lvModel.toString());

        return view;
    }
}