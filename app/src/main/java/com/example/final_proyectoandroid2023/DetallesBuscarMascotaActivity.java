package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetallesBuscarMascotaActivity extends AppCompatActivity {
    TextView tvNombre;
    TextView tvDesc;
    TextView tvEspecie;
    TextView tvRaza;
    TextView tvEdad;
    TextView tvSexo;
    TextView tvTamano;
    TextView tvVacuna;
    TextView tvSalud;
    Button btnContactoDueno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_buscar_mascota);

        tvNombre = (TextView) findViewById(R.id.tvNombreDetalleBuscar);
        tvDesc = (TextView) findViewById(R.id.tvDescripcionDetalleBuscar);
        tvEspecie = (TextView) findViewById(R.id.tvEspecieDetalleBuscar);
        tvRaza = (TextView) findViewById(R.id.tvRazaDetalleBuscar);
        tvEdad = (TextView) findViewById(R.id.tvEdadDetalleBuscar);
        tvSexo = (TextView) findViewById(R.id.tvSexoDetalleBuscar);
        tvTamano = (TextView) findViewById(R.id.tvTamanoDetalleBuscar);
        tvVacuna = (TextView) findViewById(R.id.tvVacunaDiaDetalleBuscar);
        tvSalud = (TextView) findViewById(R.id.tvSanoDetalleBuscar);
        btnContactoDueno = (Button) findViewById(R.id.btnContactoDueno);

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
}