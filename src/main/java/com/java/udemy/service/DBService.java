package com.java.udemy.service;

import com.java.udemy.domain.Chamado;
import com.java.udemy.domain.Cliente;
import com.java.udemy.domain.Tecnico;
import com.java.udemy.domain.enums.Perfil;
import com.java.udemy.domain.enums.Prioridade;
import com.java.udemy.domain.enums.Status;
import com.java.udemy.repository.ChamadoRepository;
import com.java.udemy.repository.ClienteRepository;
import com.java.udemy.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        Tecnico tecnico = new Tecnico(null, "Diogo Cortez", "123456789", "diogo.example@gmail.com", "123");
        tecnico.addPerfils(Perfil.ADMIN);

        Cliente cliente = new Cliente(null, "Cliente teste", "12345678910", "cliente.example@gmail.com", "123");

        Chamado chamado = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tecnico, cliente);

        tecnicoRepository.save(tecnico);
        clienteRepository.save(cliente);
        chamadoRepository.save(chamado);
    }

}
