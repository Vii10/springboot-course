package com.vii10.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vii10.course.entities.User;
import com.vii10.course.repositories.UserRepository;

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
		return obj.get(); //Retorna o tipo User que esta dentro do obj, que por sua vez filtrou por id
	}
	
	//Implementando um metodo para salvar um usuario
	public User insert(User user) {
		//Essa funcao retorna um objeto como padrao
		return repository.save(user);
	}
	
	//Deletando usuario pelo Id
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	//Atualizando usuario pelo Id
	public User update(Long id, User obj) {
		
		//Instancia um novo usuario sem coloca-lo no banco de dados ainda
		User entity = repository.getReferenceById(id);
		
		//Atualizando entity com base nos dados de obj
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	//Metodo de atualização
	private void updateData(User entity, User obj) {
		//Atualizando só o necessário
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
