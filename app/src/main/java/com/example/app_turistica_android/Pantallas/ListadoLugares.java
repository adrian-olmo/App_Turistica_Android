package com.example.app_turistica_android.Pantallas;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_turistica_android.AdaptadorUbicaciones.AdaptadorFirebase;
import com.example.app_turistica_android.AdaptadorUbicaciones.ListadoLugaresHolder;
import com.example.app_turistica_android.R;
import com.example.app_turistica_android.utils.Lugares;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ListadoLugares extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_listado_lugares);
        //Toast.makeText(getApplicationContext(),"Cargado correctamente!",Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.listadolugares);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference("lugares").child("defecto");


        AdaptadorFirebase adaptadorFirebase = new AdaptadorFirebase(Lugares.class, R.layout.item_listado_lugares
        ,ListadoLugaresHolder.class, referencia,ListadoLugares.this);

        recyclerView.setAdapter(adaptadorFirebase);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,
                LinearLayoutManager.VERTICAL, false));
    }
}
