package com.libraryland.controllers;

import com.libraryland.entities.Address;
import com.libraryland.entities.User;
import com.libraryland.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
@CrossOrigin(origins = "*")
public class User_Controller {

    @Autowired
    private UserServiceImpl userService;

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

    @GetMapping("/users")
    public String getUser(Model model, Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userService.findByName(username).get();
            Address address = user.getAddress();
            model.addAttribute("user", user);
            model.addAttribute("address", address);
            return "views/userDetail";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/users")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult userResult,
                             @Valid @ModelAttribute("address") Address address, BindingResult addressResult) {

        try {
            if (userResult.hasErrors() || addressResult.hasErrors()) {
                return "views/userDetail";
            }
            user.setAddress(address);
            userService.update(user.getId(), user);
            return "redirect:/";
        } catch (ValidationException e) {
            if (e.getMessage().contains(user.getUsername())) {
                userResult.rejectValue("username", "error.user", "El nombre de usuario esta ocupado");
                return "views/userDetail";
            }
            if (e.getMessage().contains(user.getEmail())) {
                userResult.rejectValue("email", "error.user", "El mail esta registrado por otra persona");
                return "views/userDetail";
            }
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/admin")
    public String admin() {
        return "views/admin/admin";
    }
}
