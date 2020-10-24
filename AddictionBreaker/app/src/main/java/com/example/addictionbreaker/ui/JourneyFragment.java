package com.example.addictionbreaker.ui;

import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.addictionbreaker.R;
import com.example.addictionbreaker.data.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JourneyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JourneyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private DatabaseHelper myDb;
    private int lifeLost;
    private int lifeLost_hour;
    private int lifeLost_minute;
    private int lifeLost_day;
    private int money;
    private int numberOfConsumption;
    private int frequency;
    private int cost;
    private int numbersOfDays;
    private TextView journey_numberOfConsumption  ;
    private TextView journey_money;
    private TextView journey_lifeLost;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JourneyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JourneyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JourneyFragment newInstance(String param1, String param2) {
        JourneyFragment fragment = new JourneyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_journey, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDb = new DatabaseHelper(getContext());
        journey_numberOfConsumption = view.findViewById(R.id.journey_numberOfConsumption);
        journey_money= view.findViewById(R.id.journey_money);
        journey_lifeLost= view.findViewById(R.id.journey_lifeLost);
        Resources res = getResources();
        String displayUsage = String.format(res.getString(R.string.journey_numberOfConsumption),Integer.toString(getConsumption()));
        journey_numberOfConsumption.setText(Integer.toString(getFrequency()));
//        String displayMoneyCost =
//        String displayLifeLost =
    }
    private int getFrequency() {
        Cursor res = myDb.getAllData();
        while(res.moveToNext()) {
            frequency = res.getInt(5);
        }
        return frequency;
    }
    private int getCost() {
        Cursor res = myDb.getAllData();
        while(res.moveToNext()) {
            cost = res.getInt(6);
        }
        return cost;
    }
    private int getConsumption(){
        numberOfConsumption =  getFrequency()*getNumberOfDays();
        return numberOfConsumption;
    }
    private int getNumberOfDays() {
        Cursor res = myDb.getAllData();
        while(res.moveToNext()) {
            numbersOfDays = res.getInt(6);
        }
        return numbersOfDays;
    }
}

