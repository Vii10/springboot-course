package com.vii10.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vii10.course.entities.User;
import com.vii10.course.repositories.UserRepository;

//Dizendo ao String que essa classe é específica de configuração:
@Configuration
//Dizendo ao String que essa classe é de configuração específica para o perfil de teste
@Profile("test")
public class TestConfig implements CommandLineRunner {
//CommandLineRunner puxa um método para execução automática
	
	//Resolvendo a dependência e associando uma instancia do UserRepository no TestConfig
	@Autowired
	//Injeção de dependência: Essa classe depende da Interface UserRepository
	private UserRepository userRepository;
	
	//Tudo que estiver nesse método é executado quando o programa for iniciado
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//Salvando usúarios no banco de dados
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
	
	
}
