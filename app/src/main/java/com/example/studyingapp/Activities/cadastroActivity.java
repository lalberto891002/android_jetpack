package com.example.studyingapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyingapp.MyAplication;
import com.example.studyingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import Conexion.Conexion_firebase;

public class cadastroActivity  extends AppCompatActivity {

    private MyAplication aplication;
    private boolean logued;
    private FirebaseAuth auth;
    private String senha;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Button registrar = findViewById(R.id.cadastro_btn_registrar);
        final TextView emailView = findViewById(R.id.cadastro_email);
        final TextView senhaView = findViewById(R.id.cadastro_senha);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= emailView.getText().toString();
                senha= senhaView.getText().toString();
                createUSer(email,senha);
            }
        });

    }

    private void createUSer(String email, final String senha){
        auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(cadastroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Alert("SUCESO");
                            Intent intent = new Intent(getApplicationContext(),perfilActivity.class);
                            intent.putExtra("senha_extra",senha);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Alert("Error de cadastro");
                        }
                    }
                });
    }

    private void Alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexion_firebase.getFirebaseAuth();
    }
}
