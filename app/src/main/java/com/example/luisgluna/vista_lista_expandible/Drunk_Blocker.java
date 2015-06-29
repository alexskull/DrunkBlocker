package com.example.luisgluna.vista_lista_expandible;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;


public class Drunk_Blocker extends ActionBarActivity {
    Random random;
    TextView pregunta;
    ArrayAdapter<CharSequence> adaptador = null;
    String respuesta,respuesta_correcta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drunk__blocker);
        int valor = Generar_Numeros_Aleatorios(random);

        Spinner respuestas = (Spinner) findViewById(R.id.spinner);
        TextView pregunta = (TextView) findViewById(R.id.Pregunta);

        if (valor==1) {
            pregunta.setText(getResources().getString(R.string.Pregunta1));
            adaptador = ArrayAdapter.createFromResource(this, R.array.Respeustas1, android.R.layout.simple_spinner_item);
            respuesta_correcta=getResources().getString(R.string.Respuesta_Correcta1);
        }

        if (valor==2) {
            pregunta.setText(getResources().getString(R.string.Pregunta2));
            adaptador = ArrayAdapter.createFromResource(this, R.array.Respeustas2, android.R.layout.simple_spinner_item);
            respuesta_correcta=getResources().getString(R.string.Respuesta_Correcta2);
        }

        if (valor==3) {
            pregunta.setText(getResources().getString(R.string.Pregunta3));
            adaptador = ArrayAdapter.createFromResource(this, R.array.Respeustas3, android.R.layout.simple_spinner_item);
            respuesta_correcta=getResources().getString(R.string.Respuesta_Correcta3);
        }

        if (valor==4) {
            pregunta.setText(getResources().getString(R.string.Pregunta4));
            adaptador = ArrayAdapter.createFromResource(this, R.array.Respeustas4, android.R.layout.simple_spinner_item);
            respuesta_correcta=getResources().getString(R.string.Respuesta_Correcta4);
        }

        if (valor==5) {
            pregunta.setText(getResources().getString(R.string.Pregunta5));
            adaptador = ArrayAdapter.createFromResource(this, R.array.Respeustas5, android.R.layout.simple_spinner_item);
            respuesta_correcta=getResources().getString(R.string.Respuesta_Correcta5);
        }

        if (valor==6) {
            pregunta.setText(getResources().getString(R.string.Pregunta6));
            adaptador = ArrayAdapter.createFromResource(this, R.array.Respeustas6, android.R.layout.simple_spinner_item);
            respuesta_correcta=getResources().getString(R.string.Respuesta_Correcta6);
        }

        if (valor==7) {
            pregunta.setText(getResources().getString(R.string.Pregunta7));
            adaptador = ArrayAdapter.createFromResource(this, R.array.Respeustas7, android.R.layout.simple_spinner_item);
            respuesta_correcta=getResources().getString(R.string.Respuesta_Correcta7);}

        if (valor==8) {
            pregunta.setText(getResources().getString(R.string.Pregunta8));
            adaptador = ArrayAdapter.createFromResource(this, R.array.Respeustas8, android.R.layout.simple_spinner_item);
            respuesta_correcta=getResources().getString(R.string.Respuesta_Correcta8);
        }

        if (valor==9) {
            pregunta.setText(getResources().getString(R.string.Pregunta9));
            adaptador = ArrayAdapter.createFromResource(this, R.array.Respeustas9, android.R.layout.simple_spinner_item);
            respuesta_correcta=getResources().getString(R.string.Respuesta_Correcta9);
        }

        if (valor==10) {
            pregunta.setText(getResources().getString(R.string.Pregunta10));
            adaptador = ArrayAdapter.createFromResource(this, R.array.Respeustas10, android.R.layout.simple_spinner_item);
            respuesta_correcta=getResources().getString(R.string.Respuesta_Correcta10);
        }

        respuestas.setAdapter(adaptador);
        respuestas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView opcion = (TextView) view;

                respuesta = opcion.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }




    public int Generar_Numeros_Aleatorios(Random random){
        int aleatorio=0;
        random = new Random();

        aleatorio = ((int)(random.nextDouble() * 10.0))+1;
        return aleatorio;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drunk__blocker, menu);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
            if (respuesta.equals(respuesta_correcta)){
                Intent back = new Intent(Drunk_Blocker.this,MainActivity.class);
                startActivity(back);
            }else{
                Toast.makeText(Drunk_Blocker.this,"Aun no esta en su mejor condición",Toast.LENGTH_LONG).show();
            }
                return true;


        }
        return super.onKeyDown(keyCode, event);
    }

    @Override

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            if (respuesta.equals(respuesta_correcta)){
                Intent back = new Intent(Drunk_Blocker.this,MainActivity.class);
                startActivity(back);

            }else{
                Toast.makeText(Drunk_Blocker.this,"Aun no esta en su mejor condición",Toast.LENGTH_LONG).show();
            }
                return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }
}
