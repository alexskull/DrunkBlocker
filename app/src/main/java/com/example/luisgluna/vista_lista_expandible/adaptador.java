package com.example.luisgluna.vista_lista_expandible;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class adaptador extends BaseExpandableListAdapter {
    private final SparseArray<GrupoDeItems> grupos;
    public LayoutInflater inflater;
    public Activity activity;
    Intent salto = new Intent();

    // Constructor
    public adaptador(Activity act, SparseArray<GrupoDeItems> grupos) {
        activity = act;
        this.grupos = grupos;
        inflater = act.getLayoutInflater();
    }
    // Nos devuelve los datos asociados a un subitem en base
    // a la posición
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return grupos.get(groupPosition).children.get(childPosition);
    }
    // Devuelve el id de un item o subitem en base a la
    // posición de item y subitem
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }
    // En base a la posición del item y de subitem nos devuelve
    // el objeto view correspondiente y el layout para los subitems
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView textvw = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.subitems_layout, null);
        }
        textvw = (TextView) convertView.findViewById(R.id.textView1);
        textvw.setText(children);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent ine = new Intent();
                //Esto emite una pequeña ventana de alerta con el mensaje seleccionado
                //Toast.makeText(activity, children, Toast.LENGTH_SHORT).show();
                switch (groupPosition){
                    case 0:
                        switch (childPosition){
                          case 0:
                              Ejecutar_Actividad(activity,Drunk_Blocker.class);
                                //GenerarSaltos(activity,Drunk_Blocker.class);
                                break;

                          case 1:
                              Ejecutar_Actividad(activity,Drunk_Blocker.class);
                                //GenerarSaltos(activity,Drunk_Blocker.class);
                                break;
                        }
                        break;
                    case 1:

                        DataBaseManager manager = new DataBaseManager(activity);
                        Cursor cursor = manager.Cargar_Cursor_Alarmas();
                        cursor.moveToFirst();
                        switch (childPosition){
                            case 0:
                                ine.setClass(activity,Nueva_Alarma.class);
                                if(cursor.getCount()<3) {
                                    activity.finish();
                                    Ejecutar_Actividad(activity,Nueva_Alarma.class);
                                 }
                                else
                                   Mostrar_Mensaje(activity,"Solo puede tener 1 alarma","corto");
                                 //GenerarSaltos(activity,Alarma.class);
                                break;
                            case 1:
                                activity.finish();
                                Ejecutar_Actividad_Enviando_Datos(activity,Modificar_Alarma.class,cursor.getString(0));
                                break;

                         /*   case 2:
                                cursor.moveToPosition(1);
                                activity.finish();
                                activity.stopService(new Intent(activity,MyService.class));
                                Ejecutar_Actividad_Enviando_Datos(activity, Modificar_Alarma.class, cursor.getString(0));
                                break;
                            */
                          /*  case 3:
                              cursor.moveToPosition(2);
                                Ejecutar_Actividad_Enviando_Datos(activity,Modificar_Alarma.class,cursor.getString(0));
                                break;

                            case 4:
                                cursor.moveToPosition(3);
                                Ejecutar_Actividad_Enviando_Datos(activity,Modificar_Alarma.class,cursor.getString(0));
                                break;

                            case 5:
                                cursor.moveToPosition(4);
                                Ejecutar_Actividad_Enviando_Datos(activity,Modificar_Alarma.class,cursor.getString(0));
                                break;*/
                            //asigna una imagen a un teim o subitem
                            //textvw.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pescadofrito, 0, 0, 0);

                        }
                        cursor.close();
                       // 
                        break;
                }
            }
        });

        textvw.setText(children);

        return convertView;
    }
    // Nos devuelve la cantidad de subitems que tiene un ítem
    @Override
    public int getChildrenCount(int groupPosition) {
        return grupos.get(groupPosition).children.size();
    }


    //Los datos de un ítem especificado por groupPosition
    @Override
    public Object getGroup(int groupPosition) {
        return grupos.get(groupPosition);
    }

    //La cantidad de ítem que tenemos definidos
    @Override
    public int getGroupCount() {
        return grupos.size();
    }

    //Método que se invoca al contraer un ítem
    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    //Método que se invoca al expandir un ítem
    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    //Devuelve el id de un ítem
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    //Obtenemos el layout para los ítems
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.items_layout, null);
        }
        GrupoDeItems grupo = (GrupoDeItems) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(grupo.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //Nos informa si es seleccionable o no un ítem o subitem
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void Ejecutar_Actividad(Activity origen, Class destino){
        salto.setClass(origen,destino);
        origen.startActivity(salto);
    }

    public void Ejecutar_Actividad_Enviando_Datos(Activity origen, Class destino,String datos){
        salto.setClass(origen,destino);
        salto.putExtra("datos",datos);
        origen.startActivity(salto);
    }

    public void Mostrar_Mensaje(Activity activity,String mensaje, String duracion){
        int tiempo=0;

        if (duracion.equals("largo"))
            tiempo=Toast.LENGTH_LONG;
        else
            tiempo=Toast.LENGTH_SHORT;

        Toast.makeText(activity,mensaje,tiempo).show();
    }
}