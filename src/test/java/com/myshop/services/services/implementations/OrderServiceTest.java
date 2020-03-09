package com.myshop.services.services.implementations;

import com.myshop.data.entities.Category;
import com.myshop.data.entities.Order;
import com.myshop.data.entities.Product;
import com.myshop.data.entities.User;
import com.myshop.data.repositories.OrderRepository;
import com.myshop.data.repositories.ProductRepository;
import com.myshop.data.repositories.UserRepository;
import com.myshop.services.models.OrderServiceModel;
import com.myshop.services.models.ProductServiceModel;
import com.myshop.services.models.UserServiceModel;
import com.myshop.services.services.OrderService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceTest extends BaseTest {

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;

    @Test
    public void add_shouldBeSuccessful() {
        User user = new User();
        user.setUsername("Gosho");
        user.setPassword("pesho");
        user.setAddress("Sofia");
        user.setEmail("pesho@goshov.com");

        Mockito.when(this.userRepository.findByUsername(Mockito.any()))
                .thenReturn(user);

        Product product = new Product();
        product.setId("invalid");
        product.setCategory(new Category());
        product.setImageUrl("www.com.com");
        product.setName("20");
        product.setPrice(BigDecimal.ONE);

        Mockito.when(this.productRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(product));

        OrderServiceModel order = new OrderServiceModel();
        order.setQuantity(1);
        order.setUser(new UserServiceModel());
        order.setProduct(new ProductServiceModel(){{
            setId("invalid id");
        }});

        this.orderService.add(order, null);
    }

    @Test
    public void add_shouldReturnError() {
        Assertions.assertThrows(Exception.class, () -> this.orderService.add(null, null));
    }

    @Test
    public void  getTotalSum_shouldReturnTotalSumCorrect() {
        Product product = new Product();
        product.setPrice(BigDecimal.TEN);
        Order order = new Order();
        order.setQuantity(1);
        order.setProduct(product);
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        Mockito.when(this.orderRepository.findAllByUserUsername(Mockito.any()))
                .thenReturn(orders);
        Double totalSum = this.orderService.getTotalSum(null);

        Assert.assertEquals(10.0, totalSum, 1);
    }

    @Test
    public void getCart_shouldReturnCartCorrect() {
        List<Order> cart = new ArrayList<>();
        Order order = new Order();
        order.setProduct(new Product());
        order.setUser(new User());
        order.setQuantity(1);
        cart.add(order);

        Mockito.when(this.orderRepository.findAllByUserUsername(Mockito.any()))
                .thenReturn(cart);

        List<OrderServiceModel> returnedCart = this.orderService.getCart("pesho");
        OrderServiceModel received = returnedCart.get(0);
        int quantity = received.getQuantity();

        Assert.assertEquals(1, quantity);
    }

}
