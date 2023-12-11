package com.example.final_proyectoandroid2023;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotasAdapter extends RecyclerView.Adapter<MascotasAdapter.MascotasViewHolder> {
    private ArrayList<Mascotas> obtenerMascotas;
    public MascotasAdapter(ArrayList<Mascotas> obtenerMascotas){
        this.obtenerMascotas = obtenerMascotas;
    }
    @NonNull
    @Override
    public MascotasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotas, parent, false);
        return new MascotasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotasViewHolder mascotasViewHolder, int position) {
        Mascotas mascota = obtenerMascotas.get(position);
        mascotasViewHolder.cvNombre.setText(mascota.getNombreMascota());
        mascotasViewHolder.cvEspecie.setText(mascota.getEspecie());
        mascotasViewHolder.cvTamano.setText(mascota.getTamano());
    }

    @Override
    public int getItemCount() {
        return obtenerMascotas.size();
    }

    public static class MascotasViewHolder extends RecyclerView.ViewHolder {
        TextView cvNombre;
        TextView cvEspecie;
        TextView cvTamano;

        public MascotasViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cvNombre = itemView.findViewById(R.id.cvNombre);
            this.cvEspecie = itemView.findViewById(R.id.cvEspecie);
            this.cvTamano = itemView.findViewById(R.id.cvTamano);
        }
    }
}
