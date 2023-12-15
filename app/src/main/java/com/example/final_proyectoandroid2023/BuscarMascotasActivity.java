package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.final_proyectoandroid2023.db.DataBaseMascotas;

import java.util.ArrayList;

public class BuscarMascotasActivity extends AppCompatActivity {
    ArrayList<Mascotas> mascotas;
    RecyclerView rvlistaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_mascotas);

        DataBaseMascotas db = new DataBaseMascotas(BuscarMascotasActivity.this);

        mascotas = db.obtenerMascotas();
        rvlistaMascotas = findViewById(R.id.rvBuscarM);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvlistaMascotas.setLayoutManager(llm);

        MascotasAdapter adaptador = new MascotasAdapter(mascotas, this);
        rvlistaMascotas.setAdapter(adaptador);

        adaptador.setOnItemClickListener(new MascotasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(BuscarMascotasActivity.this, DetallesMascotaActivity.class);
                intent.putExtra("mascota", position);
                startActivity(intent);
            }
        });
    }

}