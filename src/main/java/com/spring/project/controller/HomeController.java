package com.spring.project.controller;

import com.spring.project.entity.UserDetail;
import com.spring.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/signin")
    public String login(){
        return "login";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(){
        return "aboutUs";
    }

    @GetMapping("/contactUs")
    public String contactUs(){
        return "contactUs";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserDetail userDetail){

        boolean eu = userService.checkEmail(userDetail.getEmail());
        if(eu){
            System.out.println("Email id already exist");
        }
        else{
            UserDetail userDetail1 = userService.createUser(userDetail);
            if(userDetail1 !=null){
                System.out.println("Registered Sucessfully");
            }
            else{
                System.out.println("Something error in server");
            }
        }
        return "redirect:/signin";
    }
}
