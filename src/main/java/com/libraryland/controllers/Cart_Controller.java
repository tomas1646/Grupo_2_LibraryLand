package com.libraryland.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libraryland.entities.Book;
import com.libraryland.entities.Cart;
import com.libraryland.entities.CartDetail;
import com.libraryland.entities.User;
import com.libraryland.services.BookServiceImpl;
import com.libraryland.services.CartServiceImpl;
import com.libraryland.services.UserServiceImpl;

@Controller
@CrossOrigin(origins = "*")
public class Cart_Controller {
    @Autowired
    CartServiceImpl cartService;

    @Autowired
    protected BookServiceImpl bookService;

    @Autowired
    protected UserServiceImpl userService;

    @PostMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long id, Model model,Authentication authentication){        
        if (authentication != null){            
            try {
                
                String username =  authentication.getName();            
                Optional<User> currentUser = userService.findByName(username);
                if(currentUser.isPresent()){
                    User user = currentUser.get();
                    Cart cart = user.getCart();
                    Book book = bookService.findById(id);
                    CartDetail cartDetail = CartDetail.builder().cart(cart).book(book).price(book.getPrice()).quantity(1).build();
                    List<CartDetail> allCartDetails= cart.getDetails();
                    allCartDetails.add(cartDetail);
                    cart.setDetails(allCartDetails);
                    cart.setQuantity(cart.getQuantity() + 1);
                    cartService.update(cart.getId(), cart);
                }else{
                    throw new Exception("El usuario no se encuentra en la base de datos");
                }
                return "redirect:/index";
                
            } catch (Exception e) {
                return "error";
            }
        }else{
            return "redirect:/login";
        }        
    }
}
