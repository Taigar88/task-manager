package org.example.service;

import org.example.endpoints.responses.ApiResponse;
import org.example.models.TasksDto;
import org.example.models.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface TaskManagerUserService {
    ResponseEntity<ApiResponse> createUserRequest(UserDto request);
    ResponseEntity<ApiResponse> deleteUserRequest(UUID id);
    ResponseEntity<ApiResponse> updateUserRequest(UserDto request, UUID id);
}
