package edu.uniformix.api.controllers;

import edu.uniformix.api.domain.Users;
import edu.uniformix.api.domain.dtos.user.UpdateUserDto;
import edu.uniformix.api.domain.dtos.user.UserDto;
import edu.uniformix.api.domain.dtos.user.UserListDto;
import edu.uniformix.api.repositories.UserRepository;
import edu.uniformix.api.services.UtilsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<UserListDto> register(@RequestBody @Valid UserDto userDTO, UriComponentsBuilder uriBuilder) {
        if (repository.findByLogin(userDTO.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        Users users = new Users(userDTO, encryptedPassword);
        repository.save(users);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(users.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserListDto(users));
    }

    @GetMapping
    public ResponseEntity<Page<UserListDto>> list(@PageableDefault(sort = "name") Pageable paginate) {
        Page<UserListDto> userList = repository.findAllByActiveTrue(paginate);

        return ResponseEntity.ok(userList);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> update(@RequestBody @Valid UpdateUserDto updateUserDTO, @PathVariable String id) {
        Users users = repository.findById(id).orElse(null);

        if (users == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        UtilsService.copyNonNullProperties(updateUserDTO, users);

        return ResponseEntity.ok(new UserListDto(users));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deactivate(@PathVariable String id) {
        Users users = repository.findById(id).orElse(null);

        if (users == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> detail(@PathVariable String id) {
        Users users = repository.findById(id).orElse(null);

        if (users == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        return ResponseEntity.ok(new UserListDto(users));
    }
}