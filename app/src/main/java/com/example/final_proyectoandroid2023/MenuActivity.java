package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView tvNombreMenu = (TextView) findViewById(R.id.tvNombreMenu);

        SharedPreferences myShared = getSharedPreferences("misDatos",MODE_PRIVATE);
        String nombre = myShared.getString("nombre","No se encontro nombre");

        tvNombreMenu.setText(nombre);
    }
}