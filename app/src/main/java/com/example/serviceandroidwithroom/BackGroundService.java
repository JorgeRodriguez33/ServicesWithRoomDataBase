package com.example.serviceandroidwithroom;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.serviceandroidwithroom.Model.PersonaModal;
import com.example.serviceandroidwithroom.Repository.PersonaRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BackGroundService extends Service {
    private PersonaRepository repository;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       repository = new PersonaRepository(BackGroundService.this.getApplication());

        final int[] contador = {0};
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            List<PersonaModal> list = null;
                            try {
                                list = repository.GetAllPersonasAsyncTask();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            for (PersonaModal c: list){
                               Log.e("BACKGROUND","--------- is running... " + contador[0]);
                           }

                            Log.e("Service","Service BACKGROUND!!! is running... " + contador[0]);
                            try{
                                contador[0] +=1;
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
