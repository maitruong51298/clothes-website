package com.jwatgroupb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.jwatgroupb.entity.ProductEntity;
import com.jwatgroupb.entity.RoleUserEntity;

public interface RoleUserRepository extends JpaRepository<RoleUserEntity, Long> {
//	@Transactional
//	@Query ("select r from RoleUserEntity r where r.id = :id")
	RoleUserEntity findOneById(long id);
	
}
