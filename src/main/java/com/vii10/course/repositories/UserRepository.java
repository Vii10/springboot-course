package com.vii10.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vii10.course.entities.User;


//JpaRepository é um subframework do sistema spring
public interface UserRepository extends JpaRepository<User, Long> {
//User = Tipo de entidade que o Jpa vai utilizar
//Long = Tipo de chave que o Jpa vai utilizar
//Não há necessidade de criar a implementação da interface, pois o Jpa já possui um padrão dela
	
}
