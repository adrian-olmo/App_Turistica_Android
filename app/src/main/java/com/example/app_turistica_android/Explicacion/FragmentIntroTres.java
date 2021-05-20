package com.example.app_turistica_android.Explicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.app_turistica_android.R;


public class FragmentIntroTres extends Fragment {


    public FragmentIntroTres() {
    }
    public static FragmentIntroTres newInstance(int sectionNumber) {
        FragmentIntroTres fragment = new FragmentIntroTres();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmenttres, container, false);
        return rootView;
    }
}
