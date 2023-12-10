package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.final_proyectoandroid2023.db.DataBaseUsuarios;
import com.google.android.material.snackbar.Snackbar;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Agregar usuario a la Base de Datos
        Button btnRegistrar = (Button) findViewById(R.id.btnRegistrarRegistro);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseUsuarios db = new DataBaseUsuarios(RegistroActivity.this);

                EditText etNombre = (EditText) findViewById(R.id.etNombre);
                EditText etTelefono = (EditText) findViewById(R.id.etTelefono);
                EditText etCorreo = (EditText) findViewById(R.id.etCorreo);
                EditText etPassword = (EditText) findViewById(R.id.etPassword);
                EditText etPasswordRep = (EditText) findViewById(R.id.etPasswordRep);

                String nombre = etNombre.getText().toString();
                String telefono = etTelefono.getText().toString();
                String correo = etCorreo.getText().toString();
                String password = etPassword.getText().toString();
                String passwordRep = etPasswordRep.getText().toString();

                // Crear y poner campos en contentValues
                ContentValues contentValues = new ContentValues();

                // Primero se verifica que los campos no sean vacio
                // Luego se verifica que no sean blancos.
                if(!nombre.isEmpty()){
                    if(!telefono.isEmpty()){
                        if(!correo.isEmpty()){
                            if(!password.isEmpty()){
                                if(password.equals(passwordRep)){
                                    // Despues de pasar las comprobaciones se ejecuta este bloque
                                    contentValues.put("nombre", etNombre.getText().toString());
                                    contentValues.put("telefono", etTelefono.getText().toString());
                                    contentValues.put("correo", etCorreo.getText().toString());
                                    contentValues.put("password", etPassword.getText().toString());

                                    // Agregar el contentValues a la Base de Datos
                                    db.agregarUsuario(contentValues);

                                    // Limpiar campos formulario Registro
                                    etNombre.setText("");
                                    etTelefono.setText("");
                                    etCorreo.setText("");
                                    etPassword.setText("");
                                    etPasswordRep.setText("");

                                    Snackbar.make(v, "Usuario Ingresado", Snackbar.LENGTH_LONG).show();

                                    // Uso de intent, de regreso a MainActivity, iniciar sesion.
                                    Intent intent = new Intent(RegistroActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Snackbar.make(v, "La contraseña repetida es distinta a la contraseña", Snackbar.LENGTH_LONG).show();
                                }
                            }else{
                                Snackbar.make(v, "Campo contraseña no puede estar vacio", Snackbar.LENGTH_LONG).show();
                            }
                        }else{
                            Snackbar.make(v, "Campo correo no puede estar vacio", Snackbar.LENGTH_LONG).show();
                        }
                    }else{
                        Snackbar.make(v, "Campo telefono no puede estar vacio", Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(v, "Campo nombre no puede estar vacio", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}