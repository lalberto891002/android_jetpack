package com.example.studyingapp.Model;

import android.app.Application;
import android.app.Person;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.studyingapp.MyAplication;

import java.util.List;

public class PersonRepository {
    private PersonaDAO personaDAO;
    private LiveData<List<Persona>>allPersons;

    public PersonRepository(MyAplication application){
        PersonDataBase dataBase = application.getDataBase();
        personaDAO = dataBase.personaDAO();
        allPersons = personaDAO.getAllPeoples();
    }

    public void insert(Persona persona){
        new InsertPersonaAsynctask(personaDAO).execute(persona);
    }

    public void update(Persona persona){
        new UpdatePersonaAsynctask(personaDAO).execute(persona);
    }

    public void delete(Persona persona){
        new DeletePersonaAsynctask(personaDAO).execute(persona);
    }
    public void deleteAllPersons(){
        new DeleteAllPersonsAsynctask(personaDAO).execute();
    }

    public LiveData<List<Persona>>getAllPersons(){
        return allPersons;
    }

    private static class InsertPersonaAsynctask extends AsyncTask<Persona, Void,Void>{

        private PersonaDAO personaDAO;

        private InsertPersonaAsynctask(PersonaDAO personaDAO){
            this.personaDAO = personaDAO;
        }

        @Override
        protected Void doInBackground(Persona... personas) {
            personaDAO.insert(personas[0]);
            return null;
        }
    }

    private static class UpdatePersonaAsynctask extends AsyncTask<Persona, Void,Void>{

        private PersonaDAO personaDAO;

        private UpdatePersonaAsynctask(PersonaDAO personaDAO){
            this.personaDAO = personaDAO;
        }

        @Override
        protected Void doInBackground(Persona... personas) {
            personaDAO.update(personas[0]);
            return null;
        }
    }

    private static class DeletePersonaAsynctask extends AsyncTask<Persona, Void,Void>{

        private PersonaDAO personaDAO;

        private DeletePersonaAsynctask(PersonaDAO personaDAO){
            this.personaDAO = personaDAO;
        }

        @Override
        protected Void doInBackground(Persona... personas) {
            personaDAO.delete(personas[0]);
            return null;
        }
    }

    private static class DeleteAllPersonsAsynctask extends AsyncTask<Void, Void,Void>{

        private PersonaDAO personaDAO;

        private DeleteAllPersonsAsynctask(PersonaDAO personaDAO){
            this.personaDAO = personaDAO;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            personaDAO.deleteAllPeoples();
            return null;
        }
    }




}
