package com.example.app_turistica_android;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_turistica_android.Explicacion.OnBoardActivity;
import com.example.app_turistica_android.utils.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EditPerfil extends AppCompatActivity {
    TextView txtCorreo;
    EditText txtUsuario, txtPassword, txtConfirPassword;
    Button btnGuardar;
    private static final String TAG = "MainActivity";
    //private FirebaseAuth firebaseAuth;
    //private FirebaseDatabase database;
    //DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editperfil);
        instancias();
        acciones();
        cargarDatos1();

    }

    private void instancias() {
        txtCorreo = findViewById(R.id.txtNombre);
        txtUsuario = findViewById(R.id.editText5);
        txtPassword = findViewById(R.id.editText7);
        txtConfirPassword = findViewById(R.id.editext8);
        btnGuardar = findViewById(R.id.btnGuardar);
        myRef = FirebaseDatabase.getInstance().getReference();

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

                    txtPassword.setText(pass.getValue().toString());
                    txtCorreo.setText(correo.getValue().toString());
                    txtUsuario.setText(usuario.getValue().toString());



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error al cargar datos de usuario", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void actDatos() {
        final String uid = getIntent().getExtras().getString("uid");
        final DatabaseReference actualizar = myRef.getDatabase().getReference().child("usuarios").child(uid);


        actualizar.child("usuario").setValue(txtUsuario.getText().toString());
        actualizar.child("contrasenia").setValue(txtPassword.getText().toString());
    }



    private void acciones() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                actDatos();
                Toast.makeText(getApplicationContext(),"Actualizado correctamente!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}

