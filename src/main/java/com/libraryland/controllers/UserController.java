package com.libraryland.controllers;

import com.libraryland.entities.User;
import com.libraryland.entities.dto.LoginDTO;
import com.libraryland.services.UserServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/users")
public class UserController extends BaseControllerImpl<User, UserServiceImpl> {
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            User user = service.login(loginDTO.getUsername(), loginDTO.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }
}
