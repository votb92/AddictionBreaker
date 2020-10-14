package com.example.addictionbreaker.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.addictionbreaker.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LinksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LinksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LinksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LinksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LinksFragment newInstance(String param1, String param2) {
        LinksFragment fragment = new LinksFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_links, container, false);
        TextView textMotivation = view.findViewById(R.id.Overcoming_Motivation);
        TextView textMotivations = view.findViewById(R.id.Stopping_motivations);
        TextView textAlcoholEffects = view.findViewById(R.id.Alcohol_Effects);
        TextView textAlcoholImpact = view.findViewById(R.id.Alcohol_Impact);
        TextView textAlcoholImpactBrain = view.findViewById(R.id.Alcohol_Impact_Brain);
        TextView textQuitSmoking = view.findViewById(R.id.Quit_Smoking);
        TextView textVapeEffect = view.findViewById(R.id.Vape_Effect);
        TextView textSmokeEffect = view.findViewById(R.id.Smoking_Effects);
        TextView textImpactSmoke = view.findViewById(R.id.Impact_Of_Smoking);
        TextView textSmokeSafety = view.findViewById(R.id.Smoke_Safety);


        textMotivation.setOnClickListener(listener -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.helpguide.org/articles/addictions/overcoming-alcohol-addiction.htm"));
                LinksFragment.this.startActivity(browserIntent);
        });
        textMotivations.setOnClickListener(listener -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ashevillerecoverycenter.com/how-your-body-changes-when-you-stop-drinking-alcohol/"));
                    LinksFragment.this.startActivity(browserIntent);
                }
        );
        textAlcoholEffects.setOnClickListener(listener -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.niaaa.nih.gov/alcohols-effects-health/alcohols-effects-body"));
                    LinksFragment.this.startActivity(browserIntent);
                }
        );
        textAlcoholImpact.setOnClickListener(listener -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.recovery.org/alcohol-addiction/effects-body/"));
                    LinksFragment.this.startActivity(browserIntent);
                }
        );
        textAlcoholImpactBrain.setOnClickListener(listener -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=7x6HUNTnXUw"));
                    LinksFragment.this.startActivity(browserIntent);
                }
        );
        textQuitSmoking.setOnClickListener(listener -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://smokefree.gov/quit-smoking/why-you-should-quit/reasons-to-quit"));
                    LinksFragment.this.startActivity(browserIntent);
                }
        );
        textVapeEffect.setOnClickListener(listener -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.thelist.com/170132/what-vaping-really-does-to-your-body/"));
                    LinksFragment.this.startActivity(browserIntent);
                }
        );
        textSmokeEffect.setOnClickListener(listener -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.healthline.com/health/smoking/effects-on-body"));
                    LinksFragment.this.startActivity(browserIntent);
                }
        );
        textSmokeEffect.setOnClickListener(listener -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.healthline.com/health/smoking/effects-on-body"));
                    LinksFragment.this.startActivity(browserIntent);
                }
        );
        textImpactSmoke.setOnClickListener(listener -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Y18Vz51Nkos"));
                    LinksFragment.this.startActivity(browserIntent);
                }
        );
        textSmokeSafety.setOnClickListener(listener -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.medicalnewstoday.com/articles/vaping-vs-smoking#which-is-safer"));
                    LinksFragment.this.startActivity(browserIntent);
                }
        );
        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}