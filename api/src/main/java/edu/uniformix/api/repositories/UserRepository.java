package edu.uniformix.api.repositories;

import edu.uniformix.api.domain.User;
import edu.uniformix.api.domain.dtos.user.UserListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    Page<UserListDto> findAllByActiveTrue(Pageable paginate);

    UserDetails findByLogin(String login);
}