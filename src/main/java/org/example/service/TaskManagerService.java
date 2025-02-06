package org.example.service;

import org.example.endpoints.responses.ApiResponse;
import org.example.models.TasksDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface TaskManagerService {

    ResponseEntity<ApiResponse> createRequest(TasksDto request);
    ResponseEntity<ApiResponse> deleteRequest(UUID id);
    ResponseEntity<ApiResponse> updateRequest(TasksDto request, UUID id);
    ResponseEntity<ApiResponse> completeRequest(UUID id);
    ResponseEntity<?> ListUserTasksRequest(UUID userId);
}
