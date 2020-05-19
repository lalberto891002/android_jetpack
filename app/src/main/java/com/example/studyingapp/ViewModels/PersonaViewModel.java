package com.example.studyingapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.studyingapp.Model.PersonRepository;
import com.example.studyingapp.Model.Persona;
import com.example.studyingapp.MyAplication;

import java.util.List;

public class PersonaViewModel extends AndroidViewModel {

    private PersonRepository personRepository;
    private LiveData<List<Persona>>personas;
    public PersonaViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository((MyAplication) application);
        personas =personRepository.getAllPersons();

    }

    public void insert(Persona persona){
        personRepository.insert(persona);
    }

    public void update(Persona persona){
        personRepository.update(persona);
    }

    public void delete(Persona persona){
        personRepository.delete(persona);
    }

    public void deleteAll(){
        personRepository.deleteAllPersons();
    }

    public LiveData<List<Persona>> getAllPersons(){
        return personas;
    }

}
