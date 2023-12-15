package com.example.final_proyectoandroid2023;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotasAdapter extends RecyclerView.Adapter<MascotasAdapter.MascotasViewHolder> {
    private ArrayList<Mascotas> mascotas;
    private Activity activity;
    public MascotasAdapter(ArrayList<Mascotas> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MascotasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotas, parent, false);
        return new MascotasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotasViewHolder mascotasViewHolder, int position) {
        Mascotas mascota = mascotas.get(position);
        mascotasViewHolder.cvNombre.setText(mascota.getNombreMascota());
        mascotasViewHolder.cvEspecie.setText(mascota.getEspecie());
        mascotasViewHolder.cvTamano.setText(mascota.getTamano());

        mascotasViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, mascota.getNombreMascota(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, DetallesBuscarMascotaActivity.class);
                intent.putExtra("mascota", mascota);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
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

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener clickListen;

    public void setOnItemClickListener(OnItemClickListener listener){
        clickListen = listener;
    }
}
