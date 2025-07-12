package com.aluracursos.screenmatch.controller;

import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.ISerieRepository;
import com.aluracursos.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import java.awt.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/series")
public class SerieController {


    @Autowired
    private SerieService servicio;

    @GetMapping ("/serie")
    public List<SerieDTO> obtenerTodasLasSeries() {
        return servicio.obtenerTodasLasSeries();
    }

    @GetMapping("/inicio")
    public String mostrarMensaje(){
        return "Probando LiveReloding";
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5 (){
        return servicio.obtenerTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzamientosMasRecientes (){
        return servicio.obtenerLanzamientosMasRecientes();
    }
}
