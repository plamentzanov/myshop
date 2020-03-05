package com.myshop.web.controllers;

import com.myshop.services.models.UserServiceModel;
import com.myshop.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admins")
public class AdminController extends BaseController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/panel")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getAdminPanel(){
        return "admins/panel";
    }

    @GetMapping("/users-manager")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getUsersManager(){
        return "admins/users-manager";
    }

    @GetMapping("/manage/{id}")
    public ModelAndView manageUser(@PathVariable String id, Model model) {
        UserServiceModel user = this.userService.getUserById(id);
        if (user.getAuthorities().size() != 1) {
            model.addAttribute("error", 1);
            return super.view("admins/users-manager");
        }
        this.userService.makeModerator(user);
        return super.redirect("/admins/panel");
    }

    @GetMapping("/orders-manager")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getOrderManager(){
        return "admins/orders-manager";
    }
}
