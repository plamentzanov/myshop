package com.myshop.web.controllers.rest;

import com.myshop.services.services.OrderService;
import com.myshop.web.models.OrderViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderRestController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/cart")
    @PreAuthorize("isAuthenticated()")
    public List<OrderViewModel> getCart(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return this.orderService.getCart(loggedInUser.getName())
                .stream()
                .map(o -> this.modelMapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());
    }
}
