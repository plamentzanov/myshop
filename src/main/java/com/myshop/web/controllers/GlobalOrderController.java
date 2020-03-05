package com.myshop.web.controllers;

import com.myshop.services.models.ArchivedOrderServiceModel;
import com.myshop.services.models.GlobalOrderServiceModel;
import com.myshop.services.services.ArchivedOrderService;
import com.myshop.services.services.GlobalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/global-orders")
public class GlobalOrderController extends BaseController{

    private final GlobalOrderService globalOrderService;
    private final ArchivedOrderService archivedOrderService;

    @Autowired
    public GlobalOrderController(GlobalOrderService globalOrderService, ArchivedOrderService archivedOrderService) {
        this.globalOrderService = globalOrderService;
        this.archivedOrderService = archivedOrderService;
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
        return super.redirect("/admins/orders-manager");
    }

    @GetMapping("/decline/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView declineOrder(@PathVariable String id){
        GlobalOrderServiceModel globalOrder = this.globalOrderService.getById(id);
        List<ArchivedOrderServiceModel> orders = globalOrder.getOrders();
        this.archivedOrderService.deleteAll(orders);
        this.globalOrderService.deleteById(id);
        return super.redirect("/admins/orders-manager");
    }
}
