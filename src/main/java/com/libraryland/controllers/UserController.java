package com.libraryland.controllers;

import com.libraryland.entities.User;
import com.libraryland.services.UserServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/users")
public class UserController extends BaseControllerImpl<User, UserServiceImpl> {
}
