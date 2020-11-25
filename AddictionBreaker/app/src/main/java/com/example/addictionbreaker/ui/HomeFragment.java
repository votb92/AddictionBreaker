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
import java.util.Random;

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
    private TextView greeting;
    private TextView weeks_count;
    private TextView months_count;
    private TextView year_count;
    private TextView random_quote;
    private TextView author;
    Random rand = new Random();
    int randomNumber;
    private String[] random_quotes={"Be yourself; everyone else is already taken.","― Oscar Wilde",
            "Be the change that you wish to see in the world.", "― Mahatma Gandhi",
            "No one can make you feel inferior without your consent.", "― Eleanor Roosevelt",
            "Live as if you were to die tomorrow. Learn as if you were to live forever.","― Mahatma Gandhi",
            "There are only two ways to live your life. One is as though nothing is a miracle. The other is as though everything is a miracle.","― Albert Einstein",
            "It is never too late to be what you might have been.", "― George Eliot",
            "Life isn't about finding yourself. Life is about creating yourself","― George Bernard Shaw",
            "Success is not final, failure is not fatal: it is the courage to continue that counts", "― Winston S. Churchill",
            "Don't be afraid of your fears. They're not there to scare you. They're there to let you know that something is worth it.", "― C. JoyBell C."
    };
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
        greeting = view.findViewById(R.id.greeting_text);
        myDb = new DatabaseHelper(getContext());
        String userName = myDb.getUserName();

        greeting.append(" " + userName);
        getStartDate();

        yourProfileButton = view.findViewById(R.id.yourProfileButton);
        yourProfileButton.setVisibility(View.GONE);
        weeks_count = view.findViewById(R.id.weeks_count);
        months_count = view.findViewById(R.id.months_count);
        year_count = view.findViewById(R.id.years_count);
        viewAll();
        gettingNumberOfDays();

        random_quote = view.findViewById(R.id.random_quote);
        author = view.findViewById(R.id.author);
        randomNumber = rand.nextInt(random_quotes.length-1);
        while (isEven(randomNumber)){
            randomNumber = rand.nextInt(random_quotes.length-1);
        }
        random_quote.setText(random_quotes[randomNumber-1]);
        author.setText(random_quotes[randomNumber]);


        Calendar startingDate = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();
        startingDate.set(startDate.get(0), startDate.get(1), startDate.get(2), startDate.get(3), startDate.get(4));
        currentDate.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE));

        long startingInMillis = startingDate.getTimeInMillis();
        long currentInMillis = currentDate.getTimeInMillis();

        long difference = currentInMillis - startingInMillis;
        long numDays = difference / (24 * 60 * 60 * 1000);
        int weeks = (int) numDays / 7;
        int months = (int) numDays/ 30;
        int years = (int) numDays/365;

        weeks_count.setText(String.valueOf(weeks));
        months_count.setText(String.valueOf(months));
        year_count.setText(String.valueOf(years));
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
            break;
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

    boolean isEven(int num) { return ((num % 2) == 0); }
}