package com.java.udemy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.udemy.domain.enums.Perfil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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
}
