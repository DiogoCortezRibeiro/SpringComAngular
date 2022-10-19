package com.java.udemy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.udemy.domain.enums.Perfil;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
public abstract class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String nome;
    @Column(unique = true)
    protected String cpf;
    @Column(unique = true)
    protected String email;
    protected String senha;
    // impossibilita ter dois valores iguais dentro de nossa lista usando o SET
    // informamos que esta e uma coleção do tipo integer, e quando dermos um geter em pessoa venha junto com usuario
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    protected Set<Integer> perfils = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa() {
        super();
        addPerfils(Perfil.CLIENTE);
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfils(Perfil.CLIENTE);
    }

    public Set<Perfil> getPerfils() {
        return perfils.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfils(Perfil perfil) {
        this.perfils.add(perfil.getCodigo());
    }
}
