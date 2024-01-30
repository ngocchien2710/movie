package com.vti.movie.modal.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBidRequest {
    private int id;
    private int bidPrice;
    private Date bidTime;
    private int buyerId;
    private int productId;
}
