package com.vti.movie.modal.dtos;

import com.vti.movie.modal.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int id;

    private String productName;

    private Status status;

    private String description;

    private int startPrice;

    private Date startTime;

    private Date endTime;

    private int creatorId;
}
