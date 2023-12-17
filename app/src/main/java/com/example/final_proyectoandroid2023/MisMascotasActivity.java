package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.final_proyectoandroid2023.db.DataBaseMascotas;

import java.util.ArrayList;

public class MisMascotasActivity extends AppCompatActivity {
    TextView tvMensaje;
    TextView tvNick;
    ListView lvListaMisMascotas;
    long idDueno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_mascotas);

        tvNick = (TextView) findViewById(R.id.tvNickMisMascotas);
        tvMensaje = (TextView) findViewById(R.id.tvMensajeSinMascotas);
        tvMensaje.setVisibility(View.GONE);

        DataBaseMascotas dbmascotas = new DataBaseMascotas(MisMascotasActivity.this);

        SharedPreferences myShared = getSharedPreferences("misDatos",MODE_PRIVATE);
        String correo = myShared.getString("correo","No se encontró correo");
        String seudonimo = myShared.getString("seudonimo","No se encontró seudonimo");
        String edad = myShared.getString("edad","No se encontró edad");

        String nick = seudonimo + "(" + edad + ")";

        tvNick.setText(nick);

        idDueno = dbmascotas.obtenerIdDuenoPorCorreo(correo);

        if(idDueno == -2){
            tvMensaje.setVisibility(View.VISIBLE);
        }else{
            ArrayList<Mascotas> mascotas = dbmascotas.obtenerMascotasDelDueno(idDueno);

            ArrayList<String> nombresMascota = new ArrayList<String>();

            for (Mascotas mascota: mascotas
                 ) {
                nombresMascota.add(mascota.getNombreMascota());
            }
            lvListaMisMascotas = (ListView) findViewById(R.id.lvMisMascotas);
            lvListaMisMascotas.setAdapter(new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    nombresMascota));

            lvListaMisMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MisMascotasActivity.this, DetallesMascotaActivity.class);
                    intent.putExtra("mascota",mascotas.get(position));
                    startActivity(intent);
                }
            });
        }
    }
}