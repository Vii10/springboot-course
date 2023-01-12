package com.vii10.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vii10.course.entities.Product;
import com.vii10.course.repositories.ProductRepository;

//Registrando a classe como componente do spring. Por ser uma classe de serviço, será registrada como tal
@Service
public class ProductService {
	
	/*Para implementar as operações de buscar todos os usuarios ou
	 * buscar um usuario pelo ID, é necessário atribuir uma depencendia
	 * à interface ProductRepository*/
	
	//Injeção de dependencia de forma transparante ao programador
	@Autowired
	private ProductRepository repository;
	
	//Metodo que retorna todos os usuarios do banco de dados
	public List<Product> findAll(){ //Operação na camada de serviço
		return repository.findAll(); //Repassando chamada para o repository
	}
	
	//Metodo que retorna um usuario pel Id
	public Product findById(long id) {
		Optional <Product> obj = repository.findById(id); //Metodo que filtra apenas o tipo passado pelo Optional
		return obj.get(); //Retorna o tipo Product que esta dentro do obj, que por sua vez filtrou por id
	}
}
