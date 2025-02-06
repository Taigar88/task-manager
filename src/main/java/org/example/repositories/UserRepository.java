package org.example.repositories;

import org.example.models.TasksDto;
import org.example.models.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserDto, UUID> {
    Optional<UserDto> findById(UUID id);
    Optional<UserDto> findByEmail(String email);

}
