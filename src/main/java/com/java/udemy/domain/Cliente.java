package com.java.udemy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.udemy.domain.enums.Perfil;
import com.java.udemy.dto.ClienteDTO;
import com.java.udemy.dto.TecnicoDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Cliente extends Pessoa {

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfils(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfils(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO clientedto) {
        super();
        this.id = clientedto.getId();
        this.nome = clientedto.getNome();
        this.senha = clientedto.getSenha();
        this.cpf = clientedto.getCpf();
        this.email = clientedto.getEmail();
        this.perfils = clientedto.getPerfils().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
    }
}
