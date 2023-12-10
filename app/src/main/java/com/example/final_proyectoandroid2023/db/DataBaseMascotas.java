package com.example.final_proyectoandroid2023.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}
