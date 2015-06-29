package com.example.luisgluna.vista_lista_expandible;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;


public class Nueva_Alarma extends ActionBarActivity {
    Spinner lista;
    String[] opciones = {"AM","PM"};
    CheckBox activar;
    MainActivity obj = new MainActivity();
    EditText hora,minutos;
    TextView etiqueta2;
    DataBaseManager manager;
    String alarma_activa;
    MyService servicio;
    Intent salto = new Intent();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva__alarma);
        Button aceptar = (Button) findViewById(R.id.aceptar);
        Button cancelar = (Button) findViewById(R.id.cancelar);
        lista = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(Nueva_Alarma.this,android.R.layout.simple_spinner_item,opciones);
        lista.setAdapter(adaptador);
        activar = (CheckBox) findViewById(R.id.ActivarAlarma);
        hora = (EditText) findViewById(R.id.horas);
        minutos = (EditText) findViewById(R.id.minutos);
        etiqueta2 = (TextView) findViewById(R.id.etiqueta2);
        manager = new DataBaseManager(Nueva_Alarma.this);
        alarma_activa = "noactiva";


    }

    public void onCheckboxClicked(View view) {
        if (activar.isChecked()){
            alarma_activa="activa";
        }else{
            alarma_activa="noactiva";
        }
    }

    public void Aceptar(View view){
        String cadena= hora.getText().toString()+"/"+minutos.getText().toString()+"/"+lista.getSelectedItem().toString()+"/"+etiqueta2.getText().toString()+"/"+alarma_activa;
        manager.Insertar_Tabla_Alarma(cadena);

        finish();
        Ejecutar_Actividad(Nueva_Alarma.this,MainActivity.class);
    }

    public void Cancelar(View view){
        //Modificar al tener el servicio/demonio
        finish();
        //Ejecutar_Actividad(Nueva_Alarma.this,MainActivity.class);
        startActivity(new Intent(Nueva_Alarma.this,MainActivity.class));
    }

    public void Ejecutar_Servicio(Activity origen,Class servicio_destino){
        salto.setClass(origen,servicio_destino);
        startService(salto);
    }

    public void Ejecutar_Actividad(Activity origen, Class destino){
        salto.setClass(origen,destino);
        startActivity(salto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nueva__alarma, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
