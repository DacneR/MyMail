package com.mymail.mymail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class P_Registrar extends AppCompatActivity {

    Button REGISTRAR;

    EditText CC, USUARIO, PIN, CARGO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregistrar);
        REGISTRAR = findViewById(R.id.btnRegistrar);
        CC = findViewById(R.id.Txtcedu);
        USUARIO = findViewById(R.id.Txtusuario);
        PIN = findViewById(R.id.Txtpin);
        CARGO = findViewById(R.id.TxtCar);

    }
    @Override
    public void onStart(){
        super.onStart();

        REGISTRAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar(view);
            }
        });
    }

    public void insertar(View view){
        AdminBD admin = new AdminBD(this,"LBDS", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        int cedula, usuario, pin;
        String cargo;
        cedula = Integer.parseInt(CC.getText().toString());
        usuario = Integer.parseInt(USUARIO.getText().toString());
        pin = Integer.parseInt(PIN.getText().toString());
        cargo = CARGO.getText().toString();

        if(cedula>100000 && usuario!=0 && pin>1000 && !cargo.isEmpty())
        {
            ContentValues registro = new ContentValues();
            registro.put("cedula",cedula);
            registro.put("usuario",usuario);
            registro.put("pin",pin);
            registro.put("cargo",cargo);
            BD.insert("usuarios", null, registro);
            BD.close();

            CC.setText("");
            USUARIO.setText("");
            PIN.setText("");
            CARGO.setText("");

            Toast.makeText(this, "Registro exitoso",Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(this, "Complete los campos vacíos",Toast.LENGTH_LONG).show();
        }


    }

}