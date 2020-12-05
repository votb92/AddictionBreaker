package com.example.addictionbreaker.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.addictionbreaker.NotificationsActivity;
import com.example.addictionbreaker.R;
import com.example.addictionbreaker.data.DatabaseHelper;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText new_name;
    private Button name_change_button;
    private DatabaseHelper myDb;
    private Context context;


    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDb = new DatabaseHelper(view.getContext());
        Button notifications = view.findViewById(R.id.notification_button);
        new_name = view.findViewById(R.id.new_name);

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotificationsActivity.class);
                startActivity(intent);
            }
        });
        name_change_button = view.findViewById(R.id.name_change_button);
        name_change_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Log.i("test", name.getText().toString());
                if (new_name.getText().toString().isEmpty()) {
                    AlertDialog a = new AlertDialog.Builder(name_change_button.getContext()).create();
                    a.setTitle("Missing Fields");
                    a.setMessage("Please enter your name");
                    a.show();
                } else {
                    myDb.updateName(new_name.getText().toString());
                    Toast.makeText(view.getContext(), "Name Changed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
