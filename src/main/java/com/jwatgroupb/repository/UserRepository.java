/*
	@author:Quang Truong
	@date: Jan 14, 2020
*/

package com.jwatgroupb.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jwatgroupb.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
