package com.example.luisgluna.vista_lista_expandible;


import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyService extends Service {
    Intent salto = new Intent();
    MyTask myTask;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Alarma creada!", Toast.LENGTH_LONG).show();
        myTask = new MyTask();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this,"Antes de ejecutar------------------ "+myTask.getStatus().toString(),Toast.LENGTH_LONG).show();
        if (myTask.getStatus().toString().equals("FINISHED") || myTask.getStatus().toString().equals("PENDING")) {

/*            if (myTask.getStatus().toString().equals("RUNNING")){
                myTask.cancel(true);
            }
*/
            myTask.execute();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      //  Toast.makeText(getApplicationContext(), "Alarma cancelada!", Toast.LENGTH_SHORT).show();
        myTask.cancel(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void GenerarSaltos(Activity origen, Class destino){
        salto.setClass(origen,destino);
        startActivity(salto);
    }

    public class MyTask extends AsyncTask<String, String, String> {

        private DateFormat dateFormat;
        private String date;
        private boolean cent;
        DataBaseManager manager;
        Cursor cursor;
        ArrayList<String> vec2 = new ArrayList<String>();
      /*  public MyTask(Activity act){
            manager = new DataBaseManager(act);
            activity2 = act;
        }*/

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dateFormat = new SimpleDateFormat("HH:mm:ss");
            cent = true;
            manager = new DataBaseManager(getApplicationContext());
        }

        @Override
        protected String doInBackground(String... params) {
            String cadena;
            String[] vec;
            int horas_sys=0,minutos_sys=0,segundos_sys=0;
            while (cent){
                date = dateFormat.format(new Date());
                cursor = manager.Cargar_Cursor_Alarmas();
                cadena = date;
                vec = cadena.split(":");
                horas_sys =  Integer.parseInt(vec[0]);
                minutos_sys =  Integer.parseInt(vec[1]);
                segundos_sys =  Integer.parseInt(vec[2]);
                if (horas_sys>12){
                    horas_sys = horas_sys - 12;
                }else{
                    if (horas_sys==0){
                        horas_sys=12;
                    }
                }

               // publishProgress(horas_sys+" "+minutos_sys+" "+segundos_sys);
                cursor.moveToFirst();
                if (cursor.moveToFirst()){
                    do {
                        String id = cursor.getString(0);
                        String horas = cursor.getString(1);
                        String minutos = cursor.getString(2);
                        String horario = cursor.getString(3);
                        String etiqueta = cursor.getString(4);
                        String estado_alarma = cursor.getString(5);
                      //  publishProgress("horas sistema: "+horas_sys+" hora alarma: "+horas);
                        if (horas_sys==Integer.parseInt(horas)){
                           // publishProgress("Solo horas: "+horas);

                            if (minutos_sys==Integer.parseInt(minutos)){
                              //publishProgress("Horas: " + horas + " minutos " + minutos);
                                cursor = manager.Cargar_Cursor_Alarmas();
                                vec2 = manager.Recorrer_Cursor(cursor);
                                cadena = Recorrer_ArrayList(vec2);
                                manager.Modificar_Campo("1",cadena);


                                Intent hola = new Intent(getApplicationContext(),Alarma.class);
                                hola.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(hola);
                                stopService(new Intent(getApplicationContext(),MyService.class));
                                cent = false;
                                cancel(true);
                                onCancelled();
                                break;

                            }
                        }
                     }while(cursor.moveToNext());
                    cursor.close();
                }

                if (cent==true){
                    try {
                        // Stop 5s
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                    }
                }

                
            }
            return null;
        }

        public String Recorrer_ArrayList(ArrayList<String> vec){
            String[] cadena = vec.get(0).split("/");
            String retorno = cadena[0];

            for (int i = 1; i <cadena.length -1 ; i++) {
                retorno = retorno + "/" + cadena[i];
            }

            retorno = retorno + "/" + "noactiva";
            return retorno;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            cent = false;
        }
    }
}