package com.webapp.apis.masters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.apis.masters.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
	public List<Location> findAllByOrderByIdDesc();
}
