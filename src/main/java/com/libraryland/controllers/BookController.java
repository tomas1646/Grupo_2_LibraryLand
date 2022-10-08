package com.libraryland.controllers;

import com.libraryland.entities.Book;
import com.libraryland.services.BookServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/books")
public class BookController extends BaseControllerImpl<Book, BookServiceImpl> {
}
