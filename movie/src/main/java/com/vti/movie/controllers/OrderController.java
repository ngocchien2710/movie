package com.vti.movie.controllers;

import com.vti.movie.modal.dtos.OrderDto;
import com.vti.movie.modal.request.CreateOrderRequest;
import com.vti.movie.modal.request.UpdateOrderRequest;
import com.vti.movie.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private IOrderService service;
    @GetMapping("/buyer/{id}")
    public List<OrderDto> getBidByBuyerId(@PathVariable int id){

        return service.getBidByBuyerId(id);
    }

    @PostMapping
    public void createOrder(@RequestBody CreateOrderRequest form){
        service.createOrder(form);
    }

    @PutMapping("{id}")
    public void updateOrder(@PathVariable int id, @RequestBody UpdateOrderRequest form) {
        form.setId(id);
        service.updateOrder(form);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id){
        service.deleteOrder(id);
    }

}
