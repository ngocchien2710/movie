package com.vti.movie.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name ="create_date")
    @CreatedDate
    private LocalDateTime createdTime;

    @Column(name="price")
    private int price;

    @ManyToOne
    @JoinColumn(nullable = false,name="user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;



}
