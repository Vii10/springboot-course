package com.vii10.course.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vii10.course.entities.pk.OrderItemPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//Mapeamentos
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//Identificador, correspondente a chave primaria
	@EmbeddedId
	//Essa classe composta precisa ser instanciada, se não ela continuará valendo nulo e quebrará o programa
	private OrderItemPK id = new OrderItemPK();
	private Integer quantity;
	private Double price;
	
	public OrderItem(){}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		//Atribuindo o order e o product recebido para esse pedido
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	//Precisamos instanciar as funcões de get e set manualmente
	/* Precisamos cortar a associação de mão dupla do Order e o OrderItem
	 * Para usarmos o JsonIgnore, teremos que colocar a anotação no metodo get
	 * pois é esse método que vale no Java Enterprise
	 */
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	public void setProduc(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	//Metodo subtotal do UML
	public Double getSubTotal() {
		//Precisa ser um get para poder aparecer no Json
		return price * quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
}
