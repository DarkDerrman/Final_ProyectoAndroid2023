/*
1) Envio de datos simples (nombre y correo del usuario) desde MainActivity hacia aqui
2) Crear SharedPreferents.
 */

package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class SharedPreferentsActivity extends AppCompatActivity {
    TextView tvNombre, tvCorreo;
    EditText etSeudonimo, etEdad;
    Button btnGuardar;
    String nombre;
    String correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferents);

        tvNombre = (TextView) findViewById(R.id.tvNombrePreferencias);
        tvCorreo = (TextView) findViewById(R.id.tvCorreoPreferencias);
        btnGuardar = (Button) findViewById(R.id.btnGuardarPreferencias);
        etSeudonimo = (EditText) findViewById(R.id.etSeudonimoPreferencias);
        etEdad = (EditText) findViewById(R.id.etEdadPreferencias);

        // Recuperacion de envio de datos simples
        Bundle datos = getIntent().getExtras();
        nombre = datos.getString("nombre");
        correo = datos.getString("correo");

        tvNombre.setText(nombre);
        tvCorreo.setText(correo);

        // Obtener una referencia a SharedPreferents
        SharedPreferences myShared = getSharedPreferences("misDatos",MODE_PRIVATE);
        // Editar SharedPreferents a través de un objeto SharedPreferents.Editor
        SharedPreferences.Editor editor = myShared.edit();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seudonimo = etSeudonimo.getText().toString();
                String edad = etEdad.getText().toString();

                if(!seudonimo.isEmpty()){
                    if(!edad.isEmpty()){
                        if (esNumeroEntero(edad)) {
                            int numeroEntero = Integer.parseInt(edad);
                            // El String es un número entero válido.
                            if(0 < numeroEntero && numeroEntero <= 300){
                                // Agregar datos clave-valor
                                editor.putString("nombre",nombre);
                                editor.putString("correo",correo);
                                editor.putString("seudonimo",seudonimo);
                                editor.putString("edad",edad);
                                editor.commit();
                                Snackbar.make(v,"Preferencias guardadas",Snackbar.LENGTH_LONG).show();

                                // Iniciar la actividad MenuActivity
                                Intent intent = new Intent(SharedPreferentsActivity.this,MenuActivity.class);
                                startActivity(intent);
                                Snackbar.make(v,"Preferencias guardadas",Snackbar.LENGTH_LONG).show();

                            }else{
                                Snackbar.make(v,"Edad debe ser mayor a 1 y menor o igual a 300 años",Snackbar.LENGTH_LONG).show();
                            }
                        } else {
                            // El String no es un número entero válido.
                            Snackbar.make(v,"La edad ingresada no es numerica",Snackbar.LENGTH_LONG).show();
                        }
                    }else{
                        Snackbar.make(v,"La edad no puede estar vacia",Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(v,"El seudonimo no puede estar vacio",Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean esNumeroEntero(String str) {
        try {
            // Intenta convertir el String a un número entero.
            Integer.parseInt(str);
            return true; // Si tiene éxito, es un número entero válido.
        } catch (NumberFormatException e) {
            return false; // Si ocurre una excepción, no es un número entero válido.
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();

        etSeudonimo = (EditText) findViewById(R.id.etSeudonimoPreferencias);
        etSeudonimo.requestFocus();
    }
}