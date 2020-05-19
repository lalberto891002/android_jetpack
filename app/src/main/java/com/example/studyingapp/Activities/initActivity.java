package com.example.studyingapp.Activities;

import android.content.Intent;
import android.os.Bundle;
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

import Conexion.Conexion_firebase;

public class initActivity  extends AppCompatActivity{

    private MyAplication aplication;
    private boolean logued;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_activity);
        aplication = (MyAplication)getApplicationContext();
        logued = aplication.getEstadoLogg();
        if(logued) {
            FirebaseAuth auth = Conexion_firebase.getFirebaseAuth();
            if("".equals(aplication.getEmail())||"".equals(aplication.getPassword()))
                iniciaLoginActivity();
            else {
                auth.signInWithEmailAndPassword(aplication.getEmail(), aplication.getPassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())

                                    iniciaPerfilActivity();
                                else
                                    iniciaLoginActivity();

                            }
                        });
            }

        }
        else
        {
            Intent myIntent = new Intent(this,loginActivity.class);
            startActivity(myIntent);
            finish();
        }

    }

    private void iniciaLoginActivity()
    {
        Intent myIntent = new Intent(getApplicationContext(),loginActivity.class);
        startActivity(myIntent);
        finish();
    }

    private void iniciaPerfilActivity(){
        Intent myIntent = new Intent(getApplicationContext(), perfilActivity.class);
        startActivity(myIntent);
        finish();
    }
}
