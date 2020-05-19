package com.example.studyingapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyingapp.MyAplication;
import com.example.studyingapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import Conexion.Conexion_firebase;

public class perfilActivity extends AppCompatActivity {
   private FirebaseUser user;
   private FirebaseAuth firebaseAuth;
   private TextView email;
   private TextView ID;
   private String senha;
   private MyAplication myAplication;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        if(this.getIntent().getExtras()!=null)
            senha = getIntent().getExtras().getString("senha_extra");
        else
            senha = "";
        Button logout = findViewById(R.id.perfil_btn_logout);
        email = findViewById(R.id.perfil_email);
        ID = findViewById(R.id.perfil_senha);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexion_firebase.LogOut();
                myAplication.salvaLogueadoDeslogueado(false);
                Intent intent = new Intent(getApplicationContext(),initActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button irPersonas = findViewById(R.id.perfil_btn_personas);
        irPersonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),personasActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = Conexion_firebase.getFirebaseAuth();
        user = Conexion_firebase.getFirebaseUser();
        verifyUser();
        myAplication = (MyAplication)getApplicationContext();
        myAplication.salvaLogueadoDeslogueado(true);

    }

    private void verifyUser(){
        if(user!=null)
        {
            email.setText("EmAIL: "+user.getEmail());
            ID.setText("ID: "+user.getUid());

            user.getIdToken(true).addOnSuccessListener(this, new OnSuccessListener<GetTokenResult>() {
                @Override
                public void onSuccess(GetTokenResult getTokenResult) {
                    myAplication.salvaUid(getTokenResult.getToken());
                    myAplication.salvaEmail(user.getEmail());
                    if(!"".equals(senha))
                        myAplication.salvaPassword(senha);
                }
            });

        }
    }
}
