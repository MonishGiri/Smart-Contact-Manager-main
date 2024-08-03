package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/user")
public class UserController {

    // user dashboard page

    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // user profile page

    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }
    

    // user add contact page

    // user view contact page

    // user delete contact page

    // user search contact
}
