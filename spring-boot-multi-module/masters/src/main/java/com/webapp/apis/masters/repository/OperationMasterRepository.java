package com.webapp.apis.masters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.apis.masters.domain.OperationMaster;

@Repository
public interface OperationMasterRepository extends JpaRepository<OperationMaster,Integer> {

	List<OperationMaster> findAllByOrderByIdAsc();
	
}
