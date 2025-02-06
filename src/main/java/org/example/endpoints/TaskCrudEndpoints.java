package org.example.endpoints;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.endpoints.responses.ApiResponse;
import org.example.models.TasksDto;
import org.example.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/task", consumes = "application/json")

public class TaskCrudEndpoints {

    @Autowired
    private TaskManagerService taskManagerService;

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ApiResponse> createRequest(@RequestBody TasksDto Request) {
        return taskManagerService.createRequest(Request);
    }

    @DeleteMapping(path = "/delete/{taskId}", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> deleteRequest(@PathVariable String taskId) {
        var id = UUID.fromString(taskId);
        return taskManagerService.deleteRequest(id);
    }

    @PatchMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ApiResponse> updateRequest(@RequestBody TasksDto Request, @PathVariable String id){
        var taskId = UUID.fromString(id);
        return taskManagerService.updateRequest(Request, taskId);
    }

    @GetMapping(path = "/complete/{taskId}", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ApiResponse> completeRequest(@PathVariable String taskId) {
        var id = UUID.fromString(taskId);
        return taskManagerService.completeRequest(id);
    }

    @GetMapping(path = "/list/{userId}", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?> ListUserTaskRequest(@PathVariable String userId) {
        var id = UUID.fromString(userId);
        return taskManagerService.ListUserTasksRequest(id);
    }
}

