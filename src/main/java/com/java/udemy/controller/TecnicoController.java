package com.java.udemy.controller;

import com.java.udemy.domain.Tecnico;
import com.java.udemy.dto.TecnicoDTO;
import com.java.udemy.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tecnico")
public class TecnicoController {

   @Autowired
   private TecnicoService tecnicoService;

    @GetMapping
    public List<Tecnico> getlAll() {
        return tecnicoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(new TecnicoDTO(tecnicoService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO dto) {
        Tecnico novoTecnico = tecnicoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoTecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO dto) {
        Tecnico tecnicoAtualizado = tecnicoService.update(id, dto);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnicoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id) {
        tecnicoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
