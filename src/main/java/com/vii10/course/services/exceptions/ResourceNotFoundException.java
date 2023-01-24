package com.vii10.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	//Construtor para configurar a excessão customizada
	public ResourceNotFoundException(Object id) {
		//Chamando super classe (RuntimeException) para inserir texto customizado
		super("Resource not found. ID: " + id);	
	}
}
