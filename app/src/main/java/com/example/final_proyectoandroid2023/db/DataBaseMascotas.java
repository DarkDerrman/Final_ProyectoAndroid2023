package com.example.final_proyectoandroid2023.db;

import android.annotation.SuppressLint;
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
        super(context, "MascotasDBTempCinco", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryDuenos = "CREATE TABLE Duenos(" +
                "id_dueno INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "correo TEXT," +
                "telefono TEXT);";

        String queryMascotas = "CREATE TABLE Mascotas(" +
                "id_mascota INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_dueno INTEGER," +
                "nombre_mascota TEXT," +
                "descripcion TEXT," +
                "especie TEXT," +
                "raza TEXT," +
                "edad TEXT," +
                "sexo TEXT," +
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
        // Devuelve el id_dueno
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

    @SuppressLint("Range")
    public long obtenerIdDuenoPorCorreo(String correo){
        // Devuelve -2 si no hay Dueno y si hay devuelve el id_dueno

        SQLiteDatabase db = this.getWritableDatabase();
        long idDueno = -2;

        // Consulta la tabla Duenos
        String query = "SELECT id_dueno FROM Duenos WHERE correo = ?";
        Cursor registro = db.rawQuery(query, new String[]{correo});

        // Verificar si se encontro el dueno
        if(registro.moveToFirst()){
            idDueno = registro.getInt(registro.getColumnIndex("id_dueno"));
        }

        // Cerrar el registro y la base de datos
        registro.close();
        db.close();

        return idDueno;
    }

    @SuppressLint("Range")
    public String[] obtenerDuenoPorId(long idDueno){
        // Devuelve los datos de contacto del dueno de la mascota, por su id_dueno

        String query = "SELECT * FROM Duenos WHERE id_dueno = ?";
        String[] resultado = new String[3];
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registro;
        String[] argumentos = {String.valueOf(idDueno)};
        registro = db.rawQuery(query,argumentos);

        if(registro.moveToFirst()){
            resultado[0] = registro.getString(registro.getColumnIndex("nombre")); // Nombre
            resultado[1] = registro.getString(registro.getColumnIndex("correo")); // Correo
            resultado[2] = registro.getString(registro.getColumnIndex("telefono")); // Telefono
            registro.close();
        }

        db.close();

        return resultado;
    }

    public ArrayList<Mascotas> obtenerMascotasDelDueno(long idDueno){
        // Devuelve las mascotas del Dueno correspondiente

        String query = "SELECT * FROM Mascotas WHERE id_dueno = ?";
        ArrayList<Mascotas> listaMascotas = new ArrayList<Mascotas>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros;
        String[] argumentos = {String.valueOf(idDueno)};
        registros = db.rawQuery(query,argumentos);

        while (registros.moveToNext()){
            Mascotas mascotaTemp = new Mascotas();
            mascotaTemp.setIdMascota(registros.getInt(0));
            mascotaTemp.setIdDueno(registros.getInt(1));
            mascotaTemp.setNombreMascota(registros.getString(2));
            mascotaTemp.setDescripcion(registros.getString(3));
            mascotaTemp.setEspecie(registros.getString(4));
            mascotaTemp.setRaza(registros.getString(5));
            mascotaTemp.setEdad(registros.getString(6));
            mascotaTemp.setSexo(registros.getString(7));
            mascotaTemp.setTamano(registros.getString(8));
            mascotaTemp.setVacunaDia(registros.getString(9));
            mascotaTemp.setSano(registros.getString(10));
            listaMascotas.add(mascotaTemp);
        }
        registros.close();
        db.close();
        return  listaMascotas;
    }

    public ArrayList<Mascotas> obtenerMascotas(){
        // devuelve todas las mascotas

        String query = "SELECT * FROM Mascotas";
        ArrayList<Mascotas> listaMascotas = new ArrayList<Mascotas>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros;
        registros = db.rawQuery(query,null);

        while (registros.moveToNext()){
            Mascotas mascotaTemp = new Mascotas();
            mascotaTemp.setIdMascota(registros.getInt(0));
            mascotaTemp.setIdDueno(registros.getInt(1));
            mascotaTemp.setNombreMascota(registros.getString(2));
            mascotaTemp.setDescripcion(registros.getString(3));
            mascotaTemp.setEspecie(registros.getString(4));
            mascotaTemp.setRaza(registros.getString(5));
            mascotaTemp.setEdad(registros.getString(6));
            mascotaTemp.setSexo(registros.getString(7));
            mascotaTemp.setTamano(registros.getString(8));
            mascotaTemp.setVacunaDia(registros.getString(9));
            mascotaTemp.setSano(registros.getString(10));
            listaMascotas.add(mascotaTemp);
        }
        registros.close();
        db.close();
        return  listaMascotas;
    }
}
