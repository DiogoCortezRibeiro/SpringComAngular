package com.java.udemy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.udemy.domain.Chamado;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ChamadoDTO implements Serializable {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamanto;
    private Integer prioridade_id;
    private Integer status_id;
    private String observacoes;
    private String titulo;
    private Integer tecnico_id;
    private Integer cliente_id;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO() {
        super();
    }

    public ChamadoDTO(Chamado chamado) {
        super();
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamanto = chamado.getDataFechamanto();
        this.prioridade_id = chamado.getPrioridade().getCodigo();
        this.status_id = chamado.getStatus().getCodigo();
        this.observacoes = chamado.getObservacoes();
        this.titulo = chamado.getTitulo();
        this.tecnico_id = chamado.getTecnico().getId();
        this.cliente_id = chamado.getCliente().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.nomeCliente = chamado.getCliente().getNome();
    }
}
