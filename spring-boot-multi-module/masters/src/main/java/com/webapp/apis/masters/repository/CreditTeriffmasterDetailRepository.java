package com.webapp.apis.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webapp.apis.masters.domain.CreditTeriffmasterDetail;


@Repository
public interface CreditTeriffmasterDetailRepository extends JpaRepository<CreditTeriffmasterDetail, Integer> {

}
