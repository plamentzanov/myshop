package com.myshop.services.services.implementations;

import com.myshop.data.entities.ArchivedOrder;
import com.myshop.data.entities.GlobalOrder;
import com.myshop.data.repositories.ArchivedOrderRepository;
import com.myshop.data.repositories.GlobalOrderRepository;
import com.myshop.data.repositories.OrderRepository;
import com.myshop.services.models.ArchivedOrderServiceModel;
import com.myshop.services.models.OrderServiceModel;
import com.myshop.services.services.ArchivedOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArchivedOrderServiceImpl implements ArchivedOrderService {

    private final OrderRepository orderRepository;
    private final ArchivedOrderRepository archivedOrderRepository;
    private final GlobalOrderRepository globalOrderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArchivedOrderServiceImpl(OrderRepository orderRepository, ArchivedOrderRepository archivedOrderRepository, GlobalOrderRepository globalOrderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.archivedOrderRepository = archivedOrderRepository;
        this.globalOrderRepository = globalOrderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void archive(List<OrderServiceModel> orders, String globalOrderId) {
        GlobalOrder globalOrder = this.globalOrderRepository.findById(globalOrderId).get();

        this.orderRepository.deleteAllByUserUsername(globalOrder.getUser().getUsername());

        List<ArchivedOrder> archivedOrders = new ArrayList<>();
        ArchivedOrder archivedOrder;
        for (OrderServiceModel order : orders) {
            archivedOrder = new ArchivedOrder();
            archivedOrder.setGlobalOrder(globalOrder);
            archivedOrder.setProductName(order.getProduct().getName());
            archivedOrder.setQuantity(order.getQuantity());
            archivedOrder.setCompleted(false);
            archivedOrders.add(archivedOrder);
        }

        this.archivedOrderRepository.saveAll(archivedOrders);
    }

    @Override
    public List<ArchivedOrderServiceModel> setCompleted(List<ArchivedOrderServiceModel> orders) {
        List<ArchivedOrder> orderList = orders.stream()
                .map(o -> this.modelMapper.map(o, ArchivedOrder.class))
                .collect(Collectors.toList());
        orderList
                .forEach(o -> o.setCompleted(true));

        this.archivedOrderRepository.saveAll(orderList);

        return orderList.stream()
                .map(o -> this.modelMapper.map(o, ArchivedOrderServiceModel.class))
                .collect(Collectors.toList());
    }
}
