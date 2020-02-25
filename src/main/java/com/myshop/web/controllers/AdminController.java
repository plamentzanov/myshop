package com.myshop.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins")
public class AdminController extends BaseController {

    @GetMapping("/panel")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getAdminPanel(){
        return "admins/panel";
    }
}
