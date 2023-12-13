package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RegistroMascotasActivity extends AppCompatActivity {
    EditText etNombreMascota, etDescripcionMascota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascotas);
    }
}