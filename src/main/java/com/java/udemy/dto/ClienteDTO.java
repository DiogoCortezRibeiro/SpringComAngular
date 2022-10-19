package com.java.udemy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.udemy.domain.Cliente;
import com.java.udemy.domain.Tecnico;
import com.java.udemy.domain.enums.Perfil;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ClienteDTO implements Serializable {
    private Integer id;
    @NotNull(message = "O campo NOME é requerido")
    private String nome;
    @NotNull(message = "O campo SENHA é requerido")
    private String senha;
    @NotNull(message = "O campo CPF é requerido")
    private String cpf;
    @NotNull(message = "O campo E-mail é requerido")
    private String email;
    protected Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public ClienteDTO() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.senha = cliente.getSenha();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.perfis = cliente.getPerfils().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
    }

    public Set<Perfil> getPerfils() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }
}
