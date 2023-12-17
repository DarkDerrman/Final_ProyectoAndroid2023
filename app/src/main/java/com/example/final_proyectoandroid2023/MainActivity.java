package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.final_proyectoandroid2023.db.DataBaseUsuarios;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    EditText etCorreo, etPassword;
    Button btnRegistrar, btnAcceder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnRegistrar = (Button) findViewById(R.id.btnRegistrarMain);
        btnAcceder = (Button) findViewById(R.id.btnAcceder);

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

                etCorreo = (EditText) findViewById(R.id.etCorreoSesion);
                etPassword = (EditText) findViewById(R.id.etPasswordSesion);

                String correo = etCorreo.getText().toString();
                String password = etPassword.getText().toString();

                // Se verifica correo y contraseña
                if(!correo.isEmpty()){
                    datosUsuario = db.obtenerCorreoYContrasena(correo);
                    if(datosUsuario != null){
                        // Usuario encontrado, correo y password.
                        if(!password.isEmpty()){
                            if(password.equals(datosUsuario[1])){
                                Snackbar.make(v,"Inicio de sesión correcta",Snackbar.LENGTH_LONG).show();
                                etCorreo.setText("");
                                etPassword.setText("");
                                Intent intent = new Intent(MainActivity.this,SharedPreferentsActivity.class);
                                intent.putExtra("nombre",datosUsuario[2]);
                                intent.putExtra("correo",datosUsuario[0]);
                                startActivity(intent);
                            }else{
                                Snackbar.make(v, "Contraseña no es correcta", Snackbar.LENGTH_LONG).show();
                            }
                        }else{
                            Snackbar.make(v, "Contraseña no estar vacia", Snackbar.LENGTH_LONG).show();
                        }
                    }else{
                        Snackbar.make(v, "Usuario no registrado", Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(v, "Correo no puede estar vacio", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        etCorreo = (EditText) findViewById(R.id.etCorreoSesion);
        etCorreo.requestFocus();
    }
}