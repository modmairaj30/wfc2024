package com.webapp.apis.masters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.webapp.apis.masters.domain.AuthorMaster;



public interface AuthorMasterRepository
	extends JpaRepository< AuthorMaster, Integer>, PagingAndSortingRepository< AuthorMaster, Integer>,JpaSpecificationExecutor< AuthorMaster> {
	List<AuthorMaster> findAllByOrderByIdAsc();
	public List<AuthorMaster> findAllByOrderByIdDesc();

}
