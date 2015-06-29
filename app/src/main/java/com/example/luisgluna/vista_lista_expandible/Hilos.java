/*package com.example.luisgluna.vista_lista_expandible;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


public class Hilos extends AsyncTask<Void, Void, Boolean> {
public Activity activity;
MainActivity principal;

    public Hilos(Activity act){
        activity = act;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        principal = new MainActivity();
        for (int i = 0; i < 10; i++) {

            principal.HacerUnaPausa();
           // publishProgress();
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result)
            Toast.makeText(activity, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(activity,"Cuenta con 10 segundos antes del bloqueo",Toast.LENGTH_SHORT).show();
    }
}
*/