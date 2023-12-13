package com.example.final_proyectoandroid2023.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseUsuarios extends SQLiteOpenHelper {
    Context context;
    public DataBaseUsuarios(@Nullable Context context) {
        super(context, "UsuariosDBTempDos", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE Usuarios(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "telefono TEXT," +
                "correo TEXT," +
                "password TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        onCreate(db);
    }

    public void agregarUsuario(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Usuarios", null, contentValues);
        db.close();
    }

    @SuppressLint("Range")
    public String[] obtenerCorreoYContrasena(String correo) {
        // Funcion modificada para obtener correo, contrasena y nombre.
        // Obtener una instancia de la base de datos en modo de solo escritura
        //SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();

        // Crear un array de String para almacenar el resultado (correo y contraseña)
        String[] resultado = new String[3];

        // Ejecutar una consulta en la base de datos
        Cursor registros = db.query("Usuarios", new String[]{"correo", "password", "nombre"}, "correo" + "=?",
                new String[]{correo}, null, null, null, null);

        // Verificar si se encontraron resultados en Registros
        if (registros != null && registros.moveToFirst()) {
            // Si hay resultados, obtener el correo y la contraseña del usuario
            //resultado[0] = registros.getString(2).toString(); // Correo
            //resultado[1] = registros.getString(3).toString(); // Contraseña

            resultado[0] = registros.getString(registros.getColumnIndex("correo")); // Correo
            resultado[1] = registros.getString(registros.getColumnIndex("password")); // Contraseña
            resultado[2] = registros.getString(registros.getColumnIndex("nombre")); // Nombre


            // Cerrar el cursor para liberar recursos
            registros.close();
        } else {
            // Si no se encontraron resultados, establecer el resultado como null
            resultado = null;
        }

        // Cerrar la conexion a la base de datos
        db.close();

        // Devolver el resultado (puede ser null si no se encontraron resultados)
        return resultado;
    }

    @SuppressLint("Range")
    public String[] obtenerUsuarioPorCorreo(String correo){
        // Obtiene los datos del usuario al que le corresponde el correo
        SQLiteDatabase db = this.getWritableDatabase();

        String[] resultado = new String[3];

        Cursor registros = db.query("Usuarios", new String[]{"nombre", "telefono", "correo"}, "correo" + "=?",
                new String[]{correo}, null, null, null, null);

        if (registros != null && registros.moveToFirst()) {
            // Si hay resultados, obtener el nombre, telefono y el correo del usuario
            //resultado[0] = registros.getString(2).toString(); // Correo
            //resultado[1] = registros.getString(3).toString(); // Contraseña

            resultado[0] = registros.getString(registros.getColumnIndex("nombre")); // Nombre
            resultado[1] = registros.getString(registros.getColumnIndex("correo")); // Correo
            resultado[2] = registros.getString(registros.getColumnIndex("telefono")); // Telefono

            // Cerrar el cursor para liberar recursos
            registros.close();
        } else {
            // Si no se encontraron resultados, establecer el resultado como null
            resultado = null;
        }

        // Cerrar la conexion a la base de datos
        db.close();

        // Devolver el resultado (puede ser null si no se encontraron resultados)
        return resultado;
    }

}
