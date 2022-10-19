package com.java.udemy.service;

import com.java.udemy.domain.Pessoa;
import com.java.udemy.domain.Tecnico;
import com.java.udemy.dto.TecnicoDTO;
import com.java.udemy.exceptions.DataIlegalException;
import com.java.udemy.exceptions.ObjectNotFoundException;
import com.java.udemy.repository.PessoaRepository;
import com.java.udemy.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico getById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Tecnico não encontrado"));
    }

    public Tecnico create(TecnicoDTO dto) {
        dto.setId(null);
        dto.setSenha(encoder.encode(dto.getSenha()));
        validaPorCpfEEmail(dto);
        Tecnico tecnicoNovo = new Tecnico(dto);

        return tecnicoRepository.save(tecnicoNovo);
    }

    private void validaPorCpfEEmail(TecnicoDTO dto) {
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

    public Tecnico update(Integer id, TecnicoDTO dto) {
        dto.setId(id);
        dto.setSenha(encoder.encode(dto.getSenha()));
        Tecnico tecnicoAntigo = getById(id);
        validaPorCpfEEmail(dto);
        tecnicoAntigo = new Tecnico(dto);

        return tecnicoRepository.save(tecnicoAntigo);
    }

    public void delete(Integer id) {
        Tecnico tecnico = getById(id);

        if(tecnico.getChamados().size() > 0) {
            throw new DataIlegalException("Ordens de serviço vinculadas a este tecnico!");
        }

        tecnicoRepository.delete(tecnico);
    }
}
