package edu.uniformix.api.repositories;

import edu.uniformix.api.domain.Batch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Batch b WHERE b.batchCode = :code")
    Boolean existsByCode(@Param("code") String code);

    @Query("SELECT b FROM Batch b WHERE b.batchCode = :code")
    Batch findByCode(@Param("code") String code);

    @Query("SELECT b FROM Batch b WHERE LOWER(b.description) LIKE %:search% OR LOWER(b.batchCode) LIKE %:search%")
    Page<Batch> findByText(@Param("search") String search, Pageable pageable);

    @Query("SELECT COUNT(b) FROM Batch b")
    Long countTotalRows();
}
