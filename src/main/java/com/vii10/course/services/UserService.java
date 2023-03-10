package com.vii10.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vii10.course.entities.User;
import com.vii10.course.repositories.UserRepository;
import com.vii10.course.services.exceptions.DatabaseException;
import com.vii10.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

//Registrando a classe como componente do spring. Por ser uma classe de serviço, será registrada como tal
@Service
public class UserService {
	
	/*Para implementar as operações de buscar todos os usuarios ou
	 * buscar um usuario pelo ID, é necessário atribuir uma depencendia
	 * à interface UserRepository*/
	
	//Injeção de dependencia de forma transparante ao programador
	@Autowired
	private UserRepository repository;
	
	//Metodo que retorna todos os usuarios do banco de dados
	public List<User> findAll(){ //Operação na camada de serviço
		return repository.findAll(); //Repassando chamada para o repository
	}
	
	//Metodo que retorna um usuario pel Id
	public User findById(long id) {
		Optional <User> obj = repository.findById(id); //Metodo que filtra apenas o tipo passado pelo Optional
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); //Retorna o tipo User que esta dentro do obj, que por sua vez filtrou por id
		/* A função acima tenta dar o get, se não conseguir ele retornará uma exception tratável
		 * Esse exception é acessado através de uma expressão lambda */
	
	}
	
	//Implementando um metodo para salvar um usuario
	public User insert(User user) {
		//Essa funcao retorna um objeto como padrao
		return repository.save(user);
	}
	
	//Deletando usuario pelo Id
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}
		//Tratamento de exceptions na hora da deleção de usuário
		//Se o usuario não existir
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		//Se tiver produtos associados a ele
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	//Atualizando usuario pelo Id
	public User update(Long id, User obj) {
		try {
		//Instancia um novo usuario sem coloca-lo no banco de dados ainda
		User entity = repository.getReferenceById(id);
		
		//Atualizando entity com base nos dados de obj
		updateData(entity, obj);
		return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);	
		}
		
	}
	
	//Metodo de atualização
	private void updateData(User entity, User obj) {
		//Atualizando só o necessário
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
