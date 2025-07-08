package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.repository.ISerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private ISerieRepository repository;

    @GetMapping("/serie")
    public List<SerieDTO> obtenerTodasLasSeries(){
        return repository.findAll().stream()
                .map(s-> new SerieDTO(s.getTitulo(),s.getTotalDeTemporadas(),s.getEvaluacion(),s.getPoster(),
                        s.getGenero(),s.getActores(),s.getSinopsis()))
                .collect(Collectors.toList());
    }
}
