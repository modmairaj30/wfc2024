package com.webapp.apis.masters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.apis.masters.domain.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
	public List<Status> findAllByOrderByIdDesc();
}
