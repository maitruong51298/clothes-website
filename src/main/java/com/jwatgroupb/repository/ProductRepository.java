/*
	@author:Quang Truong
	@date: Jan 21, 2020
*/

package com.jwatgroupb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jwatgroupb.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	/* List<ProductEntity> findAllByCategoryName(String categoryName); */
	@Transactional
	@Query("select r from ProductEntity r")
	public List<ProductEntity> find10Product(Pageable pagebale);
	
	@Query(value = "select u from ProductEntity u where u.name like '%' || :keyword || '%'"
			)
	public List<ProductEntity> search(@Param("keyword") String keyword) ;

}
