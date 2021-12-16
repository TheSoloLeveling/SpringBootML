package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/main")
    public String getMain() {
        return "main";
    }
}
