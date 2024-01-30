package com.vti.movie.modal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bid")
@Data
@NoArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bid_price")
    private int bidPrice;

    @Column(name = "bid_time")
    private Date bidTime;

    @ManyToOne
    @JoinColumn(name = "buyer_id"
//            referencedColumnName = "Id")
    )
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "product_id"
//            referencedColumnName = "Id")
    )
    private Product product;

    @PrePersist
    public void prePersist() {
        if (bidTime == null) {
            bidTime = new Date();
        }
    }

}