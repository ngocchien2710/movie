package com.vti.movie.modal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;
    private int finalPrice;
    private int productId;
    private int buyerId;
}
