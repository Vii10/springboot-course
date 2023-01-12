package com.vii10.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vii10.course.entities.Category;


//JpaRepository é um subframework do sistema spring
public interface CategoryRepository extends JpaRepository<Category, Long> {
//Category = Tipo de entidade que o Jpa vai utilizar
//Long = Tipo de chave que o Jpa vai utilizar
//Não há necessidade de criar a implementação da interface, pois o Jpa já possui um padrão dela
	
}
