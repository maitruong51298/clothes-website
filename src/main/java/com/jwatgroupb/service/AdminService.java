
package com.jwatgroupb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.entity.RoleUserEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.repository.RoleUserRepository;
import com.jwatgroupb.repository.UserRepository;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleUserRepository roleUserRepository;

	public List<UserEntity> findAll() {	
		return userRepository.findAll();
	}

	   public void delete(Long id) {
	        userRepository.delete(id);
	    }
	   public UserEntity findUserById(Long id) {
			return userRepository.findOne(id);
		}
	   @Transactional
	public void updateUser(UserEntity user) {
		userRepository.update(user.getUserName(),user.getEmail(),user.getPassword(),user.getActive(), user.getRoleUserEntity());
	}
	

		public RoleUserEntity findOneById(Long id) {
			return roleUserRepository.findOne(id);
		}
	public UserEntity findUserByUserName(String username) {
		return userRepository.findOneByUserNameAndActive(username,SystemConstant.ACTIVE_STATUS);
	}
	   @Transactional
	public void saveUser(UserEntity user , Long roleid) {
	
		userRepository.save(user);
	}

	public List<UserEntity> search(String keyword) {
	    return userRepository.search(keyword);
	}
}
	
