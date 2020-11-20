package com.example.addictionbreaker.ui;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;

import android.os.Build;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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

    private String addiction = "cigarettes" ;
    private DatabaseHelper myDb;
    private int lifeLostInSeconds;
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
    private TextView FirstDescription;

    private TextView WeekCost;
    private TextView WeekLife;

    private TextView MonthCost;
    private TextView MonthLife;

    private TextView YearCost;
    private TextView YearLife;

    private ImageView AddictiveObject;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView listView;
    String mTitle[]={"The Journey Starts",
            "Every Day Counts",
            "Steady Progress",
            "A Week of Awesome",
            "Double Digits",
            "The Worst is Behind",
            "Don't Stop Me Now",
            "Two SOLID Months",
            "A Hundred Days!",
            };
    String mDescription[]={"1 Day",
            "3 Days",
            "5 Days",
            "1 Week",
            "10 Days",
            "2 Weeks",
            "1 Month",
            "2 Months",
            "100 Days"};
    int images[]={R.drawable.empty_checkbox,
            R.drawable.empty_checkbox,
            R.drawable.empty_checkbox,
            R.drawable.empty_checkbox,
            R.drawable.empty_checkbox,
            R.drawable.empty_checkbox,
            R.drawable.empty_checkbox,
            R.drawable.empty_checkbox,
            R.drawable.empty_checkbox};

    int checkedbox = R.drawable.checked_box;
    int[] requirement= {1, 3 , 5, 7, 10, 14, 30, 60, 100};

    String[] rFirstDescription = {"Cigarettes YOU didn't Smoke","Alcoholic Drinks YOU didn't consume","Pod YOU didn't Smoked"};
    int[] rAddictiveObjects = {R.drawable.add3, R.drawable.alcohol, R.drawable.vape};
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDb = new DatabaseHelper(getContext());

        FirstDescription = view.findViewById(R.id.FirstDescription);
        AddictiveObject = view.findViewById(R.id.AddictiveObject);
        settingImgAndTitles();
        journey_numberOfConsumption = view.findViewById(R.id.journey_numberOfConsumption);
        journey_money= view.findViewById(R.id.journey_money);
        journey_lifeLost= view.findViewById(R.id.journey_lifeLost);
        Resources res = getResources();
        setLifeLost();
        String displayUsage = String.format(res.getString(R.string.journey_numberOfConsumption),Integer.toString(getConsumption()));
        String displayMoneyCost = String.format(res.getString(R.string.journey_money),Integer.toString(getTotalCost()));
        String displayLifeLost = String.format(res.getString(R.string.journey_lifeLost),Integer.toString(lifeLost_day)
                ,Integer.toString(lifeLost_hour),Integer.toString(lifeLost_minute));
        journey_numberOfConsumption.setText(displayUsage);
        journey_money.setText(displayMoneyCost);
        journey_lifeLost.setText(displayLifeLost);

        WeekCost = view.findViewById(R.id.WeekCost);
        String aWeekCost = Integer.toString( getCost()*7);
        WeekCost.setText(aWeekCost);

        WeekLife = view.findViewById(R.id.WeekLife);
        WeekLife.setText(getLifeLost(7));


        MonthCost = view.findViewById(R.id.MonthCost);
        String aMonthCost = Integer.toString(getCost()*30);
        MonthCost.setText(aMonthCost);

        MonthLife = view.findViewById(R.id.MonthLife);
        MonthLife.setText(getLifeLost(30));

        YearCost = view.findViewById(R.id.YearCost);
        String aYearCost = Integer.toString(getCost()*365);
        YearCost.setText(aYearCost);

        YearLife = view.findViewById(R.id.YearLife);
        YearLife.setText(getLifeLost(365));

        listView = view.findViewById(R.id.achievement_cards);

        MyAdapter adapter = new MyAdapter(getActivity().getApplicationContext(), mTitle, mDescription, images);
        listView.setAdapter(adapter);

    }
    private void settingImgAndTitles() {
        setAddiction();
        switch(addiction){
            case "Alcohol":
                FirstDescription.setText(rFirstDescription[1]);
                AddictiveObject.setImageResource(rAddictiveObjects[1]);
                break;
            case "Vaping/Juuling":
                FirstDescription.setText(rFirstDescription[2]);
                AddictiveObject.setImageResource(rAddictiveObjects[2]);
                break;
            default :
                FirstDescription.setText(rFirstDescription[0]);
                AddictiveObject.setImageResource(rAddictiveObjects[0]);
        }
    }
    private int getFrequency() {
        Cursor res = myDb.getAllData();
        while(res.moveToNext()) {
            frequency = res.getInt(4);
        }
        return frequency;
    }
    private int getCost() {
        Cursor res = myDb.getAllData();
        while(res.moveToNext()) {
            cost = res.getInt(5);
        }
        return cost;
    }
    private int getConsumption(){
        numberOfConsumption =  getFrequency()*getNumberOfDays();
        return numberOfConsumption;
    }
    private String setAddiction(){
        Cursor res = myDb.getAllData();
        while(res.moveToNext()) {
            addiction = res.getString(3);
        }
        return addiction;
    }
    private int getTotalCost(){
        money =  getCost()*getNumberOfDays();
        return money;
    }
    private int getNumberOfDays() {
        Cursor res = myDb.getAllData();
        while(res.moveToNext()) {
            numbersOfDays = res.getInt(6);
        }
        return numbersOfDays;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setLifeLost(){
        lifeLostInSeconds = getConsumption() * 9 * 60;
        int time = lifeLostInSeconds;
        lifeLost_day =  Math.floorDiv( time , (24 * 3600));
        time = time % (24 * 3600);
        lifeLost_hour = Math.floorDiv(time , 3600);
        time %= 3600;
        lifeLost_minute =Math.floorDiv( time , 60);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLifeLost(int Days){
        int lifeLostInSeconds = getFrequency()* Days * 9 * 60;
        int time = lifeLostInSeconds;
        String lifeLost_day =  Integer.toString(Math.floorDiv( time , (24 * 3600)));
        time = time % (24 * 3600);
        String lifeLost_hour = Integer.toString(Math.floorDiv(time , 3600));
        time %= 3600;
        int lifeLost_minute =Math.floorDiv( time , 60);

        return ((lifeLost_day + "d " + lifeLost_hour + "h " + lifeLost_minute + "m"));
    }
    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter(Context c, String title[], String description[], int imgs[]){
            super(c, R.layout.achievement_list, R.id.main_title, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Context c = getActivity().getApplicationContext();
            LayoutInflater layoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View achievement_list = layoutInflater.inflate(R.layout.achievement_list, parent, false);
            ImageView imgs = achievement_list.findViewById(R.id.image);
            TextView main_title = achievement_list.findViewById(R.id.main_title);
            TextView sub_title = achievement_list.findViewById(R.id.sub_title);


            main_title.setText(rTitle[position]);
            sub_title.setText(rDescription[position]);
            if (checkAchievement(position)){
                imgs.setImageResource(checkedbox);
            }
            else {
            imgs.setImageResource(rImgs[position]);
            }
            return achievement_list;
        }

        public boolean checkAchievement(int position){
            int numbsOfDays = getNumberOfDays();
            if (numbsOfDays >= requirement[position]){
                    return true;
            }
            return false;
        }

    }
}

