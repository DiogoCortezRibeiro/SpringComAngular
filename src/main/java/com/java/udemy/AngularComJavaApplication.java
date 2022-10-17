package com.java.udemy;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AngularComJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularComJavaApplication.class, args);
	}

}
