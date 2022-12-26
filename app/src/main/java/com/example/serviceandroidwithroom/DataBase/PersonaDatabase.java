package com.example.serviceandroidwithroom.DataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.serviceandroidwithroom.Dao.Dao;
import com.example.serviceandroidwithroom.Model.PersonaModal;

// Se define la clase que definirá la base de datos
//en las anotaciones se define el nombre que tendrá la base de datos y la versión que corresponda
//a futuro si se realiza un cambio por ejemplo en los models se deberá elevar el numero de versión para indicarle a la base de datos
//que se realizo un cambio
@Database(entities = {PersonaModal.class}, version = 1)
public abstract class PersonaDatabase extends RoomDatabase {

    private static PersonaDatabase instance;

    public abstract Dao Dao();

    public static synchronized PersonaDatabase getInstance(Context context) {
        if (instance == null) {
            instance =
                    //Se genera la base de datps
                    Room.databaseBuilder(context.getApplicationContext(),
                            PersonaDatabase.class, "personas_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(PersonaDatabase instance) {
            Dao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
