package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.*;
import com.aluracursos.screenmatch.repository.ISerieRepository;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvertirDatos;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    Scanner lectura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=173f0db0";
    private ConvertirDatos conversor = new ConvertirDatos();
    private List<DatosSerie> datosSeries = new ArrayList<>();
    private ISerieRepository repositorio;
    private List<Serie> series;
    private Optional<Serie> serieBuscada;

    public Principal(ISerieRepository repository) {
        this.repositorio = repository;
    }


    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar series 
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                    4 - Buscar Serie por Titulo
                    5 - Top 5 Series
                    6 - Buscar Serie por Genero
                    7 - filtrar series por temporadas y evaluación
                    8 - Buscar episodios por titulo
                    9 - Top 5 episodios por Serie
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = lectura.nextInt();
            lectura.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    System.out.println("Que informacion te gustaria saberde la serie?");
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    System.out.println("Mostras series buscadas");
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarTop5MejoresSeries();
                    break;
                case 6:
                    buscarSeriePorGenero();
                    break;
                case 7:
                    filtrarSeriesPorTemporadaYEvaluacion();
                    break;
                case 8:
                    buscarEpisodiosPorTitulo();
                    break;
                case 9:
                    buscarTop5Episodios();
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosSerie getDatosSerie() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = lectura.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        //System.out.println(json);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        return datos;
    }

    private void buscarEpisodioPorSerie() {
        mostrarSeriesBuscadas(); //Mostrar una lista con las series buscadas en la base de datos
        System.out.println("Escriba el nombre de de los Episodios que quiere buscar");

        var nombreSerie = lectura.nextLine();//Leer valores del teclado
        //Optional para hacer la busqueda que contenga o no un resultado
        Optional<Serie> serie = series.stream()
                //filtro para encontrar las series que contengan los valores del teclado
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase())).findFirst();
        //Condicional que se acciona una ve que haya encontrado la serie
        if (serie.isPresent()) {
            //Control del resultado del optional
            var serieEncontrada = serie.get();
            //Lista de temporadas de la series
            List<DatosTemporada> temporadas = new ArrayList<>();
            //Imprime o genera el numero total de temporadas
            for (int i = 1; i <= serieEncontrada.getTotalDeTemporadas(); i++) {
                var json = consumoAPI.obtenerDatos(URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DatosTemporada datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
                temporadas.add(datosTemporada);
            }
            //Mostrar datos en pantalla
            temporadas.forEach(System.out::println);
            //Obtener una lista de Episodios de cada temporada
            List<Episodio> episodios = temporadas.stream()
                    //convertir de cada dato de temporada  en un nuevo episodio
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect((Collectors.toList()));
            //Guardar eisodios en nuestra base de datos en setEpisodios
            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        }

    }

    private void buscarSerieWeb() {
        DatosSerie datos = getDatosSerie();
        //datosSeries.add(datos);
        Serie serie = new Serie(datos);
        repositorio.save(serie);
        System.out.println(datos);
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escriba el nombre de la Serie que quiere encontrar");
        var nombreSerie = lectura.nextLine();
        //Lista para guardar la serie en una Lista SerieBuscada
        Optional<Serie> serieBuscada = repositorio.findByTituloIgnoreCase(nombreSerie);

        if (serieBuscada.isPresent()) {
            System.out.println("Serie Encontrada: " + serieBuscada.get());
        } else {
            System.out.println("Serie no Encontrada");
        }
    }

    private void buscarTop5MejoresSeries() {
        List<Serie> topSerie = repositorio.findTop5ByOrderByEvaluacionDesc();

        topSerie.forEach(s -> System.out.println("Serie: " + s.getTitulo() + "Evaluacion: " + s.getEvaluacion()));
    }

    private void buscarSeriePorGenero() {
        System.out.println("Escriba el Genero/categoria de la serie que desea buscar");

        var genero = lectura.nextLine();
        var categoria = Categoria.fromEspanol(genero);

        List<Serie> serieCategoria = repositorio.findByGenero(categoria);
        System.out.println("Las Series de la categoria " + genero);
        serieCategoria.forEach(System.out::println);
    }

    private void mostrarSeriesBuscadas() {
        series = repositorio.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    public void filtrarSeriesPorTemporadaYEvaluacion() {
        System.out.println("¿Filtrar séries con cuántas temporadas? ");
        var totalTemporadas = lectura.nextInt();
        lectura.nextLine();
        System.out.println("¿Com evaluación apartir de cuál valor? ");
        var evaluacion = lectura.nextDouble();
        lectura.nextLine();
        List<Serie> filtroSeries = repositorio.seriesPorTemparadaYEvaluacion(totalTemporadas, evaluacion);
        System.out.println("*** Series filtradas ***");
        filtroSeries.forEach(s ->
                System.out.println(s.getTitulo() + "  - evaluacion: " + s.getEvaluacion()));
    }

    private void buscarEpisodiosPorTitulo() {
        System.out.println("Escribe el nombre del episodio que deseas buscar");
        var nombreEpisodio = lectura.nextLine();
        List<Episodio> episodiosEncontrados = repositorio.episodiosPorNombre(nombreEpisodio);
        episodiosEncontrados.forEach(e ->
                System.out.printf("Serie: %s Temporada %s Episodio %s Evaluación %s\n",
                        e.getSerie().getTitulo(), e.getTemporada(), e.getNuevoEpisodio(), e.getEvaluacion()));

    }

    private void buscarTop5Episodios() {
        buscarSeriePorTitulo();
        if (serieBuscada.isPresent()) {
            Serie serie = serieBuscada.get();
            List<Episodio> topEpisodios = repositorio.top5Episodios(serie);
            topEpisodios.forEach(e ->
                    System.out.printf("Serie: %s - Temporada %s - Episodio %s - Evaluación %s\n",
                            e.getSerie().getTitulo(), e.getTemporada(), e.getTitulo(), e.getEvaluacion()));

        }
    }
}