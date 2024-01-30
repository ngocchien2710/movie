package com.vti.movie.service.implement;


import com.vti.movie.modal.dtos.BidDto;
import com.vti.movie.modal.entity.Bid;
import com.vti.movie.modal.request.CreateBidRequest;
import com.vti.movie.modal.request.UpdateBidRequest;
import com.vti.movie.repository.BidRepository;
import com.vti.movie.service.IBidService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BidService implements IBidService {
    @Autowired
    private BidRepository bidRepository;

    private final ModelMapper modelMapper;

    @Autowired
    private BidService(BidRepository bidRepository){
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<BidDto> getBidByProductId(int id) {
        List<Bid> bids = bidRepository.findByProductId(id);
        List<BidDto> bidDtos = modelMapper.map(bids, new TypeToken<List<BidDto>>() {
        }.getType());
        return bidDtos;
    }

    @Override
    public void createBid(CreateBidRequest form) {
        TypeMap<CreateBidRequest,Bid> typeMap = modelMapper.getTypeMap(CreateBidRequest.class, Bid.class);
        if(typeMap == null){
            modelMapper.addMappings(new PropertyMap<CreateBidRequest, Bid>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });

            Bid bid = modelMapper.map(form, Bid.class);
            bidRepository.save(bid);
        }
    }

    @Override
    public void deleteBid(int id) {
        bidRepository.deleteById(id);
    }

    @Override
    public void updateBid(UpdateBidRequest form) {
        if (form.getBidTime() == null){
            form.setBidTime(new Date());
        }
        Bid bid = modelMapper.map(form, Bid.class);
        bidRepository.save(bid);
    }
}
