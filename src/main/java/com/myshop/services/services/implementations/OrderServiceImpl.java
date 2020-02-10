package com.myshop.services.services.implementations;

import com.myshop.data.entities.Order;
import com.myshop.data.repositories.OrderRepository;
import com.myshop.data.repositories.ProductRepository;
import com.myshop.data.repositories.UserRepository;
import com.myshop.services.models.OrderServiceModel;
import com.myshop.services.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(OrderServiceModel order, String username) {
        Order newOrder = this.modelMapper.map(order, Order.class);
        newOrder.setUser(this.userRepository.findByUsername(username));
        newOrder.setProduct(this.productRepository.findById(newOrder.getProduct().getId()).get());
        this.orderRepository.saveAndFlush(newOrder);
    }

    @Override
    public void deleteById(String id) {
        this.orderRepository.deleteById(id);
    }

    @Override
    public Double getTotalSum(String name) {
        List<Order> cart = this.orderRepository.findAllByUserUsername(name);
        double totalSum = 0;
        for (Order order : cart) {
            totalSum +=  order.getQuantity() *  Double.parseDouble((order.getProduct().getPrice()).toString());
        }
        return totalSum;
    }

    @Override
    public List<OrderServiceModel> getCart(String username) {
        return this.orderRepository.findAllByUserUsername(username)
                .stream()
                .map(o -> this.modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());
    }
}
