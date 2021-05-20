package com.example.app_turistica_android.Explicacion;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdaptadorViewPager extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public AdaptadorViewPager(FragmentManager fm, ArrayList<Fragment> listaFg) {
        super(fm);
        fragments = listaFg;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
