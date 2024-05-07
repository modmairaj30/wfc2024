package com.webapp.apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webapp.apis.domian.TblAutoNumber;
import com.webapp.apis.domian.TblAutonumberLocation;

public interface TblAutoNumberRepository extends JpaRepository<TblAutoNumber, Integer> {
	@Modifying	  
	  @Query(value = "UPDATE TblAutoNumber s SET s.number=s.number+1    WHERE s.txnId=:txnId ")
	  public Integer updateNumber(@Param("txnId") Integer txnId);
	
	@Query("SELECT n FROM TblAutoNumber n WHERE n.txnId = ?1 ")
		public TblAutoNumber findByTxnId(Integer txnId);
		
		
		
		
		  
}