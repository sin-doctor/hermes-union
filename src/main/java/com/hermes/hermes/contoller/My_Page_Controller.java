package com.hermes.hermes.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class My_Page_Controller {

    @GetMapping("/My_Page")
    public String My_Page(@RequestParam("id") int id, Model model) {
        model.addAttribute("id", id);
        return "my_page";
    }
}
