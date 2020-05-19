package com.example.studyingapp.Activities;

import android.app.AppOpsManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyingapp.R;

public class novaPersonaActivty extends AppCompatActivity {

    public static String NOVA_PERSONA_NOMBRE = "nombrePersona";
    public static String NOVA_PERSONA_EDAD = "edadPersona";
    public static String NOVA_PERSONA_CIUDAD = "ciudadPersona";
    public static String NOVA_PERSONA_PAIS = "paisPersona";
    private EditText NombreView;
    private EditText EdadView;
    private EditText CiuadView;
    private EditText PaisView;
    private Button CancelarPersonaNova;
    private  Button CrearPersona;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_persona_activty);

        cargarVistas();
        clickListeners();

    }

    private void cargarVistas(){
        NombreView = findViewById(R.id.nova_persona_edit_nombre);
        EdadView = findViewById(R.id.nova_persona_edit_edad);
        CiuadView = findViewById(R.id.nova_persona_edit_ciudad);
        PaisView = findViewById(R.id.nova_persona_edit_pais);
        CancelarPersonaNova = findViewById(R.id.nova_persona_btn_cancelar);
        CrearPersona = findViewById(R.id.nova_persona_btn_crear);

    }

    private void clickListeners(){
        CancelarPersonaNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        CrearPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //@todo
                String nombre = NombreView.getText().toString().trim();
                String edad = EdadView.getText().toString().trim();
                String ciudad = CiuadView.getText().toString().trim();
                String pais = PaisView.getText().toString().trim();
                Intent data = new Intent();
                data.putExtra(NOVA_PERSONA_NOMBRE,nombre);
                data.putExtra(NOVA_PERSONA_EDAD,edad);
                data.putExtra(NOVA_PERSONA_CIUDAD,ciudad);
                data.putExtra(NOVA_PERSONA_PAIS,pais);

                setResult(RESULT_OK,data);
                finish();


            }
        });

    }
}
