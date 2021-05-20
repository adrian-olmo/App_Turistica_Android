package com.example.app_turistica_android.Explicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.app_turistica_android.R;


public class FragmentIntroDos extends Fragment {


    public FragmentIntroDos() {
    }
    public static FragmentIntroDos newInstance(int sectionNumber) {
        FragmentIntroDos fragment = new FragmentIntroDos();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentdos, container, false);
        return rootView;
    }
}
