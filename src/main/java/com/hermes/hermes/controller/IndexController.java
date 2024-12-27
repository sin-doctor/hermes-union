package com.hermes.hermes.controller;


import com.hermes.hermes.dto.User;
import com.hermes.hermes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class IndexController {
    @Autowired
    private UserService userService;



    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/login_search")
    public String loginBySearch() {
        return "login_search";
    }

    @PostMapping("/signup-success")
    public String signup(@ModelAttribute User user) {
        if (user.getUser_id() == null || user.getUser_id().isEmpty()) {
            throw new IllegalArgumentException("User ID is required");
        }

        userService.insertUser(user);
        return "signup-Success";
    }
}