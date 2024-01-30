package com.vti.movie.service.implement;

import com.vti.movie.modal.dtos.OrderDto;
import com.vti.movie.modal.entity.Bid;
import com.vti.movie.modal.entity.Order;
import com.vti.movie.modal.request.CreateBidRequest;
import com.vti.movie.modal.request.CreateOrderRequest;
import com.vti.movie.modal.request.UpdateOrderRequest;
import com.vti.movie.repository.OrderRepository;
import com.vti.movie.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    @Autowired
    private OrderService(OrderRepository orderRepository){
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<OrderDto> getBidByBuyerId(int id) {
        List<Order> orders =
//                orderRepository.findAll();
                orderRepository.findByBuyerId(id);
        List<OrderDto> orderDtos = modelMapper.map(orders, new TypeToken<List<OrderDto>>() {
        }.getType());
        return orderDtos;
    }

    @Override
    public void createOrder(CreateOrderRequest form) {
        TypeMap<CreateOrderRequest,Order> typeMap = modelMapper.getTypeMap(CreateOrderRequest.class, Order.class);
        if(typeMap == null){
            modelMapper.addMappings(new PropertyMap<CreateBidRequest, Bid>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });

            Order order = modelMapper.map(form, Order.class);
            orderRepository.save(order);
        }
    }

    @Override
    public void updateOrder(UpdateOrderRequest form) {
        Order order = modelMapper.map(form, Order.class);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
