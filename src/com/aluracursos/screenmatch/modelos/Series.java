package com.aluracursos.screenmatch.modelos;

public class Series extends Pelicula {
    int temporada;
    int episodiosPorTemporada;
    int minutosPorEpisodio;

    public Series(String nombre, int fechaDeLanzamiento) {
        super(nombre, fechaDeLanzamiento);
    }

    public int getTemporada() {
        return temporada;
    }

    @Override
    public int getDuracionEnMinutos() {
        return temporada*episodiosPorTemporada*minutosPorEpisodio;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public int getEpisodiosPorTemporada() {
        return episodiosPorTemporada;
    }

    public void setEpisodiosPorTemporada(int episodiosPorTemporada) {
        this.episodiosPorTemporada = episodiosPorTemporada;
    }

    public int getMinutosPorEpisodio() {
        return minutosPorEpisodio;
    }

    public void setMinutosPorEpisodio(int minutosPorEpisodio) {
        this.minutosPorEpisodio = minutosPorEpisodio;
    }

    @Override
    public String toString() {
        return "Serie: "+this.getNombre()+"("+this.getFechaDeLanzamiento()+")";
    }
}
