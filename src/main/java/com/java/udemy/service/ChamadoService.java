package com.java.udemy.service;

import com.java.udemy.domain.Chamado;
import com.java.udemy.domain.Cliente;
import com.java.udemy.domain.Tecnico;
import com.java.udemy.domain.enums.Prioridade;
import com.java.udemy.domain.enums.Status;
import com.java.udemy.dto.ChamadoDTO;
import com.java.udemy.exceptions.ObjectNotFoundException;
import com.java.udemy.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findBYId(Integer id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);

        return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado"));
    }

    public List<Chamado> getAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(ChamadoDTO chamadoDTO) {
        return chamadoRepository.save(newChamado(chamadoDTO));
    }

    public Chamado update(Integer id, ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        Chamado chamadoExistente = findBYId(id);
        chamadoExistente = newChamado(chamadoDTO);

        return chamadoRepository.save(chamadoExistente);
    }

    private Chamado newChamado(ChamadoDTO dto) {
        Tecnico tecnico = tecnicoService.getById(dto.getTecnico_id());
        Cliente cliente = clienteService.getById(dto.getCliente_id());
        Chamado chamado = new Chamado();

        if(dto.getId() != null) {
            chamado.setId(dto.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade_id()));
        chamado.setStatus(Status.toEnum(dto.getStatus_id()));
        chamado.setTitulo(dto.getTitulo());
        chamado.setObservacoes(dto.getObservacoes());

        return chamado;
    }
}
