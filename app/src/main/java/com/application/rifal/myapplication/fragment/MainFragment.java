package com.application.rifal.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.application.rifal.myapplication.R;
import com.application.rifal.myapplication.activity.ListViewActivity;
//import com.application.rifal.myapplication.activity.ListViewActivity;

public class MainFragment extends Fragment {
//Button bt;
    ImageView btn1,btnEntertainment,btnScience,btnSports,btnHealth,btnTech;
    public MainFragment() {
        // Required empty public constructor

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn1 = view.findViewById(R.id.btn_main);
        btnEntertainment = view.findViewById(R.id.btn_entertainment);
        btnScience = view.findViewById(R.id.btn_science);
        btnSports = view.findViewById(R.id.btn_sports);
        btnHealth = view.findViewById(R.id.btn_health);
        btnTech = view.findViewById(R.id.btn_technology);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListViewActivity.class);
                intent.putExtra("cat","business");
                startActivity(intent);
            }
        });

        btnEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListViewActivity.class);
                intent.putExtra("cat","entertainment");
                startActivity(intent);
            }
        });


        btnScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListViewActivity.class);
                intent.putExtra("cat","science");
                startActivity(intent);
            }
        });

        btnSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListViewActivity.class);
                intent.putExtra("cat","sports");
                startActivity(intent);
            }
        });

        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListViewActivity.class);
                intent.putExtra("cat","health");
                startActivity(intent);
            }
        });

        btnTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListViewActivity.class);
                intent.putExtra("cat","technology");
                startActivity(intent);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);

    }
}
