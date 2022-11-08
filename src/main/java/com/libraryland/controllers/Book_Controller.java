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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
        boolean fromFiltersFlag = false;
        try {
            books = bookService.findAll(pageRequest);
            model.addAttribute("books", books);
            model.addAttribute("fromFiltersFlag", fromFiltersFlag);

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
        boolean fromFiltersFlag = true;

        try {
            books = bookService.search(params.get("filtro").toString(), pageRequest);
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
            model.addAttribute("fromFiltersFlag", fromFiltersFlag);
            model.addAttribute("filter", "filtro=" + params.get("filtro").toString());

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
        List<Author> allAuthors;         
        List<Genre> allGenres;
        boolean fromFiltersFlag = true;

        try {
            books = bookService.findByGenre(pageRequest, params.get("genre").toString());
            model.addAttribute("books", books);
            allAuthors = authorService.findAll();
            allGenres = genreService.findAll();
            model.addAttribute("authors", allAuthors);
            model.addAttribute("genres", allGenres);
            model.addAttribute("fromFiltersFlag", fromFiltersFlag);
            model.addAttribute("filter", "genre=" + params.get("genre").toString());
            int totalPages = books.getTotalPages();
            if(totalPages>0){
                List<Integer> pages = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
                model.addAttribute("pages",pages);
                model.addAttribute("currentPage", page+1);
                model.addAttribute("nextPage", page+2);
                model.addAttribute("prevPage", page);
                model.addAttribute("lastPage", totalPages);
            }

            return "index";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/searchByAuthor")
    public String getAllByAuthor(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 20);
        Page<Book> books = null;
        List<Author> allAuthors;         
        List<Genre> allGenres;
        boolean fromFiltersFlag = true;

        try {
            books = bookService.findByAuthor(pageRequest, params.get("author").toString());
            model.addAttribute("books", books);
            allAuthors = authorService.findAll();
            allGenres = genreService.findAll();
            model.addAttribute("authors", allAuthors);
            model.addAttribute("genres", allGenres);
            model.addAttribute("fromFiltersFlag", fromFiltersFlag);
            model.addAttribute("filter", "author=" + params.get("author").toString());
            int totalPages = books.getTotalPages();
            if(totalPages>0){
                List<Integer> pages = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
                model.addAttribute("pages",pages);
                model.addAttribute("currentPage", page+1);
                model.addAttribute("nextPage", page+2);
                model.addAttribute("prevPage", page);
                model.addAttribute("lastPage", totalPages);
            }

            return "index";
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

    @GetMapping("/admin/books/list")
    public String bookAdminList(Model model) {
        try {
            List<Book> books = bookService.findAll();
            model.addAttribute("books", books);

            return "views/admin/books/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/admin/books/{id}")
    public String bookForm(@PathVariable("id") long id, Model model) {
        try {
            List<Author> authors = authorService.findAll();
            List<Genre> genres = genreService.findAll();
            model.addAttribute("authors", authors);
            model.addAttribute("genres", genres);

            if (id == 0) {
                model.addAttribute("book", new Book());
            } else {
                Book book = bookService.findById(id);
                model.addAttribute("book", book);
            }

            return "views/admin/books/form";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/admin/books/{id}")
    public String bookFormAction(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult bookResult, Model model) {
        try {
            if (bookResult.hasErrors()) {
                List<Author> authors = authorService.findAll();
                List<Genre> genres = genreService.findAll();
                model.addAttribute("authors", authors);
                model.addAttribute("genres", genres);

                return "views/admin/books/form";
            }

            if (id == 0) {
                bookService.save(book);
            } else {
                bookService.update(id, book);
            }

            return "redirect:/admin/books/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/admin/books/delete/{id}")
    public String bookDeleteForm(Model model, @PathVariable("id") Long id) {
        try {
            model.addAttribute("book", bookService.findById(id));
            return "views/admin/books/delete";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/admin/books/delete/{id}")
    public String bookDeleteFormAction(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.delete(id);
            return "redirect:/admin/books/list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el Libro. Tiene ordenes o carritos asociados.");

            return "redirect:/admin/books/delete/" + id;
        }
    }
}
