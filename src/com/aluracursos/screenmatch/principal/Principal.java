package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.calculos.CalculadoraDeTiempo;
import com.aluracursos.screenmatch.calculos.FiltroDeRecomendaciones;
import com.aluracursos.screenmatch.modelos.Episodio;
import com.aluracursos.screenmatch.modelos.Pelicula;
import com.aluracursos.screenmatch.modelos.Series;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto",2021);
        miPelicula.setDuracionEnMinutos(120);
        miPelicula.setInclueidoEnElPlan(true);

        miPelicula.muestraFichaTecnica();
        miPelicula.evalua(10);
        miPelicula.evalua(10);
        miPelicula.evalua(7.8);

        System.out.println(miPelicula.getTotalDeLasEvaluaciones());
        System.out.println(miPelicula.calculaMedia());

        Series casaDragon = new Series("La casa del Dragon",2018);
        casaDragon.setTemporada(1);
        casaDragon.setMinutosPorEpisodio(50);
        casaDragon.setEpisodiosPorTemporada(10);
        casaDragon.muestraFichaTecnica();
        System.out.println(casaDragon.getDuracionEnMinutos());

        Pelicula otraPelicula = new Pelicula("Matrix",1998);
        otraPelicula.setDuracionEnMinutos(180);

        CalculadoraDeTiempo calculadora = new CalculadoraDeTiempo();
        calculadora.incluye(miPelicula);
        calculadora.incluye(otraPelicula);
        calculadora.incluye(casaDragon);
        System.out.println(calculadora.getTiempoTotal());

        FiltroDeRecomendaciones filtroDeRecomendaciones = new FiltroDeRecomendaciones();
        filtroDeRecomendaciones.filtro(miPelicula);

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setNombre ("La Casa Torgoyan");
        episodio.setSerie(casaDragon);
        episodio.setTotalDeVisualizaciones(300);

        filtroDeRecomendaciones.filtro(episodio);

        var peliculaDeBruno = new Pelicula("El Señor de Los Anillos", 2002);
        peliculaDeBruno.setDuracionEnMinutos(180);
        peliculaDeBruno.setFechaDeLanzamiento(2001);

        ArrayList<Pelicula>listaDePelicula = new ArrayList<>();
        listaDePelicula.add(miPelicula);
        listaDePelicula.add(otraPelicula);
        listaDePelicula.add(peliculaDeBruno);

        System.out.println("Tamaño de lista: "+listaDePelicula.size());
        System.out.println("La primera pelicula es: "+listaDePelicula.get(0).getNombre());

        System.out.println(listaDePelicula);

        System.out.println("toString de la pelicula : "+listaDePelicula.get(0).getNombre());


    }
}
