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

import com.webapp.apis.domian.TblAutonumberLocation;
import com.webapp.apis.transactions.domain.StockReceipts;
import com.webapp.apis.transactions.domain.Stocktransfers;
import com.webapp.apis.transactions.domain.StocktransfersDetail;

@Repository
public interface StockReceiptsRepository extends JpaRepository<StockReceipts, Integer>, PagingAndSortingRepository<StockReceipts, Integer>,JpaSpecificationExecutor<StockReceipts> {
	public List<StockReceipts> findAllByOrderByIdAsc();
	@Query("SELECT location FROM StockReceipts location WHERE location.locationId IN (:locationId)")
	Page<StockReceipts> findByLocationAll(@Param("locationId")List<Integer> locationId,Pageable pageable);
	@Query(value = "SELECT (max(id)+1) FROM StockReceipts")
	public Integer getMaxId();
	@Query(value = "select d from Stocktransfers d where d.docNo=:docno")
	public Stocktransfers findByDocNo(@Param("docno") String docno);
	@Query(nativeQuery = true, value = "select d.* from stocktransfers_detail d where d.base_id=:baseId")
	public List<StocktransfersDetail> findByBaseId(@Param("baseId") int baseId);

		
/*
 * LIKE %?#{[0].toUpperCase()}%
 * List<Employee> findByEmployeeNameIn(List<String> names);                // 1. Spring JPA In cause using method name
    @Query("SELECT e FROM Employee e WHERE e.employeeName IN (:names)")     // 2. Spring JPA In cause using @Query
    List<Employee> findByEmployeeNames(@Param("names")List<String> names);
    @Query(nativeQuery =true,value = "SELECT * FROM Employee as e WHERE e.employeeName IN (:names)")   // 3. Spring JPA In cause using native query
    List<Employee> findByEmployeeName(@Param("names") List<String> names);
 */
	
}
