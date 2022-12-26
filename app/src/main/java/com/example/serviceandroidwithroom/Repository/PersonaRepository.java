package com.example.serviceandroidwithroom.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.serviceandroidwithroom.Dao.Dao;
import com.example.serviceandroidwithroom.DataBase.PersonaDatabase;
import com.example.serviceandroidwithroom.Model.PersonaModal;

import java.util.List;
import java.util.concurrent.ExecutionException;

//Clase encargada de la gestión de las operaciones con la base de datos
public class PersonaRepository {

    private Dao dao;

    public PersonaRepository(Application application) {
        PersonaDatabase database = PersonaDatabase.getInstance(application);
        dao = database.Dao();
    }

    //Método para insertar una persona en nuestra base de datos.
    public void insert(PersonaModal model) {
        new InsertPersonaAsyncTask(dao).execute(model);
    }
    public List<PersonaModal> GetAllPersonasAsyncTask() throws ExecutionException, InterruptedException {
        return new GetAllPersonasAsyncTask(dao).execute().get();
    }

    // Método para actualizar los datos en nuestra base de datos de una persona.
    public void update(PersonaModal model) {
        new UpdatePersonaAsyncTask(dao).execute(model);
    }

    // Método para eliminar una persona de nuestra base de datos de una persona.
    public void delete(PersonaModal model) {
        new DeletePersonaAsyncTask(dao).execute(model);
    }

    // Método para eliminar todas las persona de nuestra base de datos de una persona.
    public void deleteAllPersonas() {
        new DeleteAllPersonasAsyncTask(dao).execute();
    }

    private static class InsertPersonaAsyncTask extends AsyncTask<PersonaModal, Void, Void> {
        private Dao dao;

        private InsertPersonaAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PersonaModal... model) {
            // se inserta la persona...
            dao.insert(model[0]);
            return null;
        }
    }

    // Método para actualizar la persona de forma asincrónica
    private static class UpdatePersonaAsyncTask extends AsyncTask<PersonaModal, Void, Void> {
        private Dao dao;

        private UpdatePersonaAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PersonaModal... models) {
            dao.update(models[0]);
            return null;
        }
    }

    private static class DeletePersonaAsyncTask extends AsyncTask<PersonaModal, Void, Void> {
        private Dao dao;

        private DeletePersonaAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PersonaModal... models) {
            dao.delete(models[0]);
            return null;
        }
    }

    private static class DeleteAllPersonasAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllPersonasAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllPeople();
            return null;
        }
    }

    private static class GetAllPersonasAsyncTask extends AsyncTask<Void, Void, List<PersonaModal>> {
        private Dao dao;

        private GetAllPersonasAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected List<PersonaModal> doInBackground(Void... voids) {
            return  dao.getPersonasModalList();
        }

    }

}

