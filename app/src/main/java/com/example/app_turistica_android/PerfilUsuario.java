package com.example.app_turistica_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_turistica_android.Maps.MapsActivity;
import com.example.app_turistica_android.Pantallas.ListadoFavoritos;
import com.example.app_turistica_android.Pantallas.ListadoLugares;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class PerfilUsuario extends AppCompatActivity {

    LinearLayout favoritos, ayudaCuenta, editarPerfil,cerrarSesion;
    FloatingActionButton volver;
    TextView txtUsuario;
    FirebaseAuth mAuth;
    DatabaseReference myRef;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilusuario);
        uid = getIntent().getExtras().getString("uid");
        instancias();
        acciones();
        cargarDatos1();

    }

    private void instancias() {
        txtUsuario = findViewById(R.id.txtUsuario);
        favoritos = findViewById(R.id.btnFavoritos);
        ayudaCuenta = findViewById(R.id.btnAyuda);
        editarPerfil = findViewById(R.id.btnEditarPerfil);
        volver = findViewById(R.id.btnvolver);
        cerrarSesion = findViewById(R.id.btnCerrarSesion);
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
    }

    private void acciones() {

        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFavoritos = new Intent(PerfilUsuario.this, ListadoFavoritos.class);
                startActivity(intentFavoritos);
                //Toast.makeText(PerfilUsuario.this,"Permite al usuario ver sus rutas favoritas", Toast.LENGTH_SHORT).show();
            }
        });

        ayudaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilUsuario.this, Ayuda.class);
                startActivity(intent);
            }
        });

        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilUsuario.this, EditPerfil.class);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(PerfilUsuario.this, LogIn.class));
                finish();
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void cargarDatos1() {
        final String uid = getIntent().getExtras().getString("uid");
        final DatabaseReference nodoUsuarios = myRef.getDatabase().getReference().child("usuarios").child(uid);
        //Log.v("prueba", uid);

        nodoUsuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getKey().equals(uid)) {
                    Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = iterable.iterator();

                    DataSnapshot pass = iterator.next();
                    DataSnapshot correo = iterator.next();
                    DataSnapshot usuario = iterator.next();

                    txtUsuario.setText(usuario.getValue().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error al cargar datos de usuario", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
