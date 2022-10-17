package com.java.udemy.service;

import com.java.udemy.domain.Tecnico;
import com.java.udemy.dto.TecnicoDTO;
import com.java.udemy.exceptions.ObjectNotFoundException;
import com.java.udemy.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico getById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Tecnico não encontrado"));
    }

    public Tecnico create(TecnicoDTO dto) {
        dto.setId(null);
        Tecnico tecnicoNovo = new Tecnico(dto);

        return tecnicoRepository.save(tecnicoNovo);
    }
}
