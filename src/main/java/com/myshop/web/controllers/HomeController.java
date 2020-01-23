package com.myshop.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public String getHome(){
        return "home" ;
    }

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public String getIndex(){
        return "index" ;
    }
}
