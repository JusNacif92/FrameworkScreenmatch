package com.aluracursos.screenmatch.modelos;

import com.aluracursos.screenmatch.calculos.Clasificacion;

public class Episodio implements Clasificacion {
    private int numero;
    private String nombre;
    private Series serie;
    private int totalDeVisualizaciones;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Series getSerie() {
        return serie;
    }

    public void setSerie(Series serie) {
        this.serie = serie;
    }

    public int getTotalDeVisualizaciones() {
        return totalDeVisualizaciones;
    }

    public void setTotalDeVisualizaciones(int totalDeVisualizaciones) {
        this.totalDeVisualizaciones = totalDeVisualizaciones;
    }

    @Override
    public int getClasificacion() {
        if(totalDeVisualizaciones > 100){
            return 4;
        }else{
            return 2;
        }
    }
}
