package com.example.app_turistica_android.Explicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.app_turistica_android.LogIn;
import com.example.app_turistica_android.Maps.MapsActivity;
import com.example.app_turistica_android.R;

import java.util.ArrayList;

public class OnBoardActivity extends AppCompatActivity implements View.OnClickListener{
    private AdaptadorViewPager mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ImageView circulo1, circulo2, circulo3, circulo4;
    private ImageView[] circulos;
    private Button btnFinish, btnSkip;
    private ImageButton btnNext;
    private ArgbEvaluator evaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        final int color1 = ContextCompat.getColor(this, android.R.color.holo_red_light);
        final int color2 = ContextCompat.getColor(this, android.R.color.holo_purple);
        final int color3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        final int color4 = ContextCompat.getColor(this, android.R.color.holo_green_light);

        final int[] colorList = new int[]{color1, color2, color3, color4};

        circulo1 = findViewById(R.id.indicator_0);
        circulo2 = findViewById(R.id.indicator_1);
        circulo3 = findViewById(R.id.indicator_2);
        circulo4 = findViewById(R.id.indicator_3);

        btnFinish = findViewById(R.id.btn_finish);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);

        circulos = new ImageView[]{circulo1, circulo2, circulo3, circulo4};

        ArrayList listaFg = new ArrayList();
        listaFg.add(new FragmentIntroUno());
        listaFg.add(new FragmentIntroDos());
        listaFg.add(new FragmentIntroTres());
        listaFg.add(new FragmentIntroCuatro());
        mSectionsPagerAdapter = new AdaptadorViewPager(getSupportFragmentManager(), listaFg);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int colorUpdate = (Integer) evaluator.evaluate(positionOffset, colorList[position], colorList[position == 3 ? position : position + 1]);
                mViewPager.setBackgroundColor(colorUpdate);
            }

            @Override
            public void onPageSelected(int position) {
                updateDots(position);
                if (position < 3) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnFinish.setVisibility(View.INVISIBLE);
                } else if (position == 3) {
                    btnNext.setVisibility(View.INVISIBLE);
                    btnFinish.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnNext.setOnClickListener(this);
        btnSkip.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
    }

    public void updateDots(int pos) {
        for (ImageView imagenCirculo : circulos) {
            imagenCirculo.setBackgroundResource(R.drawable.circulo_deseleccionado);
        }
        circulos[pos].setBackgroundResource(R.drawable.circulo_seleccionado);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                break;
            case R.id.btn_skip:
                mViewPager.setCurrentItem(mSectionsPagerAdapter.getCount());
                break;
            case R.id.btn_finish:
                finish();
                /*FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //agrega el Fragment en el contenedor, en este caso el FrameLayout con id `FrameLayout`.
                ft.add(R.id.map, new MapsActivity(),);
                ft.commit();*/
                Intent intent = new Intent(OnBoardActivity.this, LogIn.class);
                startActivity(intent);
                break;
        }
    }
}
