/*
	@author:Quang Truong
	@date: Jan 14, 2020
*/

package com.jwatgroupb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.entity.CartItemEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<UserEntity> findAll() {	
		return userRepository.findAll();
	}
	
	public UserEntity findByUsername(String username) {
		return userRepository.findOneByUserNameAndActive(username, SystemConstant.ACTIVE_STATUS);
	}
	
	public List<CartItemEntity> findCartOfUser(String username){
		return findByUsername(username).getCartEntity().getListCartItem();
	}
}
