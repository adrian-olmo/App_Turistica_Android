package com.example.app_turistica_android;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_turistica_android.utils.Lugares;

public class DetalleLugar extends AppCompatActivity {

    ImageView imagen;
    TextView txtDescripcion, txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lugar);
        instancias();
        recuperarDatos();
    }

    private void recuperarDatos() {
        Lugares lRecuperado = null;
        //lRecuperado = (Lugares) getIntent().getExtras().get(CochesActivityRecycler.TAG1); llamar al AppCompatActivity de LugaresActivityRecycler (Sin crear)
        //cRecuperado = getIntent().getExtras().get(CochesActivityRecycler.TAG1);//Error coger TAG
        //imagen.setImageResource(lRecuperado.getImagen());
        txtNombre.setText(lRecuperado.getNombre());
        //txtDescripcion.setText(lRecuperado.getDescripcion());
        txtDescripcion.setMovementMethod(new ScrollingMovementMethod());

    }

    private void instancias() {
        imagen = findViewById(R.id.imgCambio);
        txtNombre = findViewById(R.id.txtNombre);
        txtDescripcion = findViewById(R.id.txtInfo);


    }
}
