package com.example.serviceandroidwithroom;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.serviceandroidwithroom.Model.PersonaModal;
import com.example.serviceandroidwithroom.Repository.PersonaRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ForegroundService extends Service {
    //Servicio corre aunque la app esta cerrada
    private PersonaRepository repository;

    private static final int NOTIF_ID = 3333;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        repository = new PersonaRepository(ForegroundService.this.getApplication());
        final int[] cantPersonasAgregadas = {0};
        final int[] contador = {0};
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            List<PersonaModal> list = null;
                            try {
                                list = repository.GetAllPersonasAsyncTask();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            cantPersonasAgregadas[0] = list.size();

                            Notification notification = getMyActivityNotification("Cantidad de Personas: " + cantPersonasAgregadas[0]);
                            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            mNotificationManager.notify(NOTIF_ID, notification);

                            for (PersonaModal c : list) {
                                Log.e("ROOM + FOREGROUND", c.getPrimerNombre() + " - " + c.getSegundoApellido());
                            }

                            try {
                                if (list.size() > 0)
                                    repository.delete(list.get(0));

                                //En la notificación la cantidad de personas se restara en 1

                                //Se eliminara una persona cada 4 segundos
                                Thread.sleep(4000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();

        startForeground(
                NOTIF_ID,
                getMyActivityNotification("Cantidad de Personas: " + cantPersonasAgregadas[0])
        );

        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Notification getMyActivityNotification(String text) {

        final String CHANNELID = "Foreground Service ID";
        //Los canales de notificacion se agregaron en la api26 por lo que solo funcionara en dispositivos cob android 8 o mayor
        //sino android no encontrara la clase "android.app.NotificationChannel"
        NotificationChannel channel = new NotificationChannel(CHANNELID, CHANNELID, NotificationManager.IMPORTANCE_LOW);

        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, new Intent(this, ForegroundService.class), 0);

        return new Notification.Builder(this, CHANNELID)
                .setContentTitle("Servicio de prueba")
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_face)
                .setContentIntent(contentIntent).getNotification();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
