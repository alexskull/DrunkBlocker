package com.example.luisgluna.vista_lista_expandible;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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


public class Modificar_Alarma extends ActionBarActivity {
    Spinner lista;
    String[] opciones = {"AM","PM"};
    CheckBox activar;
    MainActivity obj = new MainActivity();
    EditText hora,minutos;
    TextView etiqueta2;
    DataBaseManager manager;
    String alarma_activa;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__alarma);
        Button aceptar = (Button) findViewById(R.id.aceptar);
        Button cancelar = (Button) findViewById(R.id.cancelar);
        lista = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(Modificar_Alarma.this,android.R.layout.simple_spinner_item,opciones);
        lista.setAdapter(adaptador);
        activar = (CheckBox) findViewById(R.id.ActivarAlarma);
        hora = (EditText) findViewById(R.id.horas);
        minutos = (EditText) findViewById(R.id.minutos);
        etiqueta2 = (TextView) findViewById(R.id.etiqueta2);
        manager = new DataBaseManager(Modificar_Alarma.this);
        alarma_activa = "noactiva";
        bundle = getIntent().getExtras();
        //bundle.getString("datos");
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
        manager.Modificar_Campo(bundle.getString("datos").toString(),cadena);
        finish();
        obj.Ejecutar_Actividad(Modificar_Alarma.this,MainActivity.class);
    }

    public void Eliminar(View view){
        manager.Eliminar_Dato(bundle.getString("datos").toString());
        finish();
        obj.Ejecutar_Actividad(Modificar_Alarma.this,MainActivity.class);
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
