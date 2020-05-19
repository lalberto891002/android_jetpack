package com.example.studyingapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_table")
public class Persona {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    private String Nombre;

    private String Apellidos;

    private String Pais;

    private String Ciudad;

    private String UrlFoto;

    private int Edad;

    public Persona(){

    }
    public Persona(String nombre, String apellidos, String pais, String ciudad, String urlFoto, int edad) {
        Nombre = nombre;
        Apellidos = apellidos;
        Pais = pais;
        Ciudad = ciudad;
        UrlFoto = urlFoto;
        Edad = edad;
    }

    public Persona(int ID, String nombre, String apellidos, String pais, String ciudad, String urlFoto, int edad) {
        this.ID = ID;
        Nombre = nombre;
        Apellidos = apellidos;
        Pais = pais;
        Ciudad = ciudad;
        UrlFoto = urlFoto;
        Edad = edad;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getPais() {
        return Pais;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public String getUrlFoto() {
        return UrlFoto;
    }

    public int getEdad() {
        return Edad;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public void setUrlFoto(String urlFoto) {
        UrlFoto = urlFoto;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }
}
