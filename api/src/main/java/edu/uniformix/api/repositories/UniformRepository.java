package edu.uniformix.api.repositories;

import edu.uniformix.api.domain.Uniform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniformRepository extends JpaRepository<Uniform, Long> {
}
