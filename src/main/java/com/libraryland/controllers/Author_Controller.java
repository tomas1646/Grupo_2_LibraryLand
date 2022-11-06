package com.libraryland.controllers;

import com.libraryland.entities.Author;
import com.libraryland.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class Author_Controller {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/admin/authors/list")
    public String authorList(Model model) {
        try {
            List<Author> authors = authorService.findAll();
            model.addAttribute("authors", authors);

            return "views/admin/authors/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/admin/authors/{id}")
    public String authorForm(@PathVariable("id") long id, Model model) {
        try {
            if (id == 0) {
                model.addAttribute("author", new Author());
            } else {
                Author author = authorService.findById(id);
                model.addAttribute("author", author);
            }

            return "views/admin/authors/form";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/admin/authors/{id}")
    public String authorFormAction(@PathVariable("id") Long id, @Valid @ModelAttribute("author") Author author, BindingResult authorResult) {
        try {
            if (authorResult.hasErrors()) {
                return "views/admin/authors/form";
            }

            if (id == 0) {
                authorService.save(author);
            } else {
                authorService.update(id, author);
            }

            return "redirect:/admin/authors/list";
        } catch (ValidationException e) {
            if (e.getMessage().contains(author.getFullName())) {
                authorResult.rejectValue("fullName", "error.fullName", "Ya existe un autor con ese nombre");
                return "views/admin/authors/form";
            }
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/admin/authors/delete/{id}")
    public String authorDeleteForm(Model model, @PathVariable("id") Long id) {
        try {
            model.addAttribute("author", authorService.findById(id));
            return "views/admin/authors/delete";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/admin/authors/delete/{id}")
    public String authorDeleteFormAction(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            authorService.delete(id);
            return "redirect:/admin/authors/list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el Autor. Tiene libros asociados.");

            return "redirect:/admin/authors/delete/" + id;
        }
    }
}
