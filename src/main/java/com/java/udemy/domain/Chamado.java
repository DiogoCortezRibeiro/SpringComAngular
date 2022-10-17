package com.java.udemy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.udemy.domain.enums.Prioridade;
import com.java.udemy.domain.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Chamado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamanto;
    private Prioridade prioridade;
    private Status status;
    private String observacoes;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Chamado() {
        super();
    }

    public Chamado(Integer id, Prioridade prioridade, Status status, String observacoes, String titulo, Tecnico tecnico, Cliente cliente) {
        super();
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.observacoes = observacoes;
        this.titulo = titulo;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }
}
