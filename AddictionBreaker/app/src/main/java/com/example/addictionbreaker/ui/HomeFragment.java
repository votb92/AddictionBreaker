package com.example.addictionbreaker.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.addictionbreaker.R;
import com.example.addictionbreaker.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private DatabaseHelper myDb;
    private Button yourProfileButton;
    private Button resetButton;
    private TextView home_numberOfDays;
    private String numbersOfDays;
    ArrayList<Integer> startDate = new ArrayList<>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDb = new DatabaseHelper(getContext());
        getStartDate();

        Log.i("year", String.valueOf(startDate.get(0)));
        yourProfileButton = view.findViewById(R.id.yourProfileButton);
        viewAll();
        gettingNumberOfDays();

        Calendar startingDate = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();
        startingDate.set(startDate.get(0), startDate.get(1), startDate.get(2), startDate.get(3), startDate.get(4));
        currentDate.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE));

        long startingInMillis = startingDate.getTimeInMillis();
        long currentInMillis = currentDate.getTimeInMillis();

        long difference = currentInMillis - startingInMillis;
        long numDays = difference / (24 * 60 * 60 * 1000);
        numbersOfDays = String.valueOf(numDays);
        setDay(numDays);

        home_numberOfDays = view.findViewById(R.id.home_numberOfDays);
        home_numberOfDays.setText(numbersOfDays);
        resetButton = view.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ResetProgressActivity.class);
                startActivity(intent);
            }
        });

    }

    private int gettingNumberOfDays() {
        Cursor res = myDb.getAllData();
        while(res.moveToNext()) {
            numbersOfDays = res.getString(6);
        }
        return Integer.parseInt(numbersOfDays);
    }

    private void getStartDate(){
        Cursor res = myDb.getAllData();
        while(res.moveToNext()){
            startDate.add(res.getInt(7));
            startDate.add(res.getInt(8));
            startDate.add(res.getInt(9));
            startDate.add(res.getInt(10));
            startDate.add(res.getInt(11));
        }
    }

    /**
     * Demonstration of how to pull data from storage
     *
     * @return all the information that we store!
     */
    public void viewAll(){
        yourProfileButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Error", "Nothing was found");
                            return;
                        }
                        StringBuffer information = new StringBuffer();
                        while (res.moveToNext()){
                            information.append("ID: " + res.getString(0) +"\n");
                            information.append("NAME: " + res.getString(1) +"\n");
                            information.append("AGE: " + res.getString(2) +"\n");
                            information.append("ADDICTION: " + res.getString(3) +"\n");
                            information.append("FREQUENCY: " + res.getString(4) +"\n");
                            information.append("COST: " + res.getString(5) +"\n");
                            information.append("DAYS WITHOUT USING: " + res.getString(6) +"\n");
                            information.append("STARTING POINT, YEAR: " + res.getString(8)+ "\n");
                        }
                        showMessage("Data", information.toString());
                    }
                }
        );
    }

    public void showMessage(String Title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }
    /* Adding one day to the current day
    * */
    public boolean addOneDay(){
        int newNumberOfDays = gettingNumberOfDays() + 1;
        return myDb.updateDay(newNumberOfDays);
    }
    /* set the day to an new number
     * */
    public boolean setDay(long newNumberOfDays){
        return myDb.updateDay(newNumberOfDays);
    }
}