package com.vti.movie.service;

import com.vti.movie.modal.dtos.OrderDto;
import com.vti.movie.modal.request.CreateOrderRequest;
import com.vti.movie.modal.request.UpdateOrderRequest;

import java.util.List;

public interface IOrderService {
    List<OrderDto> getBidByBuyerId(int id);

    void createOrder(CreateOrderRequest form);

    void updateOrder(UpdateOrderRequest form);

    void deleteOrder(int id);
}
