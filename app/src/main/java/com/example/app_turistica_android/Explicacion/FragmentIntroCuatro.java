package com.example.app_turistica_android.Explicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.app_turistica_android.R;


public class FragmentIntroCuatro extends Fragment {


    public FragmentIntroCuatro() {
    }
    public static FragmentIntroCuatro newInstance(int sectionNumber) {
        FragmentIntroCuatro fragment = new FragmentIntroCuatro();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentcuatro, container, false);
        return rootView;
    }
}
