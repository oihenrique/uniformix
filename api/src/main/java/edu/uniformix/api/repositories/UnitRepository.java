package edu.uniformix.api.repositories;

import edu.uniformix.api.domain.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    @Query("SELECT u FROM Unit u WHERE u.name = :name")
    Unit findByName(@Param("name") String name);

    @Query("SELECT u FROM Unit u WHERE u.active = true")
    Page<Unit> findAllActive(Pageable pageable);
}
