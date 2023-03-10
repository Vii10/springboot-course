package com.vii10.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vii10.course.entities.Category;
import com.vii10.course.entities.Order;
import com.vii10.course.entities.OrderItem;
import com.vii10.course.entities.Payment;
import com.vii10.course.entities.Product;
import com.vii10.course.entities.User;
import com.vii10.course.entities.enums.OrderStatus;
import com.vii10.course.repositories.CategoryRepository;
import com.vii10.course.repositories.OrderItemRepository;
import com.vii10.course.repositories.OrderRepository;
import com.vii10.course.repositories.ProductRepository;
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

	// Para acessar o banco de dados em relação às categorias é necessário um repository
	@Autowired
	private CategoryRepository categoryRepository;
	
	// Acessando o product através de um repository
	@Autowired
	private ProductRepository productRepository;

	// Realizando seed de pedidos no banco de dados
	@Autowired
	private OrderRepository orderRepository;
	
	// Realizando seed do OrderItem
	@Autowired
	private OrderItemRepository orderItemRepository;

	// Tudo que estiver nesse método é executado quando o programa for iniciado
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics"); 
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers"); 
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		//Salvando a categoria no banco de dados (Precisa ser em arraylist para salvar)
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		//Salvando product no banco de dados
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		//Atribuindo categorias aos produtos (Orientado a objetos)
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		//Salvando associações
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
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
		
		//Ordens de pedido
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		/*Para salvarmos um objeto dependente em relacao 1 para 1, nós usaremos a associacao
		 * de mao dupla em memória */
		o1.setPayment(pay1);
		
		//Salvando no banco
		orderRepository.save(o1);
	}

}
