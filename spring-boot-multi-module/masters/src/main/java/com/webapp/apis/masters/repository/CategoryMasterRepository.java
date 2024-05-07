package com.webapp.apis.masters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.apis.masters.domain.AuthorMaster;
import com.webapp.apis.masters.domain.CategoryMaster;
import com.webapp.apis.masters.domain.DepartmentMaster;



public interface CategoryMasterRepository extends JpaRepository<CategoryMaster, Integer>{

	List<CategoryMaster> findAllByOrderByIdAsc();
	public List<CategoryMaster> findAllByOrderByIdDesc();



}
