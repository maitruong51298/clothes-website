/*
	@author:Quang Truong
	@date: Feb 2, 2020
*/

package com.jwatgroupb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwatgroupb.entity.BillDetailEntity;
import com.jwatgroupb.entity.BillEntity;
import com.jwatgroupb.entity.CartEntity;
import com.jwatgroupb.entity.CartItemEntity;
import com.jwatgroupb.repository.BillDetailRepository;
import com.jwatgroupb.repository.BillRepository;

@Service
public class CheckoutService {

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private BillDetailRepository billDetailRepository;

	@Transactional
	public void addBill(BillEntity bill) {
		billRepository.save(bill);
	}

	@Transactional
	public void addBillDetail(CartEntity cart,BillEntity bill) {
		List<CartItemEntity> list = cart.getListCartItem();
		for(CartItemEntity cartItem:list) {
			BillDetailEntity billDetail= new BillDetailEntity();
			billDetail.setBillEntity(bill);
			billDetail.setPrice(cartItem.getProductEntity().getPrice());
			billDetail.setProductEntity(cartItem.getProductEntity());
			billDetail.setPromotion(cartItem.getProductEntity().getPromotion());
			billDetail.setQuanity(cartItem.getQuanity());
			billDetailRepository.save(billDetail);
		}
	}
}
