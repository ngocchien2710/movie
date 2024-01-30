package com.vti.movie.modal.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequest {
    private int id;
    private int finalPrice;
    private int productId;
    private int buyerId;
}
