package com.webapp.apis.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.apis.masters.domain.ScreenOperationMaster;

@Repository
public interface ScreenOperationMasterRepository extends JpaRepository<ScreenOperationMaster, Integer> {

}
