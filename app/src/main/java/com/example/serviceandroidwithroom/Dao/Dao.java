package com.example.serviceandroidwithroom.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.serviceandroidwithroom.Model.PersonaModal;

import java.util.List;

//Anotacion necesaria para marcar la clase como una "clase Dao"
@androidx.room.Dao
public interface Dao {

    //Operacion para insertar un nuevo curso en base de datos
    @Insert
    void insert(PersonaModal model);

    //Operacion para actualizar un curso en base de datos
    @Update
    void update(PersonaModal model);

    //Operacion para eliminar un curso de base de datos
    @Delete
    void delete(PersonaModal model);

    //Operacion para eliminar todos los cursos existentes en base de datos
    //Para las anotaciones Query se debe definir de forma manual la consulta, la tabla se definio en la clase del Model como "persona_table"
    @Query("DELETE FROM persona_table")
    void deleteAllPeople();

    //Operacion para devolver todas las personas existentes en base de datos
    @Query("SELECT * FROM persona_table")
    List<PersonaModal> getPersonasModalList();

}

