package com.myshop.services.services.implementations;

import com.myshop.data.entities.GlobalOrder;
import com.myshop.data.entities.User;
import com.myshop.data.repositories.GlobalOrderRepository;
import com.myshop.data.repositories.UserRepository;
import com.myshop.services.services.GlobalOrderService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class GlobalOrderServiceTest extends BaseTest {

    @MockBean
    GlobalOrderRepository globalOrderRepository;

    @MockBean
    UserRepository userRepository;

    @Autowired
    GlobalOrderService globalOrderService;

    @Test
    public void create_shouldBeSuccessful() {
        Mockito.when(this.userRepository.findByUsername(Mockito.any()))
                .thenReturn(new User(){{
                    setUsername("gosho");
                }});
        Mockito.when(this.globalOrderRepository.findTopByUserOrderByIdDesc(Mockito.any()))
                .thenReturn(new GlobalOrder(){{
                    setId("invalidId");
                }});

        String id = this.globalOrderService.create("gosho");

        Assert.assertEquals("invalidId", id);
    }
}
