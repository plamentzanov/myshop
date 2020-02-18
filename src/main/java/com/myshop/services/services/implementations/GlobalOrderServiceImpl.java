package com.myshop.services.services.implementations;

import com.myshop.data.entities.GlobalOrder;
import com.myshop.data.repositories.GlobalOrderRepository;
import com.myshop.data.repositories.UserRepository;
import com.myshop.services.models.GlobalOrderServiceModel;
import com.myshop.services.services.GlobalOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GlobalOrderServiceImpl implements GlobalOrderService {

    private final GlobalOrderRepository globalOrderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GlobalOrderServiceImpl(GlobalOrderRepository globalOrderRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.globalOrderRepository = globalOrderRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String create(String username) {
        GlobalOrder globalOrder = new GlobalOrder();
        globalOrder.setOrderDate(new Date());
        globalOrder.setUser(this.userRepository.findByUsername(username));
        this.globalOrderRepository.saveAndFlush(this.modelMapper.map(globalOrder, GlobalOrder.class));

        return this.globalOrderRepository.findTopByUserOrderByIdDesc(this.userRepository.findByUsername(username)).getId();
    }

    @Override
    public List<GlobalOrderServiceModel> getAll() {
        return this.globalOrderRepository.findAll()
                .stream()
                .map(g -> this.modelMapper.map(g, GlobalOrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public GlobalOrderServiceModel getById(String id) {
        return this.modelMapper.map(this.globalOrderRepository.findGlobalOrderById(id), GlobalOrderServiceModel.class);
    }

    @Override
    public void update(GlobalOrderServiceModel globalOrder) {
        GlobalOrder updateOrder = this.modelMapper.map(globalOrder, GlobalOrder.class);
        updateOrder.setUser(this.userRepository.findByUsername(globalOrder.getUser().getUsername()));
        this.globalOrderRepository.saveAndFlush(updateOrder);
    }
}
