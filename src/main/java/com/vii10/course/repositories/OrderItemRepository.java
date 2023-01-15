package com.vii10.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vii10.course.entities.OrderItem;


//JpaRepository é um subframework do sistema spring
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//OrderItem	 = Tipo de entidade que o Jpa vai utilizar
//Long = Tipo de chave que o Jpa vai utilizar
//Não há necessidade de criar a implementação da interface, pois o Jpa já possui um padrão dela
	
}
