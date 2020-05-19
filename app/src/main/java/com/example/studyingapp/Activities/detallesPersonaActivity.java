package com.example.studyingapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyingapp.R;

public class detallesPersonaActivity extends AppCompatActivity {
    private TextView nombreView;
    private TextView edadView;
    private TextView paisView;
    private TextView ciudadView;
    private TextView apellidoView;
    private TextView fotoView;
    private Button voltar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_persona_activty);
        cargarVistas();
        Intent intent = getIntent();
        if(intent.getExtras()!= null){
            String nombre = intent.getExtras().getString("nombre");
            String apellidos = intent.getExtras().getString("apellidos");
            String pais = intent.getExtras().getString("pais");
            String ciudad = intent.getExtras().getString("ciudad");
            int edad = intent.getExtras().getInt("edad");
            String url = intent.getExtras().getString("urlFoto");

            nombreView.setText(nombre);
            edadView.setText(edad+"");
            apellidoView.setText(apellidos);
            paisView.setText(pais);
            ciudadView.setText(ciudad);
            fotoView.setText(url);
        }

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void cargarVistas() {
        nombreView = findViewById(R.id.detalle_persona_nombre);
        edadView = findViewById(R.id.detalles_personas_edad);
        paisView = findViewById(R.id.detalles_personas_pais);
        ciudadView = findViewById(R.id.detalles_personas_ciudad);
        apellidoView = findViewById(R.id.detalles_personas_apellidos);
        fotoView = findViewById(R.id.detalles_persona_url_foto);
        voltar = findViewById(R.id.detalles_personas_btn_voltar);
    }
}
