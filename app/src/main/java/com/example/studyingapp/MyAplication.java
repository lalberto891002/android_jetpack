package com.example.studyingapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.studyingapp.Model.PersonDataBase;

public class MyAplication extends Application {

    private MyAplication myApp;
    public static String ESTADO_LOG ="LogueadoDeslogueado";
    public static String UID_USER = "UserUID";//token para las apis
    public static String NOME_ARQUIVO_LOG = "loggued";
    public static String EMAIL_USUARIO = "Email_usuario";
    public static String SENHA_USUARIO = "Senha_usuario";
    private Boolean estadoLogg;
    private String Uid;
    private String Email;
    private String Password;
    private PersonDataBase dataBase;
    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;

        SharedPreferences preferences = getSharedPreferences(NOME_ARQUIVO_LOG, Context.MODE_PRIVATE);
        estadoLogg = preferences.getBoolean(ESTADO_LOG,false);
        Uid = preferences.getString(UID_USER,"");
        Email = preferences.getString(EMAIL_USUARIO,"");
        Password = preferences.getString(SENHA_USUARIO,"");
        inicializaDB();

    }

    public Boolean getEstadoLogg() {
        return estadoLogg;
    }

    public void salvaLogueadoDeslogueado(boolean logueado){
        this.estadoLogg = logueado;
        SharedPreferences preferences = getSharedPreferences(NOME_ARQUIVO_LOG,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(ESTADO_LOG,this.estadoLogg);
        editor.apply();
    }

    public void salvaUid(String Uid)
    {
        this.Uid = Uid;
        SharedPreferences preferences = getSharedPreferences(NOME_ARQUIVO_LOG,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UID_USER,this.Uid);
        editor.apply();
    }

    public void salvaEmail(String email)
    {
        this.Email = email;
        SharedPreferences preferences = getSharedPreferences(NOME_ARQUIVO_LOG,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EMAIL_USUARIO,Email);
        editor.apply();
    }

    public void salvaPassword(String pass)
    {
        this.Password = pass;
        SharedPreferences preferences = getSharedPreferences(NOME_ARQUIVO_LOG,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SENHA_USUARIO,Password);
        editor.apply();
    }

    public String getUid()
    {
        return Uid;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    private void inicializaDB()
    {
        //inicializar db
        dataBase = PersonDataBase.getInstance(this);
    }


    public PersonDataBase getDataBase() {
        return dataBase;
    }

}
