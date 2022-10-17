package com.java.udemy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.udemy.domain.Tecnico;
import com.java.udemy.domain.enums.Perfil;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TecnicoDTO implements Serializable {

    private Integer id;
    private String nome;
    private String senha;
    private String cpf;
    private String email;
    protected Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public TecnicoDTO(Tecnico tecnico) {
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.senha = tecnico.getSenha();
        this.cpf = tecnico.getCpf();
        this.email = tecnico.getEmail();
        this.perfis = tecnico.getPerfils().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
    }

    public Set<Perfil> getPerfils() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }
}
