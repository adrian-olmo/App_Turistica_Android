package com.example.app_turistica_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_turistica_android.Explicacion.OnBoardActivity;
import com.example.app_turistica_android.utils.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    EditText txtNombreUsuario, txtCorreo, txtPassword, txtConfirPassword;
    Button btnRegsitrarse;
    private static final String TAG = "MainActivity";
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro1);
        instancias();
        acciones();
        registarUsuarios();
    }

    private void instancias() {
        txtCorreo = findViewById(R.id.txtEmailRegistro1);
        txtNombreUsuario = findViewById(R.id.txtUsuarioRegistro1);
        txtPassword = findViewById(R.id.txtPasswordRegistro1);
        txtConfirPassword = findViewById(R.id.txtPasswordConfRegistro1);
        btnRegsitrarse = findViewById(R.id.botonRegistro1);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
    }

    private void registarUsuarios() {
        String email = txtCorreo.getText().toString().trim();
        String contrasenia = txtPassword.getText().toString().trim();
        String confirContra = txtConfirPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(contrasenia)) {
            Toast.makeText(this, "Se debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        /*if(contrasenia != confirContra){
            Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
            //validar contraseña repetidas ERROR
        }*/

        //progressDialog.setMessage("Realizando registro en linea...");
        //progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, contrasenia)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");

                            Toast.makeText(Registro.this, "Se ha registrado el usuario con el email: " + txtCorreo.getText().toString(), Toast.LENGTH_LONG).show();

                            String correo = txtCorreo.getText().toString();
                            String usuario = txtNombreUsuario.getText().toString();
                            String passwd = txtPassword.getText().toString();

                            Map<String, Object> map = new HashMap<>();
                            map.put("correo",correo);
                            map.put("usuario",usuario);
                            map.put("contrasenia",passwd);
                            String uid = firebaseAuth.getCurrentUser().getUid();

                            //FirebaseUser userFirebase = firebaseAuth.getCurrentUser();
                            //userFirebase.getUid();

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            System.out.println(uid);
                            DatabaseReference myRef = database.getReference("usuarios").child(uid);
                            myRef.setValue(map);



                            /*DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("usuarios");
                            // creo un objeto de tipo usuario;
                            String correo = txtCorreo.getText().toString();
                            String usuario = txtNombreUsuario.getText().toString();
                            String passwd = txtPassword.getText().toString();
                            Usuarios u = new Usuarios(correo,usuario,passwd);
                            database.setValue("aaaaaaaaaa");*/



                        } else {
                            Toast.makeText(Registro.this, "No se ha registrado el usuario", Toast.LENGTH_LONG).show();
                            System.out.println("NO HAS ENTRADOOOOOOO");
                        }
                        //progressDialog.dismiss();
                    }
                });
    }

    private void acciones() {
        btnRegsitrarse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validarEmail(txtCorreo) == true){
                    registarUsuarios();
                    //vaciarCampos();
                    Intent intent = new Intent(Registro.this, OnBoardActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        });

    }

    public void vaciarCampos() {
        txtCorreo.setText("");
        txtPassword.setText("");
        txtConfirPassword.setText("");
        txtNombreUsuario.setText("");

    }

   private boolean validarEmail(EditText email) {

       final String emailInput = txtCorreo.getText().toString().trim();
       String passwordInput = txtPassword.getText().toString();

       //Comprueba las variantes de email

       if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){

           if(TextUtils.isEmpty(passwordInput)){
               txtPassword.setError("Introduce contraseña");
           } else {
               return true;
           }
       } else if (!TextUtils.isEmpty(emailInput)){
           txtCorreo.setError("Email no valido");
       }else if (TextUtils.isEmpty((emailInput))){
           txtCorreo.setError("Introduce un email");
       }

       return false;
   }


}


