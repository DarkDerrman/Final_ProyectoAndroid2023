package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.final_proyectoandroid2023.db.DataBaseUsuarios;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnRegistrar = (Button) findViewById(R.id.btnRegistrarMain);
        Button btnAcceder = (Button) findViewById(R.id.btnAcceder);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class );
                startActivity(intent);
            }
        });
        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseUsuarios db = new DataBaseUsuarios(MainActivity.this);
                String[] datosUsuario;

                EditText etCorreo = (EditText) findViewById(R.id.etCorreoSesion);
                EditText etPassword = (EditText) findViewById(R.id.etPasswordSesion);

                String correo = etCorreo.getText().toString();
                String password = etPassword.getText().toString();

                // Se verifica correo y contraseña
                if(!correo.isEmpty()){
                    if(!password.isEmpty()){
                        datosUsuario = db.obtenerCorreoYContrasena(correo);
                        if(datosUsuario != null){
                            if(password.equals(datosUsuario[1])){
                                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                                startActivity(intent);
                            }else{
                                Snackbar.make(v, "Contraseña no es correcta", Snackbar.LENGTH_LONG).show();
                            }
                        }else{
                            Snackbar.make(v, "Usuario no registrado", Snackbar.LENGTH_LONG).show();
                        }
                    }else{
                        Snackbar.make(v, "Campo contraseña no puede estar vacio", Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(v, "Campo correo no puede estar vacio", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        EditText etCorreo = (EditText) findViewById(R.id.etCorreoSesion);
        EditText etPassword = (EditText) findViewById(R.id.etPasswordSesion);

        etPassword.setText("");
        etCorreo.setText("");

    }
}