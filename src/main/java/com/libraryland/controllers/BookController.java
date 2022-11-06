package com.libraryland.controllers;

import com.libraryland.entities.Book;
import com.libraryland.services.BookServiceImpl;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/books")
public class BookController extends BaseControllerImpl<Book, BookServiceImpl> {
    @GetMapping("/byGenre/{genre}")
    public ResponseEntity<?> getByGenre(Pageable pageable,@PathVariable String genre){
        try{
            return ResponseEntity.status((HttpStatus.OK)).body(service.findByGenre(pageable,genre));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }
}
