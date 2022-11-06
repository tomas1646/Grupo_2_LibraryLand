package com.libraryland.controllers;

import com.libraryland.entities.Address;
import com.libraryland.entities.User;
import com.libraryland.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
@CrossOrigin(origins = "*")
public class User_Controller {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "views/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());
        return "views/register";
    }

    @PostMapping("/perform_register")
    public String perform_register(@Valid @ModelAttribute("user") User user, BindingResult userResult,
                                   @Valid @ModelAttribute("address") Address address, BindingResult addressResult) {

        try {
            if (userResult.hasErrors() || addressResult.hasErrors()) {
                return "views/register";
            }
            user.setAddress(address);
            userService.save(user);
            return "redirect:/login";
        } catch (ValidationException e) {
            if (e.getMessage().contains(user.getUsername())) {
                userResult.rejectValue("username", "error.user", "El nombre de usuario esta ocupado");
                return "views/register";
            }
            if (e.getMessage().contains(user.getEmail())) {
                userResult.rejectValue("email", "error.user", "El mail esta registrado por otra persona");
                return "views/register";
            }
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "views/register";
        }
    }

    @GetMapping("/admin")
    public String admin(){
        return "views/admin/admin";
    }
}
