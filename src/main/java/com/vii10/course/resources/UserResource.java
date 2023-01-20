package com.vii10.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vii10.course.entities.User;
import com.vii10.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	//Dependencia para o service (*Sempre use o @Autowired quando se tratar de spring para fazer a dependencia auto)
	@Autowired
	private UserService userService;
	
	//Esse metodo retorna todos os usuarios no postman
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userService.findAll(); //Chamando serviço na operação findAll
		return ResponseEntity.ok().body(list);
				//.ok retorna uma resposta de sucesso do http
				//.body é o corpo da resposta a ser retornada
	}
	
	//Definindo que essa URL terá um parametro que será passado (id). Retorna um usuario especifico
	@GetMapping(value = "/{id}") //Essa requisição aceita um ID dentro da URL
	//A única forma de o String aceitar esse ID é usando a anotação PathVariable
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//Para inserir algo no banco, vamos precisar usar o @postmapping, que faz um pré-processamento da compilação do controlador
	@PostMapping
	public ResponseEntity <User> insert(@RequestBody User user){
	//RequestBody auxilia a determinar o formato Json na hora da requisiçao e ao processo de deserialização para um objeto user do java
		user = userService.insert(user);
	/* Para retornar o 201, precisaremos usar o .created para tal. Porém, o .created necessida de uma resposta que contenha um cabecalho
	 * chamado location com o endereço do novo recurso inserido
	 */
		
	//Gerando endereço com o springboot para criar o URI de retorno
		URI uri  = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
}