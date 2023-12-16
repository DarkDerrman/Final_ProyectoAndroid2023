package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_proyectoandroid2023.db.DataBaseMascotas;
import com.google.android.material.snackbar.Snackbar;

public class ContactoDuenoActivity extends AppCompatActivity {
    TextView tvNombreContacto;
    TextView tvCorreoContacto;
    TextView tvTelefonoContacto;
    long idDueno;
    String[] contactoDueno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_dueno);

        tvNombreContacto = (TextView) findViewById(R.id.tvNombreContacto);
        tvCorreoContacto = (TextView) findViewById(R.id.tvCorreoContacto);
        tvTelefonoContacto = (TextView) findViewById(R.id.tvTelefonoContacto);

        contactoDueno = new String[3];

        DataBaseMascotas dbmascotas = new DataBaseMascotas(ContactoDuenoActivity.this);

        Intent intent = getIntent();
        if (intent != null) {
            idDueno = intent.getLongExtra("idDueno",-2);
            if(idDueno == -2){
                Toast.makeText(this,"El id del dueño no encontrado",Toast.LENGTH_LONG).show();
            }else{
                contactoDueno = dbmascotas.obtenerDuenoPorId(idDueno);
                tvNombreContacto.setText(contactoDueno[0]);
                tvCorreoContacto.setText(contactoDueno[1]);
                tvTelefonoContacto.setText(contactoDueno[2]);
            }
        }else{
            Toast.makeText(this,"Conexión perdida entre activities",Toast.LENGTH_LONG).show();
        }
    }
}