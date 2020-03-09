package com.myshop.services.services.implementations;

import com.myshop.data.entities.GlobalOrder;
import com.myshop.data.entities.User;
import com.myshop.data.repositories.ArchivedOrderRepository;
import com.myshop.data.repositories.GlobalOrderRepository;
import com.myshop.data.repositories.OrderRepository;
import com.myshop.services.models.ArchivedOrderServiceModel;
import com.myshop.services.models.OrderServiceModel;
import com.myshop.services.models.ProductServiceModel;
import com.myshop.services.models.UserServiceModel;
import com.myshop.services.services.ArchivedOrderService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArchivedOrderServiceTest extends BaseTest {

    @MockBean
    ArchivedOrderRepository archivedOrderRepository;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    GlobalOrderRepository globalOrderRepository;

    @Autowired
    ArchivedOrderService archivedOrderService;

    @Test
    public void archive_shouldBeSuccessful() throws InterruptedException {
        GlobalOrder globalOrder = new GlobalOrder();
        globalOrder.setUser(new User(){{
            setUsername("pesho");
        }});
        List<OrderServiceModel> orderServiceModels = new ArrayList<>();
        OrderServiceModel orderServiceModel = new OrderServiceModel();
        orderServiceModel.setProduct(new ProductServiceModel(){{
            setName("product");
        }});
        orderServiceModel.setUser(new UserServiceModel());
        orderServiceModel.setQuantity(1);
        orderServiceModels.add(orderServiceModel);

        Mockito.when(this.globalOrderRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(globalOrder));

        this.archivedOrderService.archive(orderServiceModels, "id");
    }

    @Test
    public void setCompleted_shouldBeSuccessful() {
        List<ArchivedOrderServiceModel> orders = new ArrayList<>();
        ArchivedOrderServiceModel order = new ArchivedOrderServiceModel();
        order.setCompleted(false);
        orders.add(order);

        List<ArchivedOrderServiceModel> receivedOrders = this.archivedOrderService.setCompleted(orders);

        Assert.assertTrue(receivedOrders.get(0).isCompleted());
    }
}
