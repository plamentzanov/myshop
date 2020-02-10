package com.myshop.web.controllers;

import com.myshop.services.models.OrderServiceModel;
import com.myshop.services.services.OrderService;
import com.myshop.services.services.ProductService;
import com.myshop.web.models.OrderCreateModel;
import com.myshop.web.models.OrderViewModel;
import com.myshop.web.models.ProductViewModel;
import com.myshop.web.models.UserCheckoutModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/orders")
public class OrderController extends BaseController{

    private final OrderService ordersService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService ordersService, ProductService productService, ModelMapper modelMapper) {
        this.ordersService = ordersService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add-to-cart/{productId}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addToCard(@PathVariable String productId, @ModelAttribute("model") OrderCreateModel model) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        model.setProduct(this.modelMapper.map(this.productService.getById(productId), ProductViewModel.class));
        this.ordersService.add(this.modelMapper.map(model, OrderServiceModel.class), loggedInUser.getName());
        return super.redirect("/products/select-category");
    }

    @GetMapping("/cart")
    @PreAuthorize("isAuthenticated()")
    public String getCart(Model model){
       Authentication loggedInUser =  SecurityContextHolder.getContext().getAuthentication();
        List<OrderViewModel> cart = this.ordersService.getCart(loggedInUser.getName())
                .stream()
                .map(o -> this.modelMapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());

        double totalSum = this.ordersService.getTotalSum(loggedInUser.getName());

        model.addAttribute("cart", cart);
        model.addAttribute("totalSum", totalSum);

        return "orders/cart";
    }

    @GetMapping("/remove-from-cart/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView removeFromCart(@PathVariable String id){
        this.ordersService.deleteById(id);
        return super.redirect("/orders/cart");
    }

    @GetMapping("/checkout")
    @PreAuthorize("isAuthenticated()")
    public String getCheckout(Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        UserCheckoutModel user = this.modelMapper.map(loggedInUser.getPrincipal(), UserCheckoutModel.class);
        double totalSum = this.ordersService.getTotalSum(loggedInUser.getName());

        model.addAttribute("user", user);
        model.addAttribute("totalSum", totalSum);

        return "orders/checkout";
    }

    @PostMapping("/checkout")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView checkout(){

        return super.redirect("/home");
    }
}
