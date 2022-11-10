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
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/findCart")
    public String findCart(Model model, Authentication authentication) {
        if (authentication != null){            
            try {
                
                String username =  authentication.getName();
                Optional<User> currentUser = userService.findByName(username);
                if(currentUser.isPresent()){
                    User user = currentUser.get();
                    Cart cart = user.getCart();                                        
                    List<CartDetail> allCartDetails= cart.getDetails();                    

                    model.addAttribute("cart", cart);
                    double total = 550;
                    for(int i = 0;i < allCartDetails.size();i++){
                        total = total + allCartDetails.get(i).getPrice();
                    }                                               
                    model.addAttribute("total", total);            
                }else{
                    throw new Exception("El usuario no se encuentra en la base de datos");
                }
                return "views/cart";
                
            } catch (Exception e) {
                return "error";
            }
        }else{
            return "redirect:/login";
        }
    }

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
                    if(book.getStock() < 1){
                        throw new Exception("No hay stock");    
                    }else{
                        CartDetail cartDetail = CartDetail.builder().cart(cart).book(book).price(book.getPrice()).quantity(1).build();
                        List<CartDetail> allCartDetails= cart.getDetails();
                        allCartDetails.add(cartDetail);
                        cart.setDetails(allCartDetails);
                        cart.setQuantity(cart.getQuantity() + 1);
                        cartService.update(cart.getId(), cart);
                    }                                        
                }else{
                    throw new Exception("El usuario no se encuentra en la base de datos");
                }
                return "redirect:/";
                
            } catch (Exception e) {
                return "error";
            }
        }else{
            return "redirect:/login";
        }        
    }

    @PostMapping("/deleteCartDetail/{id}")
    public String deleteCartDetail(@PathVariable("id") Long id, Model model,Authentication authentication){

        if (authentication != null){
            try {
                String username =  authentication.getName();
                Optional<User> currentUser = userService.findByName(username);

                if(currentUser.isPresent()){
                    User user = currentUser.get();
                    Cart cart = user.getCart();
                    Book book = bookService.findById(id);
                    List<CartDetail> cartDetails = cart.getDetails();
                    cartDetails.removeIf(d->d.getBook().getId()==book.getId());
                    cartService.update(cart.getId(), cart);
                }else{
                    throw new Exception("El usuario no se encuentra en la base de datos");
                }
                return "redirect:/findCart";

            } catch (Exception e) {
                return "error";
            }
        }else{
            return "redirect:/login";
        }
    }
}
