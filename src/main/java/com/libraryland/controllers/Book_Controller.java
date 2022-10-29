package com.libraryland.controllers;

import com.libraryland.entities.Book;
import com.libraryland.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value="books")
public class Book_Controller {

    @Autowired
    protected BookServiceImpl bookService;

    @GetMapping("")
    public String index(@RequestParam Map<String,Object> params, Model model){
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString())-1):0;
        PageRequest pageRequest = PageRequest.of(page,20);
        Page<Book> books = null;
        try {
            books = bookService.findAll(pageRequest);
            model.addAttribute("books", books);
            model.addAttribute("totalPages", books.getTotalPages());

            return "index";
        } catch (Exception e) {
            return "error";
        }

    }
}
