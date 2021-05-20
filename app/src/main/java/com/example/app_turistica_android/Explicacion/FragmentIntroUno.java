package com.example.app_turistica_android.Explicacion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_turistica_android.R;


public class FragmentIntroUno extends Fragment {


    public FragmentIntroUno() {
    }
    public static FragmentIntroUno newInstance(int sectionNumber) {
        FragmentIntroUno fragment = new FragmentIntroUno();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentuno, container, false);
        return rootView;
    }
}
