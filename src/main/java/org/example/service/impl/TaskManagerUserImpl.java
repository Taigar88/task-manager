package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.endpoints.responses.ApiResponse;
import org.example.exceptions.TaskManagerException;
import org.example.models.UserDto;
import org.example.repositories.UserRepository;
import org.example.service.TaskManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskManagerUserImpl implements TaskManagerUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public ResponseEntity<ApiResponse> createUserRequest(UserDto request) {
        try{
            request.setPassword(encoder.encode(request.getPassword()));
            userRepository.save(request);
        }catch(TaskManagerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.of(Set.of("User create error : " + e.getMessage())));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ofSuccess(Set.of("User Created")));
    }
    @Override
    public ResponseEntity<ApiResponse> deleteUserRequest(UUID id) {
        try{
            var users = userRepository.findById(id);
            if(users.isPresent()){
                var user = users.stream().findFirst().orElseThrow();
                if(!user.getRole().equals("ROLE_ADMIN")){
                    throw new TaskManagerException("Unauthorised user not administrator");
                }else{
                    userRepository.delete(user);
                }
            }else{
                throw new TaskManagerException("No user found");
            }

        }catch(TaskManagerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.of(Set.of("User delete error : " + e.getMessage())));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ofSuccess(Set.of("User Deleted")));
    }
    @Override
    public ResponseEntity<ApiResponse> updateUserRequest(UserDto request, UUID id) {
        try{
            var users = userRepository.findById(id);
            if(users.isPresent()){
                var user = users.stream().findFirst().orElseThrow();
                user.setName(request.getName());
                user.setSurname(request.getSurname());
                userRepository.saveAndFlush(user);
            }else{
                throw new TaskManagerException("No user found");
            }

        }catch(TaskManagerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.of(Set.of("User update error : " + e.getMessage())));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ofSuccess(Set.of("User Updated")));
    }
}
