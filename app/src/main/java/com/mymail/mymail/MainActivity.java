package com.mymail.mymail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button REGISTRAR, INICIAR;

    EditText PIN, USUARIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        REGISTRAR = findViewById(R.id.btnRegistrar);
        INICIAR = findViewById(R.id.btnLoguear);

        PIN= findViewById(R.id.Txtpin);
        USUARIO = findViewById(R.id.Txtusuario);





    }

    @Override
    public void onStart(){
        super.onStart();

        REGISTRAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarPantalla(view);
            }
        });

        INICIAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarU(view);
            }
        });
    }

    public void pasarPantalla(View view){
        Intent P = new Intent(this,P_Registrar.class);
        startActivity(P);
    }

    String TAG = "DEPURACION";
    public void consultarU(View view){
        int U=0, P=0;
        try {
            U = Integer.parseInt(USUARIO.getText().toString());
            P = Integer.parseInt(PIN.getText().toString());
        }catch (Exception e){

        }


        if(U!=0 && P>1000){
            Log.i(TAG,"Estoy dentro del si");
            try {
                AdminBD admin = new AdminBD(this,"LBDS", null, 1);
                SQLiteDatabase BD = admin.getWritableDatabase();
                Log.i(TAG,"vamos bien");
                Cursor row = BD.rawQuery("Select cedula, usuario, pin, cargo from usuarios where usuario="+U+" and pin="+P,null);
                Log.i(TAG,"Estoy hay en row"+row);

                String cedula, usuario, pin, cargo;
                if(row.moveToFirst()){
                    cedula = row.getString(0);
                    usuario = row.getString(1);
                    pin = row.getString(2);
                    cargo = row.getString(3);

                    Log.i(TAG,"Esto hay  "+cargo);

                    if(cargo.equals("Admin"))
                    {

                        Intent in = new Intent(this,P_admin.class);

                        in.putExtra("usuario",usuario);
                        in.putExtra("cedula",cedula);
                        in.putExtra("pin",pin);
                        in.putExtra("cargo",cargo);
                        BD.close();

                        startActivity(in);

                        USUARIO.setText("");
                        PIN.setText("");



                    }else if (cargo.equals("Usuario"))
                    {
                        Intent in = new Intent(this,P_usuarios.class);

                        in.putExtra("usuario",usuario);
                        in.putExtra("cedula",cedula);
                        in.putExtra("pin",pin);
                        in.putExtra("cargo",cargo);
                        BD.close();

                        startActivity(in);

                        USUARIO.setText("");
                        PIN.setText("");


                    }else{
                        Toast.makeText(this, "No se encuentra el cargo",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, "No se encuentra el usuario",Toast.LENGTH_LONG).show();
                }



            }catch (Exception e){
                Toast.makeText(this, "Error capturado "+ e,Toast.LENGTH_LONG).show();
            }



        }else{
            Toast.makeText(this, "Usuario y/o contraseña incorrecto/s o vacío ",Toast.LENGTH_LONG).show();
        }


    }


}