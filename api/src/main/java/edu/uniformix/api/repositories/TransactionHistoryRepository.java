package edu.uniformix.api.repositories;

import edu.uniformix.api.domain.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, String> {
    @Query("SELECT t FROM TransactionHistory t WHERE LOWER(t.employeeName) LIKE %:search%")
    Page<TransactionHistory> findByEmployeeName(@Param("search") String search, Pageable pageable);
}
