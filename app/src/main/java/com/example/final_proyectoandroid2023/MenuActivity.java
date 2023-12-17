package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView tvNombreMenu = (TextView) findViewById(R.id.tvNombreMenu);

        SharedPreferences myShared = getSharedPreferences("misDatos",MODE_PRIVATE);
        String nombre = myShared.getString("nombre","No se encontro nombre");

        String nombreS = "(" + nombre + ")";

        tvNombreMenu.setText(nombreS);

        Button btnRegistrar = (Button) findViewById(R.id.btnRegistrarMascotas);
        Button btnMisMascotas = (Button) findViewById(R.id.btnMisMascotas);
        Button btnBuscar = (Button) findViewById(R.id.btnBuscarMascotas);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RegistroMascotasActivity.class);
                startActivity(intent);
            }
        });

        btnMisMascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MisMascotasActivity.class);
                startActivity(intent);
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, BuscarMascotasActivity.class);
                startActivity(intent);
            }
        });

    }
}