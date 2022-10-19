package com.java.udemy.controller;

import com.java.udemy.domain.Cliente;
import com.java.udemy.dto.ClienteDTO;
import com.java.udemy.dto.TecnicoDTO;
import com.java.udemy.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getlAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(new ClienteDTO(clienteService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO dto) {
        Cliente novoCliente = clienteService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoCliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO dto) {
        Cliente clienteAutalizado = clienteService.update(id, dto);
        return ResponseEntity.ok().body(new ClienteDTO(clienteAutalizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id) {
        clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
