package com.webapp.apis.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.apis.transactions.domain.BillingPrintDetail;

@Repository
public interface BillingPrintDetailRepository extends JpaRepository<BillingPrintDetail, Integer> {

}
