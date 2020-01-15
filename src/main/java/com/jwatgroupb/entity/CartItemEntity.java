/*
	@author:Quang Truong
	@date: Jan 15, 2020
*/

package com.jwatgroupb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cartitem")
public class CartItemEntity extends BaseEntity{
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cartid")
	private CartEntity cartEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid")
	private ProductEntity productEntity;
	
	@Column(name = "quanity")
	private int quanity;

	public CartEntity getCartEntity() {
		return cartEntity;
	}

	public void setCartEntity(CartEntity cartEntity) {
		this.cartEntity = cartEntity;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	
	
	
}
