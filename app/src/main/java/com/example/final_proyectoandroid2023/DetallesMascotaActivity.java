package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetallesMascotaActivity extends AppCompatActivity {

    TextView tvNombre;
    TextView tvDesc;
    TextView tvEspecie;
    TextView tvRaza;
    TextView tvEdad;
    TextView tvSexo;
    TextView tvTamano;
    TextView tvVacuna;
    TextView tvSalud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_mascota);

        tvNombre = (TextView) findViewById(R.id.tvNombreDetalle);
        tvDesc = (TextView) findViewById(R.id.tvDescripcionDetalle);
        tvEspecie = (TextView) findViewById(R.id.tvEspecieDetalle);
        tvRaza = (TextView) findViewById(R.id.tvRazaDetalle);
        tvEdad = (TextView) findViewById(R.id.tvEdadDetalle);
        tvSexo = (TextView) findViewById(R.id.tvSexoDetalle);
        tvTamano = (TextView) findViewById(R.id.tvTamanoDetalle);
        tvVacuna = (TextView) findViewById(R.id.tvVacunaDiaDetalle);
        tvSalud = (TextView) findViewById(R.id.tvSanoDetalle);

        Intent intent = getIntent();
        if (intent != null){
            Mascotas mascota = (Mascotas) intent.getSerializableExtra("mascota");
            if (mascota != null){
                tvNombre.setText(mascota.getNombreMascota());
                tvDesc.setText(mascota.getDescripcion());
                tvEspecie.setText(mascota.getEspecie());
                tvRaza.setText(mascota.getRaza());
                tvEdad.setText(mascota.getEdad());
                tvSexo.setText(mascota.getSexo());
                tvTamano.setText(mascota.getTamano());
                tvVacuna.setText(mascota.getVacunaDia());
                tvSalud.setText(mascota.getSano());
            }
        }
    }

    /*
    private Object setVisible(int invisible) {
        Object resultado = false;
        if(invisible == 4){

        }
    }

     */
}