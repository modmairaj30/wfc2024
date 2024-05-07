package com.webapp.apis.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.apis.masters.domain.Tutorial;



public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
