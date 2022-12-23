/* Classe para testar o Rest do SpringBoot */

package com.vii10.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vii10.course.entities.User;

//Para definirmos que essa classe é um recurso web e implementada por um controlador Rest, usamos a anotação abaixo
@RestController
@RequestMapping(value = "/users") // Nomeando recurso. É chamado de users porque é relacionado a entitade User
public class UserResource {
	
	//Indicando que esse metodo responde a requisição do tipo Get do HTTP
	@GetMapping
	
	//Endpoint para acessar os usúarios. O tipo ResponseEntity retorna respostas de requisições web
	public ResponseEntity<User>	findAll(){
		//Apenas para fins de testes
		User u = new User(1L, "Maria", "maria@gmail.com", "99999999", "12345678");
		//O L após o 1 é porque o tipo long precisa dessa classificação para ser "entendida"
		return ResponseEntity.ok().body(u);
		/*Chamamos o Response que é o tipo de retorno necessário. O .ok significa que o retorno funcionou e
		o .body serve para definirmos qual vai ser o "conteúdo" do retorno */
		
	}
}
