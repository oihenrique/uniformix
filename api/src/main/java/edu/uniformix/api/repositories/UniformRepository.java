package edu.uniformix.api.repositories;

import edu.uniformix.api.domain.Uniform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UniformRepository extends JpaRepository<Uniform, Long> {
    @Query("SELECT u FROM Uniform u WHERE LOWER(u.name) = LOWER(:name)")
    Uniform findByName(@Param("name") String name);
}
