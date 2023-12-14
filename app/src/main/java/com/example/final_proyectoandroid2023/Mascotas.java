package com.example.final_proyectoandroid2023;

import java.io.Serializable;

public class Mascotas implements Serializable {
    private int idMascota,idDueno;
    private String nombreMascota,descripcion,especie,raza,edad,sexo,tamano,vacunaDia,sano;

    public Mascotas(){
    }

    /*public Mascotas(String nombreMascota, String descripcion, String especie, String raza, String edad, String sexo, String tamano, String vacunaDia, String sano) {
        this.nombreMascota = nombreMascota;
        this.descripcion = descripcion;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.sexo = sexo;
        this.tamano = tamano;
        this.vacunaDia = vacunaDia;
        this.sano = sano;
    }*/

    public Mascotas(String nombreMascota, String especie, String tamano){
        this.nombreMascota = nombreMascota;
        this.especie = especie;
        this.tamano = tamano;
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

    public String getDescripcion() {
        return descripcion;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public String getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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
