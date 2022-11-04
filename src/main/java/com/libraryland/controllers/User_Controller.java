package com.libraryland.controllers;

import com.libraryland.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
public class User_Controller {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String index(@RequestParam Map<String, Object> params, Model model) {
        return "views/login";
    }
}
