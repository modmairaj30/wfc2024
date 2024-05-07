package com.webapp.apis.masters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.apis.masters.domain.Mode;

@Repository
public interface ModeRepository extends JpaRepository<Mode, Integer> {
	public List<Mode> findAllByOrderByIdDesc();
}
