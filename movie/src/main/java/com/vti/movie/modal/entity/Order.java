package com.vti.movie.modal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`order`")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "final_price")
    private int finalPrice;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "buyer_id")
    private int buyerId;

}
