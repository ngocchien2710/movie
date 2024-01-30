package com.vti.movie.controllers;

import com.vti.movie.modal.dtos.BidDto;
import com.vti.movie.modal.request.CreateBidRequest;
import com.vti.movie.modal.request.UpdateBidRequest;
import com.vti.movie.service.IBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bids")
@CrossOrigin("*")
public class BidController {
    @Autowired
    private IBidService service;

    @GetMapping("/product/{id}")
    public List<BidDto> getBidByProductId(@PathVariable int id){

        return service.getBidByProductId(id);
    }

    @PostMapping
    public void createBid(@RequestBody CreateBidRequest form){
        service.createBid(form);
    }

    @PutMapping("{id}")
    public void updateBid(@PathVariable int id, @RequestBody UpdateBidRequest form) {
        form.setId(id);
        service.updateBid(form);
    }

    @DeleteMapping("/{id}")
    public void deleteBid(@PathVariable int id){
        service.deleteBid(id);
    }
}

