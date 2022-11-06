package com.libraryland.controllers;

import com.libraryland.entities.Author;
import com.libraryland.entities.Book;
import com.libraryland.entities.Genre;
import com.libraryland.services.AuthorServiceImpl;
import com.libraryland.services.BookServiceImpl;
import com.libraryland.services.GenreServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "")
public class Book_Controller {

    @Autowired
    protected BookServiceImpl bookService;
    @Autowired
    protected GenreServiceImpl genreService;
    @Autowired
    protected AuthorServiceImpl authorService;

    @GetMapping("")
    public String index(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 20);
        Page<Book> books = null;
        List<Author> allAuthors;         
        List<Genre> allGenres;
        try {
            books = bookService.findAll(pageRequest);
            model.addAttribute("books", books);

            int totalPages = books.getTotalPages();
            if(totalPages>0){
                List<Integer> pages = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
                model.addAttribute("pages",pages);
                model.addAttribute("currentPage", page+1);
                model.addAttribute("nextPage", page+2);
                model.addAttribute("prevPage", page);
                model.addAttribute("lastPage", totalPages);
            }
            allAuthors = authorService.findAll();
            allGenres = genreService.findAll();
            model.addAttribute("authors", allAuthors);
            model.addAttribute("genres", allGenres);
            return "index";
        } catch (Exception e) {
            return "error";
        }
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

    @GetMapping("/searchByGenre")
    public String getAllByGenre(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 20);
        Page<Book> books = null;

        try {
            books = bookService.findByGenre(pageRequest, params.get("genre").toString());
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
