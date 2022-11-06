package com.libraryland.controllers;

import com.libraryland.entities.Genre;
import com.libraryland.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class Genre_Controller {
    @Autowired
    private GenreService genreService;

    @GetMapping("/admin/genres/list")
    public String genres(Model model) {
        try {
            List<Genre> genres = genreService.findAll();
            model.addAttribute("genres", genres);

            return "views/admin/genres/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/admin/genres/{id}")
    public String genre(@PathVariable("id") long id, Model model) {
        try {
            if (id == 0) {
                model.addAttribute("genre", new Genre());
            } else {
                Genre genre = genreService.findById(id);
                model.addAttribute("genre", genre);
            }

            return "views/admin/genres/form";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/admin/genres/{id}")
    public String genre(@PathVariable("id") Long id, @Valid @ModelAttribute("genre") Genre genre, BindingResult genreResult) {
        try {
            if (genreResult.hasErrors()) {
                return "views/admin/genres/form";
            }

            if (id == 0) {
                genreService.save(genre);
            } else {
                genreService.update(id, genre);
            }

            return "redirect:/admin/genres/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/admin/genres/delete/{id}")
    public String deleteGenreView(Model model, @PathVariable("id") Long id) {
        try {
            model.addAttribute("genre", genreService.findById(id));
            return "views/admin/genres/delete";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/admin/genres/delete/{id}")
    public String deleteGenre(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            genreService.delete(id);
            return "redirect:/admin/genres/list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el Género. Tiene libros asociados.");

            return "redirect:/admin/genres/delete/" + id;
        }
    }
}
