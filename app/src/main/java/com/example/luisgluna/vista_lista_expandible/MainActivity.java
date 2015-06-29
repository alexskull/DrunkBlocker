package com.example.luisgluna.vista_lista_expandible;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class MainActivity extends Activity {
    SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();
    Hilos hilo,hilo2;
    Switch estado_drunk_blocker;
    Switch estado_alarma;
    GrupoDeItems grupo0 = new GrupoDeItems("Drunk Blocker");
    GrupoDeItems grupo1 = new GrupoDeItems("Alarma");
    adaptador adapter;
    Intent salto = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearDatos();
        hilo2 = new Hilos("bd");
        if (hilo2.getStatus().toString().equals("FINISHED") || hilo2.getStatus().toString().equals("PENDING"))
        hilo2.execute();


        estado_drunk_blocker = (Switch) findViewById(R.id.switch1);
        estado_alarma = (Switch) findViewById(R.id.switch2);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listViewexp);
        adaptador adapter = new adaptador(this, grupos);
        listView.setAdapter(adapter);

    }

    public void crearDatos() {
        grupo0.children.add("Preferencias");
        grupo0.children.add("Configuración");
        grupos.append(0, grupo0);

        grupo1.children.add(0,"Nueva Alarma");
        grupos.append(1, grupo1);
    }

    public void AgregarAlarma(String valor,int posicion){
        grupo1.children.add(posicion,valor);
        grupos.append(1,grupo1);
    }

    public void Mostrar_Mensaje(Activity activity,String mensaje, String duracion){
        int tiempo=0;

        if (duracion.equals("largo"))
            tiempo=Toast.LENGTH_LONG;
        else
            tiempo=Toast.LENGTH_SHORT;

        Toast.makeText(activity,mensaje,tiempo).show();
    }

    public void LanzarDrunkBlocker(View view) throws InterruptedException {
        boolean on = estado_drunk_blocker.isChecked();
        if (on) {
            hilo = new Hilos("drunk blocker");
            hilo.execute();
        }
    }

    public void Ejecutar_Actividad(Activity origen, Class destino){
        salto.setClass(origen,destino);
        startActivity(salto);
    }

    public void Ejecutar_Actividad_Enviando_Datos(Activity origen, Class destino,String datos){
        salto.setClass(origen,destino);
        salto.putExtra("datos",datos);
        startActivity(salto);
    }

    public void Ejecutar_Servicio(Activity origen,Class servicio_destino){
        salto.setClass(origen,servicio_destino);
        startService(salto);
    }

    /*public ArrayList<String> Recorrer_Lista(ArrayList<String> Lista){
        ArrayList<String> separado = null;
        String[] vec;

        vec= Lista.get(0).toString().split("/");

        return separado;
    }*/
   /* public void Ejecutar_Actividad_ConservandoValores(Activity origen, Class destino, int codigo){

        Intent salto = new Intent(origen,destino);
        origen.startActivityForResult(salto,codigo);
    }
 */

    public void HacerUnaPausa(){
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public class Hilos extends AsyncTask<Void, Void, Boolean> {
        String valor;
        Cursor cursor;
        DataBaseManager manager;

       public Hilos(String parametro){
           valor = parametro;
       }

        @Override
        protected Boolean doInBackground(Void... params) {
        boolean pasar = true;

            if(valor.equals("drunk blocker")) {
                for (int i = 0; i < 10; i++) {

                    HacerUnaPausa();
                    if (!estado_drunk_blocker.isChecked()) {
                        Mostrar_Mensaje(MainActivity.this, "Entre en la cancelacion", "corta");
                        cancel(true);
                        pasar = false;
                        break;
                    }

                }

                if (pasar) {
                    //startActivity(new Intent(MainActivity.this,Drunk_Blocker.class));
                    Ejecutar_Actividad(MainActivity.this,Drunk_Blocker.class);
                    cancel(true);
                }
            }


            if (valor.equals("bd")){

                 Actualizar_Alarmas();
                 Verificar_Hilo_Alarma();
            }

            return true;
        }

        @Override
        protected void onPreExecute() {

            if(valor.equals("drunk blocker")) {
                Mostrar_Mensaje(MainActivity.this, "Cuenta con 10 segundos antes del bloqueo", "corto");
            }


        }

        public void Actualizar_Alarmas(){
            manager = new DataBaseManager(MainActivity.this);
            cursor = manager.Cargar_Cursor_Alarmas();

            int posicion = 1;
            if (cursor.moveToFirst()){
                do {
                    String id = cursor.getString(0);
                    String horas = cursor.getString(1);
                    String minutos = cursor.getString(2);
                    String horario = cursor.getString(3);
                    String etiqueta = cursor.getString(4);
                    String estado_alarma = cursor.getString(5);

                    AgregarAlarma(horas + ":" + minutos + " " + horario + "       " + etiqueta + " ---------" + estado_alarma, posicion);
                    posicion++;
                }while(cursor.moveToNext());

                cursor.close();
                 
            }
        }

        public void Verificar_Hilo_Alarma(){
            manager = new DataBaseManager(MainActivity.this);
            cursor = manager.Cargar_Cursor_Alarmas();

            if (cursor.moveToFirst()){
                do {
                    String id = cursor.getString(0);
                    String horas = cursor.getString(1);
                    String minutos = cursor.getString(2);
                    String horario = cursor.getString(3);
                    String etiqueta = cursor.getString(4);
                    String estado_alarma = cursor.getString(5);
                    if (estado_alarma.equals("activa")){
                          Ejecutar_Servicio(MainActivity.this,MyService.class);
                          break;
                    }
                    }while(cursor.moveToNext());

                cursor.close();
            }
        }

        public void Obvervando_Alarmas(){
            manager = new DataBaseManager(MainActivity.this);
            cursor = manager.Cargar_Cursor_Alarmas();

            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(0);
                    String horas = cursor.getString(1);
                    String minutos = cursor.getString(2);
                    String horario = cursor.getString(3);
                    String etiqueta = cursor.getString(4);
                    String estado_alarma = cursor.getString(5);

                    if(!grupo1.children.get(1).equals(horas + ":" + minutos + " " + horario + "       " + etiqueta + " ---------" + estado_alarma)){
                        grupo1.children.remove(1);
                        AgregarAlarma(horas + ":" + minutos + " " + horario + "       " + etiqueta + " ---------" + estado_alarma, 1);
                        break;
                    }

                   }while(cursor.moveToNext());
                cursor.close();
            }
        }
    }
}
