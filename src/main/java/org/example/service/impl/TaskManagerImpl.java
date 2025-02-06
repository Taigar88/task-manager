package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.endpoints.responses.ApiResponse;
import org.example.exceptions.TaskManagerException;
import org.example.models.TasksDto;
import org.example.repositories.TasksRepository;
import org.example.service.TaskManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskManagerImpl implements TaskManagerService {
    private final TasksRepository tasksRepository;
    public ResponseEntity<ApiResponse> createRequest(TasksDto request){
        try{
            if(request != null){
                request.setStatus("created");
                request.setUpdateAt(Timestamp.valueOf(LocalDateTime.now(ZoneId.of( "Africa/Johannesburg"))));
                tasksRepository.save(request);
            }
        }catch (TaskManagerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.of(Set.of("Task create error : " + e.getMessage())));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ofSuccess(Set.of("Task Created")));
    }
    public ResponseEntity<ApiResponse> deleteRequest(UUID id){
        try{
            var tasks = tasksRepository.findById(id);
            if(tasks.isPresent()){
                var task = tasks.stream().findFirst().orElseThrow();
                tasksRepository.delete(task);
            }else{
                throw new TaskManagerException("failed to find record to delete");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.of(Set.of("Task delete error : " + e.getMessage())));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ofSuccess(Set.of("Task Deleted")));
    }
    public ResponseEntity<ApiResponse> updateRequest(TasksDto request, UUID id){
        try{
            var tasks = tasksRepository.findById(id);
            if(tasks.isPresent()){
                var task = tasks.stream().findFirst().orElseThrow();
                task.setDescription(request.getDescription());
                task.setName(request.getName());
                if(request.getStatus() != null){
                    task.setStatus(request.getStatus());
                }
                task.setUpdateAt(Timestamp.valueOf(LocalDateTime.now(ZoneId.of( "Africa/Johannesburg"))));
                tasksRepository.saveAndFlush(task);
            }else{
                throw new TaskManagerException("Failed to find record to update");
            }
        }catch(TaskManagerException e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ApiResponse.of(Set.of("Task update error : " + e.getMessage())));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ofSuccess(Set.of("Task Updated")));
    }
    public ResponseEntity<ApiResponse> completeRequest(UUID id){
        try{
            var tasks = tasksRepository.findById(id);
            if(tasks.isPresent()){
                var task = tasks.stream().findFirst().orElseThrow();
                task.setStatus("Completed");
                task.setUpdateAt(Timestamp.valueOf(LocalDateTime.now(ZoneId.of( "Africa/Johannesburg"))));
                tasksRepository.saveAndFlush(task);
            }else{
                throw new TaskManagerException("Failed to find record to update status");
            }
        }catch (TaskManagerException e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ApiResponse.of(Set.of("Task update error : " + e.getMessage())));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ofSuccess(Set.of("Task Completed")));
    }

    public ResponseEntity<?> ListUserTasksRequest(UUID userId){
        List<List<TasksDto>> list;
        try{
            var tasks = tasksRepository.findByUserId(userId);
            if(tasks.isPresent()){
                list = tasks.stream().toList();
            }else{
                throw new TaskManagerException("No tasks available");
            }
        }catch(TaskManagerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.of(Set.of("Task list error : " + e.getMessage())));
        }

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ofSuccess(Set.of(list)));
    }
}
