package com.myshop.services.services.implementations;

import com.myshop.data.entities.ArchivedOrder;
import com.myshop.data.entities.GlobalOrder;
import com.myshop.data.repositories.ArchivedOrderRepository;
import com.myshop.data.repositories.GlobalOrderRepository;
import com.myshop.data.repositories.OrderRepository;
import com.myshop.services.models.OrderServiceModel;
import com.myshop.services.services.ArchivedOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArchivedOrderServiceImpl implements ArchivedOrderService {

    private final OrderRepository orderRepository;
    private final ArchivedOrderRepository archivedOrderRepository;
    private final GlobalOrderRepository globalOrderRepository;

    @Autowired
    public ArchivedOrderServiceImpl(OrderRepository orderRepository, ArchivedOrderRepository archivedOrderRepository, GlobalOrderRepository globalOrderRepository) {
        this.orderRepository = orderRepository;
        this.archivedOrderRepository = archivedOrderRepository;
        this.globalOrderRepository = globalOrderRepository;
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
}
