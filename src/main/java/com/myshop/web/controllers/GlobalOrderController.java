package com.myshop.web.controllers;

import com.myshop.services.models.GlobalOrderServiceModel;
import com.myshop.services.services.ArchivedOrderService;
import com.myshop.services.services.GlobalOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/global-orders")
public class GlobalOrderController extends BaseController{

    private final GlobalOrderService globalOrderService;
    private final ArchivedOrderService archivedOrderService;
    private final ModelMapper modelMapper;

    @Autowired
    public GlobalOrderController(GlobalOrderService globalOrderService, ArchivedOrderService archivedOrderService, ModelMapper modelMapper) {
        this.globalOrderService = globalOrderService;
        this.archivedOrderService = archivedOrderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/orders-manager")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getOrderManager(){
        return "orders/orders-manager";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String viewOrder(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "orders/view-order";
    }

    @GetMapping("/accept/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView acceptOrder(@PathVariable String id){
        GlobalOrderServiceModel globalOrder = this.globalOrderService.getById(id);
        globalOrder.setOrders(this.archivedOrderService.setCompleted(globalOrder.getOrders()));
        globalOrder.setCompleted(true);
        this.globalOrderService.update(globalOrder);
        return super.redirect("/global-orders/orders-manager");
    }

    @GetMapping("/decline/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView declineOrder(@PathVariable String id){
        return super.redirect("/global-orders/orders-manager");
    }
}
