package com.java.udemy.controller;

import com.java.udemy.domain.Chamado;
import com.java.udemy.dto.ChamadoDTO;
import com.java.udemy.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/chamado")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> getById(@PathVariable Integer id) {
        Chamado chamado = chamadoService.findBYId(id);

        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @GetMapping
    public ResponseEntity<List<Chamado>> getAll() {
        return ResponseEntity.ok().body(chamadoService.getAll());
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@RequestBody ChamadoDTO chamadoDTO) {
        Chamado chamado = chamadoService.create(chamadoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @RequestBody ChamadoDTO chamadoDTO) {
        Chamado chamado = chamadoService.update(id, chamadoDTO);

        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }
}
