package com.webapp.apis.transactions.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webapp.apis.transactions.domain.StockInfo;
import com.webapp.apis.transactions.domain.Stockincnote;
import com.webapp.apis.transactions.domain.Stocktransfers;
 
@Repository
public interface StockInCNoteRepository extends JpaRepository<Stockincnote, Integer>, PagingAndSortingRepository<Stockincnote, Integer>,JpaSpecificationExecutor<Stockincnote> {
	
	public List<Stockincnote> findAllByOrderByIdAsc();
	@Query("SELECT location FROM Stockincnote location WHERE location.locationId IN (:locationId)")
	Page<Stockincnote> findByLocationAll(@Param("locationId")List<Integer> locationId,Pageable pageable);
	@Query(value = "SELECT (max(id)+1) FROM Stockincnote")
	public Integer getMaxId();
	//@Query("SELECT c FROM Stockincnote c WHERE c.cPrefix=:cPrefix AND c.fromNo>=:fromNo AND c.toNo<=:toNo")
	@Query(  value = "SELECT count(*) FROM stockincnote h, stockincnote_detail d where h.id=d.base_id    and  h.c_prefix=:cPrefix and d.s_no BETWEEN :fromNo  AND :toNo ",  nativeQuery = true)
	public int findByPrefixAndFromNoAndToNo1(@Param("cPrefix") String cPrefix,@Param("fromNo") String fromNo,@Param("toNo") String toNo);
	
	

	
}
