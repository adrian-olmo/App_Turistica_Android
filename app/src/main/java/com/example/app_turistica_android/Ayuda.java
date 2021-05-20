package com.example.app_turistica_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageButton;

public class Ayuda extends AppCompatActivity {
    GifImageButton btnGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        instancias();
        acciones();
    }

    private void acciones() {
        btnGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Sin actualizaciones pendientes, gracias!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void instancias() {
        btnGif= findViewById(R.id.btnGif);
    }
}
