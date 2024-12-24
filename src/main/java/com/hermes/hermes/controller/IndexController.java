package com.hermes.hermes.controller;

import org.springframework.ui.Model;
import com.hermes.hermes.dto.User;
import com.hermes.hermes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(Model model) {
        List<Map<String,Object>> users = userService.getAllUsers();

        return "index";
    }

    @GetMapping("/Signup")
    public String signup() {
        return "signup";
    }
    @PostMapping("/Signup-success")
    public String signupSuccess(@ModelAttribute User user, Model model) {
        userService.insertUser(user);
        model.addAttribute("msg","회원가입이 성공적으로 완료되었습니다.");
        return "success";
    }
}
