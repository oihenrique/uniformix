package edu.uniformix.api.repositories;

import edu.uniformix.api.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Supplier s WHERE s.code = :code")
    Boolean existsByCode(@Param("code") String code);

    @Query("SELECT s FROM Supplier s WHERE s.name = :name")
    Supplier findByName(@Param("name") String name);
}
