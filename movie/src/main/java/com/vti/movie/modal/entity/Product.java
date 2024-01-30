package com.vti.movie.modal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "productname",length = 255)
    private String productName;
    //
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "description",length = 255)
    private String description;

    @Column(name = "start_price ")
    private int startPrice;

    @Column(name = "`start_time`")
    private Date startTime;

    @Column(name = "`end_time`")
    private Date endTime;

    @ManyToOne()
    @JoinColumn(name = "creator_id")
    private User creatorId;


}
