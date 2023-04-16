package com.mymail.mymail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;

public class P_admin extends AppCompatActivity {

    public TextView CEDULA, CARGO, USUARIO;
    public EditText TITLE, DESCRIP, COMEN, COLOR;

    public Button INSERTAR, LIMPIAR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padmin);
        CEDULA = findViewById(R.id.txtcc);
        CARGO = findViewById(R.id.txtcar);
        USUARIO = findViewById(R.id.txtuse);

        TITLE = findViewById(R.id.ettitle);
        DESCRIP = findViewById(R.id.etdescrip);
        COMEN = findViewById(R.id.etcomen);
        COLOR = findViewById(R.id.etcolor);

        INSERTAR = findViewById(R.id.btnInsertar);
        LIMPIAR = findViewById(R.id.btnLimpiar);
    }

    @Override
    public  void onStart(){
        super.onStart();
        cargarInfo();

        INSERTAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar(view);
            }
        });

        LIMPIAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Limpiar();
            }
        });



    }

    public void Limpiar(){
        AdminBDS admin = new AdminBDS(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        // Eliminar todos los registros de la tabla "nombre_tabla"
        BD.delete("noti", null, null);

        BD.close();

    }

    public String TAG = "DEPURACION";
    public void insertar(View view){


        String title, descrip, comen, color;

        title = TITLE.getText().toString();
        descrip = DESCRIP.getText().toString();
        comen = COMEN.getText().toString();
        color = COLOR.getText().toString();

        try {
            if(!title.isEmpty() && !descrip.isEmpty() && !comen.isEmpty() && !color.isEmpty())
            {
                Log.i(TAG,"esto hay en todo " + title + descrip + comen + color);

                AdminBDS admin = new AdminBDS(this,"BD",null,1);
                SQLiteDatabase BD = admin.getWritableDatabase();

                ContentValues registro = new ContentValues();
                registro.put("color",color);
                registro.put("titulo",title);
                registro.put("descr",descrip);
                registro.put("comen",comen);

                long result = BD.insert("noti",null,registro);

                if (result == -1){
                    Toast.makeText(this, "Error al insertar datos", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
                }
                BD.close();

                TITLE.setText("");
                DESCRIP.setText("");
                COMEN.setText("");
                COLOR.setText("");

            }else
            {
                Toast.makeText(this, "Complete los campos vacíos",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "Error capturado " +e,Toast.LENGTH_LONG).show();
        }



    }

    public void cargarInfo(){
        String cedula = getIntent().getStringExtra("cedula");
        String usuario = getIntent().getStringExtra("usuario");
        String cargo = getIntent().getStringExtra("cargo");

        CEDULA.setText(cedula);
        USUARIO.setText(usuario);
        CARGO.setText(cargo);


    }
}