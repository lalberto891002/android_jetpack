package com.example.studyingapp.Model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Persona.class},version = 1)
public abstract class PersonDataBase extends RoomDatabase {
    private static String DATABASE_NAME = "persona_db";
    private static PersonDataBase instance;

    public abstract PersonaDAO personaDAO();

    public static synchronized PersonDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),PersonDataBase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataBaseAsyncTask(instance).execute();
        }
    };

    private static class PopulateDataBaseAsyncTask extends AsyncTask<Void,Void,Void>
    {

        private PersonaDAO personaDAO;

        private PopulateDataBaseAsyncTask(PersonDataBase dataBase){
            this.personaDAO = dataBase.personaDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            personaDAO.insert(new Persona("Luis ALberto","Calvo Diaz","Brasil","Aracatuba","www.fotoluis.com",30));
            personaDAO.insert(new Persona("Mabel","Cruz Rodriguez","Brasil","Aracatuba","www.urlfotomabel.com",28));
            personaDAO.insert(new Persona("Felipe","Perez Perez","Cuba","La Habana","www.fotofelipe.com",45));
            return null;
        }
    }
}
