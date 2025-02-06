package org.example.endpoints;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.endpoints.responses.ApiResponse;
import org.example.models.AuthRequest;
import org.example.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.example.models.UserDto;
import org.example.service.TaskManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user", consumes = "application/json")
public class TaskManagerUserEndpoint {
    @Autowired
    private TaskManagerUserService taskManagerUserService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse> createUserRequest(@RequestBody UserDto Request) {
        return taskManagerUserService.createUserRequest(Request);
    }

    @DeleteMapping(path = "/delete/{UserId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse> deleteRequest(@PathVariable String UserId) {
        var id = UUID.fromString(UserId);
        return taskManagerUserService.deleteUserRequest(id);
    }

    @PatchMapping(path = "/update/{UserId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse> updateRequest(@RequestBody UserDto Request, @PathVariable String UserId){
        var id = UUID.fromString(UserId);
        return taskManagerUserService.updateUserRequest(Request,id);
    }

    @GetMapping("/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ApiResponse> userProfile() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ofSuccess(Set.of("User profile")));
    }

    @GetMapping("/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> adminProfile() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ofSuccess(Set.of("ADMIN profile")));
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
