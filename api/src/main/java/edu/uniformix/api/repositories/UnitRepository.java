package edu.uniformix.api.repositories;

import edu.uniformix.api.domain.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
