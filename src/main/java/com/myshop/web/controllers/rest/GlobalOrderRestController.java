package com.myshop.web.controllers.rest;

import com.myshop.services.services.GlobalOrderService;
import com.myshop.web.models.ArchivedOrderViewModel;
import com.myshop.web.models.GlobalOrderViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/global-orders")
public class GlobalOrderRestController {

    private final GlobalOrderService globalOrderService;
    private final ModelMapper modelMapper;

    @Autowired
    public GlobalOrderRestController(GlobalOrderService globalOrderService, ModelMapper modelMapper) {
        this.globalOrderService = globalOrderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public List<GlobalOrderViewModel> getAllGlobalOrders(){
        return this.globalOrderService.getAll()
                .stream()
                .filter(g -> !g.isCompleted())
                .map(g -> this.modelMapper.map(g, GlobalOrderViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public List<ArchivedOrderViewModel> getGlobalOrderOrders(@PathVariable String id){
        return this.globalOrderService.getById(id)
                .getOrders()
                .stream()
                .map(ao -> this.modelMapper.map(ao, ArchivedOrderViewModel.class))
                .collect(Collectors.toList());
    }
}
