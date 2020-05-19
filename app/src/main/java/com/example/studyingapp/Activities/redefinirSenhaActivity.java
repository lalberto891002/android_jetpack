package com.example.studyingapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import Conexion.Conexion_firebase;

public class redefinirSenhaActivity extends AppCompatActivity {

    private Button resetearSenha;
    private EditText emailView;
    private FirebaseAuth auth;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redefinir_senha_activity);
        cargarVistas();
        addClickListeners();
    }

    private void cargarVistas(){
        resetearSenha = findViewById(R.id.resetar_btn_resetar);
        emailView = findViewById(R.id.resetar_email);
    }

    private void addClickListeners(){
        resetearSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailView.getText().toString().trim();
                resetSenha(email);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexion_firebase.getFirebaseAuth();
    }

    private void resetSenha(String email){
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Alert("Se envio um emial para redefinir senha");

                        }
                        else
                            Alert("Nao achamos esse usuario no sistema");

                        finish();
                    }
                });
    }

    private void Alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
}
