package com.aluracursos.screenmatch.modelos;

import com.aluracursos.screenmatch.ErrorEnConversionDeDuracionException;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable <Titulo> {
    @SerializedName("Title")
    //Atributos de la calse madre
    private String nombre;
    @SerializedName("Year")
    private int fechaDeLanzamiento;
    private int  duracionEnMinutos;
    private boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDeLasEvaluaciones;

    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Titulo(TituloOmdb miTituloOmdb) throws ErrorEnConversionDeDuracionException {
        this.nombre = miTituloOmdb.title();
        this.fechaDeLanzamiento = Integer.valueOf(miTituloOmdb.year());
        if (miTituloOmdb.runtime().contains("N/A")){
            throw new ErrorEnConversionDeDuracionException("No pude convertir la duracion, porque contiene un N/A");
        }
        this.duracionEnMinutos = Integer.valueOf(
                miTituloOmdb.runtime().substring(0,3).replace(" ","")
        );
    }
    //Metodo publico. Getter para accerder al nombre de la pelicula.
    public String getNombre() {
        return nombre;
    }
    //Metodo publico. Getter para acceder a la fecha de lanzamiento.
    public int getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }
    //Metodo publico. Getter para acceder a la duracion de la pelicula
    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }
    //Metodo publico para saber si esta en el plan o no.
    public boolean isIncluidoEnElPlan() {
        return incluidoEnElPlan;
    }
    //Setter para modificar el atributo noombre en la aplicacion
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //Setter para modificar la fecha de lanzamiento en la aplicacion
    public void setFechaDeLanzamiento(int fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }
    //Setter para modificar la duracion de la pelicula
    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }
    //Setter para modificar el plan Incluido
    public void setInclueidoEnElPlan(boolean inclueidoEnElPlan) {
        this.incluidoEnElPlan = inclueidoEnElPlan;
    }
    //Ficha tecnica con los atributos asignados a ella
    public void muestraFichaTecnica(){
        System.out.println("El nombre de la pelicula es: "+ nombre);
        System.out.println("Su fecha de lanzamiento es: "+ fechaDeLanzamiento);
        System.out.println("Duracion en minutos: "+duracionEnMinutos);
    }
    //Metodo get para acceder al valor del atributo
    public int getTotalDeLasEvaluaciones (){
        return totalDeLasEvaluaciones;
    }
    //metodo para calcular el total de evaluaciones
    public void evalua (double nota){
        sumaDeLasEvaluaciones += nota;
        totalDeLasEvaluaciones++;
    }
    //metodo para obttner la media de la pelicula
    public double calculaMedia() {
        return sumaDeLasEvaluaciones / totalDeLasEvaluaciones;
    }

    @Override
    public int compareTo(Titulo otroTitulo) {
        return this.getNombre().compareTo(otroTitulo.getNombre());
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento;
    }
}
