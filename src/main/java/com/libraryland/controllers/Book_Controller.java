package com.libraryland.controllers;

import com.libraryland.entities.Book;
import com.libraryland.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "")
public class Book_Controller {

    @Autowired
    protected BookServiceImpl bookService;

    @GetMapping("")
    public String index(@RequestParam Map<String, Object> params, Model model) {
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 20);
        Page<Book> books = null;

        try {
            books = bookService.search(params.get("filtro").toString(), pageRequest);
            model.addAttribute("books", books);

            return "views/search";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        try {
            Book book = bookService.findById(id);
            model.addAttribute("book", book);

            return "views/detail";
        } catch (Exception e) {
            return "error";
        }
    }
}
