/*
	@author:Quang Truong
	@date: Jan 21, 2020
*/

package com.jwatgroupb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.jwatgroupb.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByCategoryName(String categoryName);
	
	@Transactional
	@Query("select r from CategoryEntity r")
	public List<CategoryEntity> find10Catetory(Pageable pagebale); 
	
	
}
