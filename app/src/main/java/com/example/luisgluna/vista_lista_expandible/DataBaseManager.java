package com.example.luisgluna.vista_lista_expandible;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBaseManager {
    public static final String NOMBRE_TABLA = "alarmas";

    public static final String NOMBRE_COLUMNA_ID = "_id";
    public static final String NOMBRE_COLUMNA_HORA = "hora";
    public static final String NOMBRE_COLUMNA_MINUTOS = "minutos";
    public static final String NOMBRE_COLUMNA_HORARIO = "horario";
    public static final String NOMBRE_COLUMNA_ETIQUETA = "etiqueta";
    public static final String NOMBRE_COLUMNA_ESTADO_ALARMA = "estado";
    Context co;

    public static final String NOMBRE_TABLA2 = "estado_hilo";
    public static final String NOMBRE_COLUMNA_ID2 = "_id";
    public static final String NOMBRE_COLUMNA_ACTIVO = "activo";


    public static final String CREATE_TABLE = "create table "+ NOMBRE_TABLA + " ("
            +NOMBRE_COLUMNA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +NOMBRE_COLUMNA_HORA +" TEXT NOT NULL,"
            +NOMBRE_COLUMNA_MINUTOS +" TEXT NOT NULL,"
            +NOMBRE_COLUMNA_HORARIO +" TEXT NOT NULL,"
            +NOMBRE_COLUMNA_ETIQUETA +" TEXT,"
            +NOMBRE_COLUMNA_ESTADO_ALARMA + " TEXT NOT NULL);";

    public static final String CREATE_TABLE2 = "create table "+ NOMBRE_TABLA2 + " ("
            +NOMBRE_COLUMNA_ID2 +" integer primary key autoincrement,"
            +NOMBRE_COLUMNA_ACTIVO +" integer not null default 0);";



    public BdHelper helper;
    public SQLiteDatabase db;

    public DataBaseManager(Context context) {
        helper = new BdHelper(context);
        db = helper.getWritableDatabase();
        co = context;
    }

    private ContentValues Generar_Contenedor_Valores(String cadena){
        ContentValues valores = new ContentValues();
        String[] vec;

        vec = cadena.split("/");
        valores.put(NOMBRE_COLUMNA_HORA,vec[0]);
        valores.put(NOMBRE_COLUMNA_MINUTOS,vec[1]);
        valores.put(NOMBRE_COLUMNA_HORARIO,vec[2]);
        valores.put(NOMBRE_COLUMNA_ETIQUETA,vec[3]);
        valores.put(NOMBRE_COLUMNA_ESTADO_ALARMA,vec[4]);
       // Toast.makeText(co,"Horas: "+vec[0] + " Minutos: " + vec[1] + " Horario: " + vec[2] + " Etiqueta: " + vec[3] + " Estado Alarma: " + vec[4],Toast.LENGTH_LONG).show();
        return valores;
    }

    private ContentValues Generar_Contenedor_Valores_Hilo(int cadena){
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE_COLUMNA_ACTIVO,cadena);
        // Toast.makeText(co,"Horas: "+vec[0] + " Minutos: " + vec[1] + " Horario: " + vec[2] + " Etiqueta: " + vec[3] + " Estado Alarma: " + vec[4],Toast.LENGTH_LONG).show();
        return valores;
    }


    public void Insertar_Tabla_Alarma(String cadena){
        db.insert(NOMBRE_TABLA,null,Generar_Contenedor_Valores(cadena));
        //Generar_Contenedor_Valores(cadena);
       // Eliminar_Dato();
    }

    public void Insertar_Tabla_Estado_Hilo(int cadena){
        db.insert(NOMBRE_TABLA2,null,Generar_Contenedor_Valores_Hilo(cadena));
        //Generar_Contenedor_Valores(cadena);
        // Eliminar_Dato();
    }

    public void Eliminar_Dato(String id){
        db.delete(NOMBRE_TABLA,NOMBRE_COLUMNA_ID + "=?",new String[]{id});
    }

    public void Eliminar_Multiples_Dato(String nom,String nom2){
        db.delete(NOMBRE_TABLA,NOMBRE_COLUMNA_ETIQUETA + " IN (?,?)",new String[]{nom,nom2});
    }

    public void Eliminar_Dato_Hilo(String id){
        db.delete(NOMBRE_TABLA2,NOMBRE_COLUMNA_ID2 + "=?",new String[]{id});
    }

    public void Modificar_Campo(String id,String cadena){
        db.update(NOMBRE_TABLA,Generar_Contenedor_Valores(cadena),NOMBRE_COLUMNA_ID +"=?",new String[]{id});
    }

    public void Modificar_Campo_Tabla_Hilo(String id,int cadena){
        db.update(NOMBRE_TABLA2,Generar_Contenedor_Valores_Hilo(cadena),NOMBRE_COLUMNA_ID2 +"=?",new String[]{id});
    }

    public Cursor Cargar_Cursor_Alarmas(){
        String[] columnas = new String[]{NOMBRE_COLUMNA_ID,NOMBRE_COLUMNA_HORA,NOMBRE_COLUMNA_MINUTOS,NOMBRE_COLUMNA_HORARIO,NOMBRE_COLUMNA_ETIQUETA,NOMBRE_COLUMNA_ESTADO_ALARMA};

        return db.query(NOMBRE_TABLA,columnas,null,null,null,null,null);
    }

    public Cursor Cargar_Cursor_Hilo(){
        String[] columnas = new String[]{NOMBRE_COLUMNA_ID2,NOMBRE_COLUMNA_ACTIVO};

        return db.query(NOMBRE_TABLA2,columnas,null,null,null,null,null);
    }

    public ArrayList Recorrer_Cursor(Cursor cursor){
        ArrayList<String> cadena = new ArrayList<String>();
        int i=0,j=0,posicion=0;



        if (cursor.moveToFirst()){
           // Toast.makeText(co,"Numero de columnas: "+cursor.getColumnCount()+"",Toast.LENGTH_SHORT).show();

            do {


                cadena.add(cursor.getString(1)+"/"+cursor.getString(2)+"/"+cursor.getString(3)+"/"+cursor.getString(4)+"/"+cursor.getString(5));
                /*for (j = 1; j == cursor.getColumnCount(); j++) {
                    if (j <= cursor.getColumnCount()-1)
                        cadena.add(posicion,cadena.get(posicion)+cursor.getString(j)+"/");
                    else
                        cadena.add(posicion,cadena.get(posicion)+cursor.getString(j));
                }
                posicion++;*/
                //Toast.makeText(co,"cadena: "+cadena[i]+" El cursor: "+cursor.getString(j),Toast.LENGTH_SHORT).show();
              //  Toast.makeText(co,"cadena: "+cadena.get(0),Toast.LENGTH_SHORT).show();
                //i++;
            }while(cursor.moveToNext());
            cursor.close();
            //Toast.makeText(co,"cadena: "+cadena.get(0),Toast.LENGTH_SHORT).show();
        }
        return cadena;
    }
}
