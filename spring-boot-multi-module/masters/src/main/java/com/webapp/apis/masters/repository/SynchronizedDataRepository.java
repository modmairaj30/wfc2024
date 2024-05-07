package com.webapp.apis.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.apis.masters.domain.SynchronizedData;

@Repository
public interface SynchronizedDataRepository extends JpaRepository <SynchronizedData, Integer>{

}