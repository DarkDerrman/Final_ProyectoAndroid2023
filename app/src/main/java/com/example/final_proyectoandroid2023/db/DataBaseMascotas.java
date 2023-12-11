package com.example.final_proyectoandroid2023.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.final_proyectoandroid2023.Mascotas;

import java.util.ArrayList;

public class DataBaseMascotas extends SQLiteOpenHelper {
    Context context;
    public DataBaseMascotas(@Nullable Context context) {
        super(context, "MascotasDB", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryDuenos = "CREATE TABLE Duenos(" +
                "id_dueno INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_dueno TEXT," +
                "correo TEXT," +
                "telefono TEXT);";

        String queryMascotas = "CREATE TABLE Mascotas(" +
                "id_mascota INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_dueno INTEGER," +
                "nombre_mascota TEXT," +
                "ruta_foto TEXT," +
                "descripcion TEXT," +
                "especie TEXT," +
                "edad TEXT," +
                "tamano TEXT," +
                "vacuna_dia VARCHAR(2)," +
                "sano VARCHAR(2)," +
                "FOREIGN KEY (id_dueno) REFERENCES Duenos(id_dueno));";

        db.execSQL(queryDuenos);
        db.execSQL(queryMascotas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Duenos");
        db.execSQL("DROP TABLE IF EXISTS Mascotas");
        onCreate(db);
    }

    public long agregarDueno(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        long idDueno = db.insert("Duenos",null,contentValues);
        db.close();
        return idDueno;
    }

    public long agregarMascota(ContentValues contentValues, long idDueno){
        SQLiteDatabase db = this.getWritableDatabase();
        long idMascota = db.insert("Mascotas",null,contentValues);
        db.close();
        return idMascota;
    }

    public ArrayList<Mascotas> obtenerMascotas(String especie){
        // especie puede ser "Perro" o "Gato"
        String queryPerros = "SELECT * FROM Mascotas WHERE especie = 'Perro'";
        String queryGatos = "SELECT * FROM Mascotas WHERE especie = 'Gato'";
        ArrayList<Mascotas> listaMascotas = new ArrayList<Mascotas>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros;
        if(especie.equals("Perro")){
            registros = db.rawQuery(queryPerros,null);
        }else{
            registros = db.rawQuery(queryGatos,null);
        }
        while (registros.moveToNext()){
            Mascotas mascotaTemp = new Mascotas();
            mascotaTemp.setIdMascota(registros.getInt(0));
            mascotaTemp.setIdDueno(registros.getInt(1));
            mascotaTemp.setNombreMascota(registros.getString(2));
            mascotaTemp.setRutaFoto(registros.getString(3));
            mascotaTemp.setDescripcion(registros.getString(4));
            mascotaTemp.setEspecie(registros.getString(5));
            mascotaTemp.setEdad(registros.getString(6));
            mascotaTemp.setTamano(registros.getString(7));
            mascotaTemp.setVacunaDia(registros.getString(8));
            mascotaTemp.setSano(registros.getString(9));
            listaMascotas.add(mascotaTemp);
        }
        registros.close();
        db.close();
        return  listaMascotas;
    }
}
