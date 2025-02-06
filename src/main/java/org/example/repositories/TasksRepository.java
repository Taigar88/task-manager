package org.example.repositories;

import org.example.models.TasksDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TasksRepository extends JpaRepository<TasksDto, UUID> {
    Optional<List<TasksDto>> findByUserId(UUID UserId);
    Optional<TasksDto> findById(UUID id);

}
