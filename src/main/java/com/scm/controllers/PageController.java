package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home Page Handler");
        model.addAttribute("name","Substring Technologies");
        model.addAttribute("MyName","Monish Giri");

        return "home";
    }

    // about route
    @GetMapping("/about")
    public String aboutPage(Model model) {
        System.out.println("About page loading");
        model.addAttribute("isLogin",false);
        return "about";
    }

    // servce route
    @GetMapping("/services")
    public String servicesPage() {
        System.out.println("About page loading");
        return "services";
    }

    // contact route
    @GetMapping("/contact")
    public String contactPage() {
        System.out.println("contact page loading");
        return "contact";
    }

    // Login route
    @GetMapping("/login")
    public String loginPage() {
        System.out.println("Login page loading");
        return "login";
    }

    // servce route
    @GetMapping("/register")
    public String registerPage(Model model) {
        UserForm userForm = new UserForm();
        // can also add default data
        // userForm.setName("Monish");
        // userForm.setAbout("Hello there how are you");
        
        model.addAttribute("userForm",userForm);
        return "register";
    }

    // processing register
    @PostMapping("/do-register")
    public String processRegister(@ModelAttribute UserForm userForm){
        System.out.println("Processing registration");
        // fetch the form data
        // UserForm
        System.out.println(userForm);
        // validate form data
        // todo

        // convert userform to user
        User user = User.builder()
        .name(userForm.getName())
        .email(userForm.getEmail())
        .password(userForm.getPassword())
        .about(userForm.getAbout())
        .phoneNumber(userForm.getPhoneNumber())
        .profilePic("https://static.vecteezy.com/system/resources/previews/005/544/718/non_2x/profile-icon-design-free-vector.jpg")
        .build();
        // save to database
        User savedUser = userService.savUser(user);
        System.out.println("User saved");
        
        // redirect
        return "redirect:/register";
    }
    
}
