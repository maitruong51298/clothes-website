/*
	@author:Quang Truong
	@date: Jan 15, 2020
*/

package com.jwatgroupb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUserNameAndActive(username, SystemConstant.ACTIVE_STATUS);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		// put thong tin vao security de duy tri thong tin khi user login vao he thong
		User user = new User(username, userEntity.getPassword(), true, true, true, true, authorities);

		return null;
	}

}
