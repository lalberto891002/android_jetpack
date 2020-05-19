package com.example.studyingapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studyingapp.MyAplication;
import com.example.studyingapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import Conexion.Conexion_firebase;

public class loginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView emailView;
    private TextView senhaVIew;
    private TextView mudarSenha;
    private Button registrar;
    private Button Loguear;
    private com.google.android.gms.common.SignInButton singButton;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        cargarVistas();
        addClickListeners();
        googleSingInInicialization();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = Conexion_firebase.getFirebaseAuth();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

    }

    private void login(String email, final String senha){
        firebaseAuth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent = new Intent(getApplicationContext(),perfilActivity.class);
                            intent.putExtra("senha_extra",senha);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Alert("Email o senha incorrecto");
                        }
                    }
                });
    }

    private void Alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
    private void cargarVistas(){
        registrar= findViewById(R.id.Login_btn_newUser);
        emailView = findViewById(R.id.login_email);
        senhaVIew = findViewById(R.id.login_senha);
        mudarSenha = findViewById(R.id.login_mudar_senha);
        Loguear = findViewById(R.id.Login_btn_loguear);
    }

    private void addClickListeners()
    {
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),cadastroActivity.class);
                startActivity(intent);
                finish();

            }
        });

        Loguear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailView.getText().toString().trim();
                String senha = senhaVIew.getText().toString().trim();
                login(email,senha);
            }
        });

        mudarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),redefinirSenhaActivity.class);
                startActivity(intent);
            }
        });
        singButton = findViewById(R.id.sign_in_button);
        singButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSingIn();
            }
        });
    }

    private void googleSingInInicialization(){
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }

    private void googleSingIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            MyAplication app = (MyAplication)getApplication();
            app.salvaEmail(account.getEmail());//podria intentar hacer un registro a firebase
            app.salvaPassword("default");
            Intent intent = new Intent(getApplicationContext(),perfilActivity.class);
            startActivity(intent);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Alert("Error autenticando");
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
}
