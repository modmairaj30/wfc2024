package com.webapp.apis.masters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.apis.masters.domain.TariffTypeMaster;

@Repository
public interface TariffTypeMasterRepository extends JpaRepository<TariffTypeMaster, Integer> {
	public List<TariffTypeMaster> findAllByOrderByIdDesc();
}
