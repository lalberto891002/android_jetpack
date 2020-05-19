package com.example.studyingapp.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonaDAO {
    @Insert
    void insert(Persona persona);

    @Update
    void update(Persona persona);

    @Delete
    void delete(Persona pErsona);

    @Query("DELETE FROM person_table")
    void deleteAllPeoples();

    @Query("SELECT * FROM person_table ORDER BY Nombre DESC")
    LiveData<List<Persona>> getAllPeoples();


}
