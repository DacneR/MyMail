package com.mymail.mymail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;

import java.util.ArrayList;

public class P_usuarios extends AppCompatActivity {

    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pusuarios);


    }
    @Override
    public void onStart(){
        super.onStart();
        cargar();
    }

    public void cargar(){

        try {
            AdminBDS admin = new AdminBDS(this,"BD",null,1);
            SQLiteDatabase BD = admin.getReadableDatabase();

            Cursor cursor = BD.query(
                    "noti",   // Nombre de la tabla
                    new String[]{"color", "titulo", "descr","comen"},  // Columnas a obtener
                    null,  // Clausula WHERE (null para obtener todos los registros)
                    null,
                    null,
                    null,
                    null
            );
            ArrayList<ListaElem> elementos = new ArrayList<>();
            /*Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.caballero);


            ImageView imagen = new ImageView(this);
            imagen.setImageBitmap(bitmap);*/

            while (cursor.moveToNext()) {
                String color = cursor.getString(cursor.getColumnIndexOrThrow("color"));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descr"));
                String comentario = cursor.getString(cursor.getColumnIndexOrThrow("comen"));

                elementos.add(new ListaElem(color,titulo,descripcion,comentario));


            }
            cursor.close();
            BD.close();


        /*elementos.add(new ListaElem("#7B1FA2","Pedro","Ciudad de México","Activo",0));
        elementos.add(new ListaElem("#7B1FA2","Pedro","Ciudad de México","Activo",0));
        elementos.add(new ListaElem("#7B1FA2","Pedro","Ciudad de México","Activo",0));
        elementos.add(new ListaElem("#7B1FA2","Pedro","Ciudad de México","Activo",0));
        elementos.add(new ListaElem("#7B1FA2","Pedro","Ciudad de México","Activo",0));
        elementos.add(new ListaElem("#7B1FA2","Pedro","Ciudad de México","Activo",0));
        elementos.add(new ListaElem("#7B1FA2","Pedro","Ciudad de México","Activo",0));
        elementos.add(new ListaElem("#7B1FA2","Pedro","Ciudad de México","Activo",0));*/

            RecyclerView contenedor = findViewById(R.id.Contenedor);

            contenedor.setHasFixedSize(true);
            LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
            layout.setOrientation(LinearLayoutManager.VERTICAL);
            contenedor.setAdapter(new Adaptador(elementos));
            contenedor.setLayoutManager(layout);
        }catch (Exception e){
            Log.i("DEPURACION","Error capturado "+e);
        }


    }
}