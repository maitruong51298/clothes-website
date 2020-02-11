/*
	@author:Quang Truong
	@date: Jan 21, 2020
*/

package com.jwatgroupb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwatgroupb.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	/* List<ProductEntity> findAllByCategoryName(String categoryName); */
}
