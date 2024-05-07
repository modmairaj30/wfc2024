package com.webapp.apis.masters.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webapp.apis.masters.domain.BranchMaster;

@Repository
public interface BranchMasterRepository extends JpaRepository<BranchMaster, Integer> {
	public List<BranchMaster> findAllByOrderByIdDesc();
}
