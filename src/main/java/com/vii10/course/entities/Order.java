package com.vii10.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vii10.course.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//Classe responsável pelo pedido
@Entity
@Table(name = "tb_order") // Renomeando classe, pois há um conflito com o padrão reservado pelo SQL
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	// Formatando data para o padrão ISO através de uma anotação
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	// O tipo Instant é muito melhor que o tipo Date e de fácil implementação
	
	//Implementando o OrderStatus
	private Integer orderStatus;

	/*
	 * Implementando o relacionamento entre a classe pedido e a classe cliente para
	 * que o JPA transforme isso em uma chave estrangeira em nosso banco de dados
	 */
	@ManyToOne
	@JoinColumn(name = "client_id") // Esse nome se refere ao nome que será dado a chave estrangeira
	private User client;
	
	//O set é mapeado pelo id.order, pois o .order possue os id dos pedidos. O Set OrderItem possui os Ids de pedidos nessa classe
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	//Para que seja possivel realizar a relacao 1 para 1 com o mesmo Id, precisamos usar o cascade
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		Id = id;
		this.moment = moment;
		//Atribuindo um order status para o objeto order
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public OrderStatus getOrderStatus() {
		//Chamando método valueOf() para acessar o número interno da classe e, o retornando
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
		this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItems(){
		return items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(Id, other.Id);
	}

}
