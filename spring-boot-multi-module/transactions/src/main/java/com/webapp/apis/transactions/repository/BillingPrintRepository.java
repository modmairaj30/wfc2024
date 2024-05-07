package com.webapp.apis.transactions.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.webapp.apis.transactions.domain.BillingPrint;


@Repository
public interface BillingPrintRepository extends JpaRepository<BillingPrint, Integer>, PagingAndSortingRepository<BillingPrint, Integer> {
	Page<BillingPrint> findAll(Pageable pageable);
	public Page<BillingPrint> findAllByOrderByIdDesc(Pageable pageable);
}
 