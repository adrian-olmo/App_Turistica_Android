package com.example.app_turistica_android.Pantallas;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_turistica_android.Mensajes.AdaptadorMensajes;
import com.example.app_turistica_android.Mensajes.Mensaje;
import com.example.app_turistica_android.Mensajes.MensajeEnviar;
import com.example.app_turistica_android.Mensajes.MensajeRecibir;
import com.example.app_turistica_android.R;
import com.example.app_turistica_android.utils.Usuarios;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat extends AppCompatActivity {

    private CircleImageView imagenperfil;
    private TextView nombreusuario;
    private RecyclerView listadomensajes;
    private EditText txtMensajes;
    private FloatingActionButton btnEnviar;

    private AdaptadorMensajes adaptadorMensajes;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private DatabaseReference myRef;

    Usuarios usuarios;
    String uid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        uid = getIntent().getExtras().getString("uid");
        Log.v("datos",uid);
        instancias();
        acciones();
        cargarDatos1();
    }

    private void acciones() {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.push().setValue(new MensajeEnviar(txtMensajes.getText().toString(), nombreusuario.getText().toString(),
                        "", "1", ServerValue.TIMESTAMP));

                txtMensajes.setText(" ");

                //adaptadorMensajes.agregarMensaje();
            }
        });

        adaptadorMensajes.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                scrollBar();
            }
        });

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MensajeRecibir mrec = dataSnapshot.getValue(MensajeRecibir.class);
                adaptadorMensajes.agregarMensaje(mrec);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void scrollBar(){ //El recycler siempre mostrara el ultimo elemento de la lista
        listadomensajes.scrollToPosition(adaptadorMensajes.getItemCount() -1);
    }
    private void instancias() {
        imagenperfil = findViewById(R.id.fotoperfil);
        nombreusuario = findViewById(R.id.nombrechat);

        listadomensajes = findViewById(R.id.mensajes);
        txtMensajes = findViewById(R.id.txtmensajes);
        btnEnviar = findViewById(R.id.btnEnviarMsg);

        adaptadorMensajes = new AdaptadorMensajes(this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        listadomensajes.setLayoutManager(lm);
        listadomensajes.setAdapter(adaptadorMensajes);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("chat");
        myRef = database.getReference().child("usuarios");

    }

    private void cargarDatos1() {
        //final String uid = getIntent().getExtras().getString("uid");
        final DatabaseReference nodoUsuarios = myRef.child(uid);
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

                    nombreusuario.setText(usuario.getValue().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error al cargar datos de usuario", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
