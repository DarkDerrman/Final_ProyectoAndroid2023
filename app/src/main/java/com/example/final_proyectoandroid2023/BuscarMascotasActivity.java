package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.final_proyectoandroid2023.db.DataBaseMascotas;

import java.util.ArrayList;

public class BuscarMascotasActivity extends AppCompatActivity {
    ArrayList<Mascotas> listaPerros;
    ArrayList<Mascotas> listaGatos;

    RecyclerView rvlistaMascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_mascotas);

        DataBaseMascotas db_mascotas = new DataBaseMascotas(BuscarMascotasActivity.this);

        ArrayList<Mascotas> listaMascotas = db_mascotas.obtenerMascotas();

        rvlistaMascotas = (RecyclerView) findViewById(R.id.listBuscarM);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvlistaMascotas.setLayoutManager(llm);
        startAdaptador();

    }

    public void startAdaptador(){
        MascotasAdapter adaptador = new MascotasAdapter(listaMascotas);
        rvlistaMascotas.setAdapter(adaptador);
    }
}