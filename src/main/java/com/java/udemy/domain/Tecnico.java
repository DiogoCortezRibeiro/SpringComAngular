package com.java.udemy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.udemy.domain.enums.Perfil;
import com.java.udemy.dto.TecnicoDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Tecnico extends Pessoa {

    @OneToMany(mappedBy = "tecnico")
    @JsonIgnore
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfils(Perfil.TECNICO);
    }

    public Tecnico(TecnicoDTO tecnicoDTO) {
        super();
        this.id = tecnicoDTO.getId();
        this.nome = tecnicoDTO.getNome();
        this.senha = tecnicoDTO.getSenha();
        this.cpf = tecnicoDTO.getCpf();
        this.email = tecnicoDTO.getEmail();
        this.perfils = tecnicoDTO.getPerfils().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfils(Perfil.TECNICO);
    }
}
