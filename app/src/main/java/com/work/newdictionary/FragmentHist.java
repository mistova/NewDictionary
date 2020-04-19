package com.work.newdictionary;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import static com.work.newdictionary.FragmentDict.dataSource;
public class FragmentHist extends Fragment implements AdapterView.OnItemClickListener {

    Communicate com;

    private AdView mAdView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        int clr = sharedPref.getInt("color", -16642494);
        view.setBackgroundColor(clr);

        com = (Communicate) getActivity();
        ListView list = view.findViewById(R.id.exam_list);
        ArrayList<ListViewModel> lvModel = new ArrayList<>();
        ArrayList<String> wordList = dataSource.listWords();
        for(int i = 0;i < wordList.size();i++){
            lvModel.add(new ListViewModel(wordList.get(i)));
        }
        ContactAdapter adaptor = new ContactAdapter(getActivity(), R.layout.special_list_item, lvModel);
        list.setAdapter(adaptor);
        list.setOnItemClickListener(this);

        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = view.findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListViewModel listViewModel = (ListViewModel) parent.getItemAtPosition(position);
        com.respond(listViewModel.toString());
    }
}
