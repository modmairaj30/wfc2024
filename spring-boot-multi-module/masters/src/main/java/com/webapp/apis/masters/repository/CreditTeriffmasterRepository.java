package com.webapp.apis.masters.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.webapp.apis.masters.domain.CreditTeriffmaster;

@Repository
public interface CreditTeriffmasterRepository extends JpaRepository<CreditTeriffmaster, Integer>, PagingAndSortingRepository<CreditTeriffmaster, Integer>,JpaSpecificationExecutor<CreditTeriffmaster> {
	public List<CreditTeriffmaster> findAllByOrderByIdDesc();
	public Page<CreditTeriffmaster> findAllByOrderByIdDesc(Pageable pageable);
}
