package com.java.udemy.service;

import com.java.udemy.domain.Cliente;
import com.java.udemy.domain.Pessoa;
import com.java.udemy.domain.Tecnico;
import com.java.udemy.dto.ClienteDTO;
import com.java.udemy.dto.TecnicoDTO;
import com.java.udemy.exceptions.DataIlegalException;
import com.java.udemy.exceptions.ObjectNotFoundException;
import com.java.udemy.repository.ClienteRepository;
import com.java.udemy.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente getById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

    public Cliente create(ClienteDTO dto) {
        dto.setId(null);
        dto.setSenha(encoder.encode(dto.getSenha()));
        validaPorCpfEEmail(dto);
        Cliente clienteNovo = new Cliente(dto);

        return clienteRepository.save(clienteNovo);
    }

    private void validaPorCpfEEmail(ClienteDTO dto) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(dto.getCpf());
        verificaPessoa(pessoa, dto.getId(), "CPF já cadastrado no sistema");

        pessoa = pessoaRepository.findByEmail(dto.getEmail());
        verificaPessoa(pessoa, dto.getId(), "E-mail já cadastrado no sistema");
    }

    private void verificaPessoa(Optional<Pessoa> pessoa, Integer id, String msg) {
        if(pessoa.isPresent() && pessoa.get().getId() != id) {
            throw new DataIlegalException(msg);
        }
    }

    public Cliente update(Integer id, ClienteDTO dto) {
        dto.setId(id);
        dto.setSenha(encoder.encode(dto.getSenha()));
        Cliente clienteAntigo = getById(id);
        validaPorCpfEEmail(dto);
        clienteAntigo = new Cliente(dto);

        return clienteRepository.save(clienteAntigo);
    }

    public void delete(Integer id) {
        Cliente cliente = getById(id);

        if(cliente.getChamados().size() > 0) {
            throw new DataIlegalException("Ordens de serviço vinculadas a este cliente!");
        }

        clienteRepository.delete(cliente);
    }
}
