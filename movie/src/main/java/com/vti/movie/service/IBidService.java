package com.vti.movie.service;

import com.vti.movie.modal.dtos.BidDto;
import com.vti.movie.modal.request.CreateBidRequest;
import com.vti.movie.modal.request.UpdateBidRequest;

import java.util.List;

public interface IBidService {
    List<BidDto> getBidByProductId(int id);

    void createBid(CreateBidRequest form);

    void deleteBid(int id);

    void updateBid(UpdateBidRequest form);
}
