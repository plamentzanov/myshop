package com.myshop.web.controllers;

import com.myshop.services.models.UserServiceModel;
import com.myshop.services.services.UserService;
import com.myshop.web.models.UserLoginModel;
import com.myshop.web.models.UserRegisterModel;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String getLogin() {
        return "users/login";
    }

    @GetMapping("/login?error")
    @PreAuthorize("isAnonymous()")
    public String getLoginError(Model model) {
        model.addAttribute("loginError", true);
        return "users/login";
    }


    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String getRegister(Model model) {
        model.addAttribute("userRegisterModel", new UserRegisterModel());
        return "users/register";
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(@Valid UserRegisterModel userRegisterModel,
                                 BindingResult bindingResult) {

        if (!userService.isUsernameFree(this.modelMapper.map(userRegisterModel, UserServiceModel.class))){
            bindingResult.addError(new FieldError("userRegisterModel","username", "Username is taken!"));
            return super.view("users/register");
        }

        if (!userService.isEmailFree(this.modelMapper.map(userRegisterModel, UserServiceModel.class))){
            bindingResult.addError(new FieldError("userRegisterModel","email", "Email is taken!"));
            return super.view("users/register");
        }

        if (!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
            bindingResult.addError(new FieldError("userRegisterModel","password", "Passwords don't match!"));
            return super.view("users/register");
        }

        if (bindingResult.hasErrors()) {
            return super.view("users/register");
        }

        this.userService.register(this.modelMapper.map(userRegisterModel, UserServiceModel.class));

        return super.redirect("users/login");
    }


}
