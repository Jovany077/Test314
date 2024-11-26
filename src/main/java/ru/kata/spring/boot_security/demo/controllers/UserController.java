package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String userPage(Model model) {
        return "user";
    }

//    @GetMapping
//    public String userPage (Model model, @AuthenticationPrincipal User user) {
//        model.addAttribute("user", user);
//        boolean isAdmin = user.getRoles().stream()
//                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
//        model.addAttribute("isAdmin", isAdmin);
//        return "user";
//    }
}
