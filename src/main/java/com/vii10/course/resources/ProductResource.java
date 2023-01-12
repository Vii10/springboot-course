package com.vii10.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vii10.course.entities.Product;
import com.vii10.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	//Dependencia para o service (*Sempre use o @Autowired quando se tratar de spring para fazer a dependencia auto)
	@Autowired
	private ProductService userService;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> list = userService.findAll(); //Chamando serviço na operação findAll
		return ResponseEntity.ok().body(list);
				//.ok retorna uma resposta de sucesso do http
				//.body é o corpo da resposta a ser retornada
	}
	
	//Definindo que essa URL terá um parametro que será passado (id)
	@GetMapping(value = "/{id}") //Essa requisição aceita um ID dentro da URL
	//A única forma de o String aceitar esse ID é usando a anotação PathVariable
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	
}