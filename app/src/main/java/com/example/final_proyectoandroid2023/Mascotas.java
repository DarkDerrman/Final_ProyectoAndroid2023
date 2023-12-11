package com.example.final_proyectoandroid2023;

import java.io.Serializable;

public class Mascotas implements Serializable {
    private int idMascota,idDueno;
    private String nombreMascota,rutaFoto,descripcion,especie,edad,tamano,vacunaDia,sano;

    public Mascotas(){
    }

    public Mascotas(String nombreMascota, String rutaFoto, String descripcion, String especie, String edad, String tamano, String vacunaDia, String sano) {
        this.nombreMascota = nombreMascota;
        this.rutaFoto = rutaFoto;
        this.descripcion = descripcion;
        this.especie = especie;
        this.edad = edad;
        this.tamano = tamano;
        this.vacunaDia = vacunaDia;
        this.sano = sano;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public int getIdDueno() {
        return idDueno;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEspecie() {
        return especie;
    }

    public String getEdad() {
        return edad;
    }

    public String getTamano() {
        return tamano;
    }

    public String getVacunaDia() {
        return vacunaDia;
    }

    public String getSano() {
        return sano;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public void setVacunaDia(String vacunaDia) {
        this.vacunaDia = vacunaDia;
    }

    public void setSano(String sano) {
        this.sano = sano;
    }
}
