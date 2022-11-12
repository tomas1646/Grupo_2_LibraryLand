package com.libraryland.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

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
import com.libraryland.entities.Order;
import com.libraryland.entities.OrderDetail;
import com.libraryland.entities.User;
import com.libraryland.services.BookServiceImpl;
import com.libraryland.services.CartServiceImpl;
import com.libraryland.services.OrderServiceImpl;
import com.libraryland.services.UserServiceImpl;

import javax.naming.OperationNotSupportedException;

@Controller
@CrossOrigin(origins = "*")
public class Cart_Controller {
    @Autowired
    CartServiceImpl cartService;

    @Autowired
    protected BookServiceImpl bookService;

    @Autowired
    protected UserServiceImpl userService;

    @Autowired
    protected OrderServiceImpl orderService;

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
                        total = total + (allCartDetails.get(i).getPrice() * allCartDetails.get(i).getQuantity());
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
                    }

                    List<CartDetail> allCartDetails = cart.getDetails();
                    if(allCartDetails.stream().anyMatch(cd->cd.getBook().getId() == book.getId())){
                        cart = incrementCartDetailQuantity(cart, book);
                        cartService.update(cart.getId(), cart);
                        return "redirect:/findCart";
                    }

                    CartDetail cartDetail = CartDetail.builder().cart(cart).book(book).price(book.getPrice()).quantity(1).build();

                    allCartDetails.add(cartDetail);
                    cart.setDetails(allCartDetails);
                    cart.setQuantity(cart.getQuantity() + 1);
                    cartService.update(cart.getId(), cart);

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
    @PostMapping("/reduceQuantity/{id}")
    public String reduceQuantity(@PathVariable("id") Long id, Model model,Authentication authentication) {
        if (authentication != null){
            try {
                String username =  authentication.getName();
                Optional<User> currentUser = userService.findByName(username);
                if(currentUser.isPresent()){
                    User user = currentUser.get();
                    Cart cart = user.getCart();
                    Book book = bookService.findById(id);
                    List<CartDetail> allCartDetails = cart.getDetails();

                    CartDetail oCartDetail = allCartDetails.stream().filter(cd->cd.getBook().getId() == book.getId()).findFirst().get();
                    if(oCartDetail.getQuantity()==1){
                        return "redirect:/findCart";
                    }
                    allCartDetails.remove(oCartDetail);
                    oCartDetail.setQuantity(oCartDetail.getQuantity()-1);
                    allCartDetails.add(oCartDetail);
                    cart.setDetails(allCartDetails);
                    cart.setQuantity(cart.getQuantity() - 1);

                    cartService.update(cart.getId(), cart);
                    return "redirect:/findCart";

                }else{
                    throw new Exception("El usuario no se encuentra en la base de datos");
                }
            } catch (Exception e) {
                return "error";
            }
        }else{
            return "redirect:/login";
        }
    }

    @PostMapping("/incrementQuantity/{id}")
    public String incrementQuantity(@PathVariable("id") Long id, Model model,Authentication authentication) {
        if (authentication != null){
            try {

                String username =  authentication.getName();
                Optional<User> currentUser = userService.findByName(username);
                if(currentUser.isPresent()){
                    User user = currentUser.get();
                    Cart cart = user.getCart();
                    Book book = bookService.findById(id);

                    List<CartDetail> allCartDetails = cart.getDetails();

                    cart = incrementCartDetailQuantity(cart, book);
                    cartService.update(cart.getId(), cart);
                    return "redirect:/findCart";

                }else{
                    throw new Exception("El usuario no se encuentra en la base de datos");
                }
            } catch (Exception e) {
                return "error";
            }
        }else{
            return "redirect:/login";
        }
    }

    private Cart incrementCartDetailQuantity(Cart cart, Book book) throws Exception {
        List<CartDetail> allCartDetails = cart.getDetails();
        CartDetail oCartDetail = allCartDetails.stream().filter(cd->cd.getBook().getId() == book.getId()).findFirst().get();
        if(oCartDetail.getQuantity()==book.getStock()){
            return cart;
        }
        allCartDetails.remove(oCartDetail);
        oCartDetail.setQuantity(oCartDetail.getQuantity()+1);
        allCartDetails.add(oCartDetail);
        cart.setDetails(allCartDetails);
        cart.setQuantity(cart.getQuantity() + 1);
        return cart;
    }

    @PostMapping("/payCart")
    public String payCart(Model model,Authentication authentication) {
        if (authentication != null){
            try {
                String username =  authentication.getName();
                Optional<User> currentUser = userService.findByName(username);
                if(currentUser.isPresent()){
                    User user = currentUser.get();
                    Cart cart = user.getCart();
                    List<CartDetail> allCartDetails = cart.getDetails();
                    if(allCartDetails.isEmpty()){
                       return "redirect:/findCart" ;
                    }
                    List<OrderDetail> allOrderDetails = new ArrayList<OrderDetail>();

                    Random random = new Random();
                    Order newOrder = Order.builder().number(random.nextInt() & Integer.MAX_VALUE).date(new Date()).user(user).build();
                    double total = 0;
                    for(int i=0;i<allCartDetails.size();i++){                        
                        OrderDetail newOrderDerail = OrderDetail.builder().book(allCartDetails.get(i).getBook()).price(allCartDetails.get(i).getPrice()).quantity(allCartDetails.get(i).getQuantity()).order(newOrder).build();
                        allOrderDetails.add(newOrderDerail);
                        total = total + allCartDetails.get(i).getPrice();                                                
                        Book book = allCartDetails.get(i).getBook();
                        book.setStock(book.getStock()-allCartDetails.get(i).getQuantity());
                        bookService.update(book.getId(), book);
                    }
                    int size = allCartDetails.size();
                    for(int i = 0;i<size;i++){
                        allCartDetails.remove(0);                        
                    }
                    newOrder.setTotal(total+550);
                    newOrder.setDetails(allOrderDetails);
                    orderService.save(newOrder);                                                     
                    cart.setQuantity(0);
                    cartService.update(cart.getId(), cart);
                    return "redirect:/orders";

                }else{
                    throw new Exception("El usuario no se encuentra en la base de datos");
                }
            } catch (Exception e) {
                return "error";
            }
        }else{
            return "redirect:/login";
        }
    }
}
