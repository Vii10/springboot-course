package com.vii10.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vii10.course.entities.Order;
import com.vii10.course.entities.User;
import com.vii10.course.entities.enums.OrderStatus;
import com.vii10.course.repositories.OrderRepository;
import com.vii10.course.repositories.UserRepository;

//Dizendo ao Spring que essa classe é específica de configuração:
@Configuration
//Dizendo ao Spring que essa classe é de configuração específica para o perfil de teste
@Profile("test")
public class TestConfig implements CommandLineRunner {
//CommandLineRunner puxa um método para execução automática

	// Resolvendo a dependência e associando uma instancia do UserRepository no
	// TestConfig
	@Autowired
	// Injeção de dependência: Essa classe depende da Interface UserRepository
	private UserRepository userRepository;

	// Realizando seed de pedidos no banco de dados
	@Autowired
	private OrderRepository orderRepository;

	// Tudo que estiver nesse método é executado quando o programa for iniciado
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		/*
		 * Instanciando objetos do tipo Order com datas instanciadas usando o
		 * instant.parse ISO8601 = Data formatada em Greenwich (UTC - GMT). É o padrão
		 * de desenvolvimento u1, uN... no final da instanciação é uma forma de
		 * associação de objetos
		 */
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

		// Salvando usúarios no banco de dados
		userRepository.saveAll(Arrays.asList(u1, u2));
		// Salvando pedidos no banco de dados
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}

}
