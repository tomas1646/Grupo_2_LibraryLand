package com.libraryland.controllers;

import com.libraryland.entities.Genre;
import com.libraryland.services.GenreServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/genres")
public class GenreController extends BaseControllerImpl<Genre, GenreServiceImpl> {
}
