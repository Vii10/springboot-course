package com.vii10.course.entities.enums;

public enum OrderStatus {
	
	//O java exige que tratemos essas classificações manuais
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	//Código do tipo enumerado
	private int code;
	
	//Construtor para o tipo enumerado
	private OrderStatus(int code) {
		this.code = code;
	}
	
	//Metodo público para acessar o código do lado de fora da classe
	public int getCode() {
		return code;
	}
	
	//Convertendo um valor númerico para um tipo enumerado (static, pois não precisa instancia-lo)
	public static OrderStatus valueOf(int code) { //Retorna um valor associado ao OrderStatus
		//Percorrendo todos os enumerados e retornando um código correspondente
		for(OrderStatus value : OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		
		//Se o código for diferente
		throw new IllegalArgumentException("Invalid Order Status code");
	}
}
