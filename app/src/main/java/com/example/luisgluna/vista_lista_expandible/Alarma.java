package com.example.luisgluna.vista_lista_expandible;

import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Alarma extends ActionBarActivity {
    MediaPlayer mp;
    SoundPool sp;
    int flujodemusica=0;
    String respuesta="",respuesta_correcta;
    MyTask hilo = new MyTask();
    boolean parar=false;
    Random random;
    int valor;
    MainActivity obj = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);
        valor = Generar_Numeros_Aleatorios(random);
        TextView pregunta = (TextView) findViewById(R.id.pregunta);
        ImageButton boton1 = (ImageButton) findViewById(R.id.imageButton1);
        ImageButton boton2 = (ImageButton) findViewById(R.id.imageButton2);
        pregunta.setText(getResources().getString(R.string.Pregunta_Grafica1_0));
        sp = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujodemusica= sp.load(this,R.raw.alarma_relog,1);
        hilo.execute();

        if(valor==1){
          pregunta.setText(getResources().getString(R.string.Pregunta_Grafica1_0));
          boton1.setImageResource(R.drawable.libro_azul);
          boton2.setImageResource(R.drawable.libro_verde);
          respuesta_correcta  = getResources().getString(R.string.Respuesta_Correcta_Grafica1);
        }

        if(valor==2){
            pregunta.setText(getResources().getString(R.string.Pregunta_Grafica2_0));
            boton1.setImageResource(R.drawable.barak_obama);
            boton2.setImageResource(R.drawable.george_bush);
            respuesta_correcta  = getResources().getString(R.string.Respuesta_Correcta_Grafica2);
        }

        if(valor==3){
            pregunta.setText(getResources().getString(R.string.Pregunta_Grafica3_0));
            boton1.setImageResource(R.drawable.bandera1);
            boton2.setImageResource(R.drawable.bandera2);
            respuesta_correcta= getResources().getString(R.string.Respuesta_Correcta_Grafica3);
        }

        if(valor==4){
            pregunta.setText(getResources().getString(R.string.Pregunta_Grafica4_0));
            boton1.setImageResource(R.drawable.bicicleta);
            boton2.setImageResource(R.drawable.motocicleta);
            respuesta_correcta= getResources().getString(R.string.Respuesta_Correcta_Grafica4);
        }

        if(valor==5){
            pregunta.setText(getResources().getString(R.string.Pregunta_Grafica5_0));
            boton1.setImageResource(R.drawable.guitarra);
            boton2.setImageResource(R.drawable.cuatro);
            respuesta_correcta= getResources().getString(R.string.Respuesta_Correcta_Grafica5);
        }

        if(valor==6){
            pregunta.setText(getResources().getString(R.string.Pregunta_Grafica6_0));
            boton1.setImageResource(R.drawable.girasol4);
            boton2.setImageResource(R.drawable.sol);
            respuesta_correcta= getResources().getString(R.string.Respuesta_Correcta_Grafica6);
        }

        if(valor==7){
            pregunta.setText(getResources().getString(R.string.Pregunta_Grafica7_0));
            boton1.setImageResource(R.drawable.zapote);
            boton2.setImageResource(R.drawable.granada2);
            respuesta_correcta= getResources().getString(R.string.Respuesta_Correcta_Grafica7);
        }

        if(valor==8){
            pregunta.setText(getResources().getString(R.string.Pregunta_Grafica8_0));
            boton1.setImageResource(R.drawable.policia);
            boton2.setImageResource(R.drawable.guardabosques);
            respuesta_correcta= getResources().getString(R.string.Respuesta_Correcta_Grafica8);
        }

        if(valor==9){
            pregunta.setText(getResources().getString(R.string.Pregunta_Grafica9_0));
            boton1.setImageResource(R.drawable.televisor);
            boton2.setImageResource(R.drawable.pantalla_cine);
            respuesta_correcta= getResources().getString(R.string.Respuesta_Correcta_Grafica9);
        }

        if(valor==10){
            pregunta.setText(getResources().getString(R.string.Pregunta_Grafica10_0));
            boton1.setImageResource(R.drawable.lobo_siberiano);
            boton2.setImageResource(R.drawable.lobo_blanco);
            respuesta_correcta= getResources().getString(R.string.Respuesta_Correcta_Grafica10);
        }

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click_Opcion2(2);
            }
        });

    }

    public void Click_Boton1(View view){
        //Toast.makeText(Alarma.this,"Me presionaron 1111111111",Toast.LENGTH_SHORT).show();
        respuesta=Comparaciones(1);
        parar=false;
    }

    public void Click_Opcion2(int boton){
        //Toast.makeText(Alarma.this,"Me presionaron 2222222",Toast.LENGTH_SHORT).show();
        respuesta=Comparaciones(boton);
        parar=false;
    }

    public String Comparaciones(int num_boton){
        String resultado="";

        if(valor==1 && num_boton==1){
            resultado = getResources().getString(R.string.Respuesta_Grafica1_2);
        }else {
            if(valor == 1 && num_boton==2){
                resultado = getResources().getString(R.string.Respuesta_Grafica1_1);
            }
        }

        if(valor==2 && num_boton==1){
            resultado = getResources().getString(R.string.Respuesta_Grafica2_1);
        }else {
            if(valor == 2 && num_boton==2){
                resultado = getResources().getString(R.string.Respuesta_Grafica2_2);
            }
        }

        if(valor==3 && num_boton==1){
            resultado = getResources().getString(R.string.Respuesta_Grafica3_2);
        }else {
            if(valor == 3 && num_boton==2){
                resultado = getResources().getString(R.string.Respuesta_Grafica3_1);
            }
        }

        if(valor==4 && num_boton==1){
            resultado = getResources().getString(R.string.Respuesta_Grafica4_1);
        }else {
            if(valor == 4 && num_boton==2){
                resultado = getResources().getString(R.string.Respuesta_Grafica4_2);
            }
        }

        if(valor==5 && num_boton==1){
            resultado = getResources().getString(R.string.Respuesta_Grafica5_2);
        }else {
            if(valor == 5 && num_boton==2){
                resultado = getResources().getString(R.string.Respuesta_Grafica5_1);
            }
        }

        if(valor == 6 && num_boton==1){
            resultado = getResources().getString(R.string.Respuesta_Grafica6_1);
        }else {
            if(valor == 6 && num_boton==2){
                resultado = getResources().getString(R.string.Respuesta_Grafica6_2);
            }
        }

        if(valor == 7 && num_boton==1){
            resultado = getResources().getString(R.string.Respuesta_Grafica7_2);
        }else {
            if(valor == 7 && num_boton==2){
                resultado = getResources().getString(R.string.Respuesta_Grafica7_1);
            }
        }

        if(valor == 8 && num_boton==1){
            resultado = getResources().getString(R.string.Respuesta_Grafica8_1);
        }else {
            if(valor == 8 && num_boton==2){
                resultado = getResources().getString(R.string.Respuesta_Grafica8_2);
            }
        }

        if(valor == 9 && num_boton==1){
            resultado = getResources().getString(R.string.Respuesta_Grafica9_2);
        }else {
            if(valor == 9 && num_boton==2){
                resultado = getResources().getString(R.string.Respuesta_Grafica9_1);
            }
        }

        if(valor == 10 && num_boton==1){
            resultado = getResources().getString(R.string.Respuesta_Grafica10_1);
        }else {
            if(valor == 10 && num_boton==2){
                resultado = getResources().getString(R.string.Respuesta_Grafica10_2);
            }
        }
        return resultado;
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
        getMenuInflater().inflate(R.menu.menu_alarma, menu);
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
            if (respuesta.equals(respuesta_correcta)){
                finish();
                Intent back = new Intent(Alarma.this,MainActivity.class);
                startActivity(back);

                return true;
                //return super.onKeyDown(keyCode, event);
                }else{
                    obj.Mostrar_Mensaje(Alarma.this,"Aun no estas en tus mejores condiciones","largo");
                    return true;
                }
        }
        return super.onKeyDown(keyCode,event);

    }

    public class MyTask extends AsyncTask<String, String, String> {
    boolean parar = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            while (!parar) {
                if(respuesta.equals("")){
                    sp.play(flujodemusica, 1, 1, 0, 0, 1);
                    try {
                        Thread.sleep(5500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    parar = true;
                    break;
                }
            }
            cancel(true);
            return null;
        }


        @Override
        protected void onProgressUpdate(String... values) {
             Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //cancel(true);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
