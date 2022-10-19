package com.java.udemy.service;

import com.java.udemy.domain.Pessoa;
import com.java.udemy.repository.PessoaRepository;
import com.java.udemy.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> pessoa = pessoaRepository.findByEmail(email);

        if(pessoa.isPresent()) {
            return new UserSS(pessoa.get().getId(), pessoa.get().getEmail(), pessoa.get().getSenha(), pessoa.get().getPerfils());
        }

        throw new UsernameNotFoundException(email);
    }
}
