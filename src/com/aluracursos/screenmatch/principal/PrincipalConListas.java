package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.modelos.Pelicula;
import com.aluracursos.screenmatch.modelos.Series;
import com.aluracursos.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PrincipalConListas {
    public static void main (String[]args){
        Pelicula miPelicula = new Pelicula("Encanto",2023);
        miPelicula.evalua(9);
        Pelicula otraPelicula = new Pelicula("Avatar",2023);
        otraPelicula.evalua(6);
        var peliculaDeBruno = new Pelicula("El señor de los anillos",2001);
        peliculaDeBruno.evalua(10);
        Series lost = new Series("Lost",2000);


        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(peliculaDeBruno);
        lista.add(lost);

        for (Titulo item:lista){
            System.out.println("Nombre:" + item.getNombre());
            if(item instanceof Pelicula pelicula && pelicula.getClasificacion() > 2){
                System.out.println("Clasificacion: "+ pelicula.getClasificacion());
            }
        }

        ArrayList <String>listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add("Penelope Cruz");
        listaDeArtistas.add("Antonio Banderoas");
        listaDeArtistas.add("Ricardo Durin");

        System.out.println("Lista de Artistas ordenada:" + listaDeArtistas);

        Collections.sort(listaDeArtistas);
        Collections.sort(lista);
        System.out.println("Lista de Titulos ordenados: "+lista);

        lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento));
        System.out.println("Lista Ordenada por Fecha: "+lista);

        lista.sort(Comparator.comparing(Titulo::getDuracionEnMinutos));
        System.out.println("Lista de peliculas ordenadas segun su duracion: "+lista);
    }
}
