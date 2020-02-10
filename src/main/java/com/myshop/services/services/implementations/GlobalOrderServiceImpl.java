package com.myshop.services.services.implementations;

import com.myshop.data.entities.GlobalOrder;
import com.myshop.data.repositories.GlobalOrderRepository;
import com.myshop.data.repositories.OrderRepository;
import com.myshop.services.models.GlobalOrderServiceModel;
import com.myshop.services.models.OrderServiceModel;
import com.myshop.services.services.GlobalOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalOrderServiceImpl implements GlobalOrderService {

    private final GlobalOrderRepository globalOrderRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GlobalOrderServiceImpl(GlobalOrderRepository globalOrderRepository, OrderRepository orderRepository, ModelMapper modelMapper) {
        this.globalOrderRepository = globalOrderRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addGlobalOrder(GlobalOrderServiceModel globalOrder) {

       GlobalOrder globalOrder1 = this.modelMapper.map(globalOrder, GlobalOrder.class);
        for (OrderServiceModel order : globalOrder.getOrders()) {
            this.orderRepository.deleteById(order.getId());
        }

        this.globalOrderRepository.saveAndFlush(globalOrder1);
    }
}
