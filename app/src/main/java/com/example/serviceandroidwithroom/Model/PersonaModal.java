package com.example.serviceandroidwithroom.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Room es un ORM
//se generan las tablas de base de datos a partir de las clases que representan un modelo
//en este caso una persona y se le define el nombre de la tabla
@Entity(tableName = "persona_table")
public class PersonaModal {

    //con esta anotación se define que el atributo debajo sera autoincremental
    @PrimaryKey(autoGenerate = true)
    private int id;

    //Primer Nombre de la persona
    private String primerNombre;

    //Primer Nombre de la persona
    private String segundoNombre;

    //Primer Nombre de la persona
    private String primerApellido;

    //Primer Nombre de la persona
    private String segundoApellido;

    //Descripcion de la persona
    private String descripcion;

    //para el constructor no se pasa un id porque ese atributo es autoincremental (lo definimos con la anotación arriba)
    public PersonaModal(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String descripcion) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.descripcion = descripcion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
