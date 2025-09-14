package com.spring.project.controller;

import com.spring.project.entity.UserDetail;
import com.spring.project.entity.cart;
import com.spring.project.repository.UserRepository;
import com.spring.project.service.CartService;
import com.spring.project.service.CategoryService;
import com.spring.project.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    public CartService cartService;


    @Autowired
    private CategoryService categoryService;

	@Autowired
    private ProductService productService;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @ModelAttribute
    private void userDetails(Model m, Principal p){
        String email = p.getName();
        UserDetail userDetail = userRepository.findByEmail(email);
        m.addAttribute("user",userDetail);
    }

    @GetMapping("/home")
    public String home(){
        return "user/home";
    }

    @GetMapping("/contactUs")
    public String contactUs(){
        return "contactUs";
    }

    @GetMapping("/shop")
	public String getShop(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("products", productService.findAll());
       // model.addAttribute("user",userRepository.findByEmail());
		return "user/shop";
	}

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }


    @PostMapping("/addItem")
    public String addProductToCart(@RequestParam String username,
                                   @RequestParam Long productId) {


        UserDetail userDetail=userRepository.findByEmail(username);
        int id=userDetail.getId();
        String username1=userDetail.getFullName();
        System.out.println(id+" "+username1);
        if( cartService.check(id,productId)){
            return "redirect:/user/shop";
        }
        else {
            cartService.addItemToCart(username, productId);
            System.out.println("added");
            return "redirect:/user/viewCart";
        }

    }

    @GetMapping("/viewCart")
    public String viewCart(Model model,Principal p) {
        String email=p.getName();
        System.out.println(email);
        UserDetail userDetail=userRepository.findByUserEmail(email);
        System.out.println(userDetail);
        List<cart> getItems=cartService.findProductsByUserId(userDetail.getId());
        //System.out.println(getItems);

        for (cart item : getItems) {
            System.out.println("Product Name: " + item.getProduct().getName());
            System.out.println("Price: " + item.getProduct().getPrice());
            System.out.println("User Email: " + (item.getUser() != null ? item.getUser().getEmail() : "No user"));
        }

        model.addAttribute("cartItems", getItems);
        model.addAttribute("user", userDetail);
        return "user/cart";
    }



    @PostMapping("/remove/{itemId}")
    public String deleteCartItem(@PathVariable("itemId") Long itemId) {
        System.out.println("details are:");
        System.out.println("itemId");
        cartService.deleteItem(itemId);
        return "redirect:/user/viewCart";
    }
    @GetMapping("/buy")
    public String buyPage() {
        return "user/buy";
    }

    // Route to handle the purchase



    // Route for the buy success page
    @GetMapping("/buySuccess")
    public String buySuccess(@RequestParam String address, Model model) {
        model.addAttribute("address", address);
        return "user/buySuccess";
    }


}
