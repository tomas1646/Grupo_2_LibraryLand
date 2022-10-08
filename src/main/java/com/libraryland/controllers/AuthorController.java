package com.libraryland.controllers;

import com.libraryland.entities.Author;
import com.libraryland.services.AuthorServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/authors")
public class AuthorController extends BaseControllerImpl<Author, AuthorServiceImpl> {
}
