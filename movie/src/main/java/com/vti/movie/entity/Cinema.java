package com.vti.movie.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="cinema")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 2000)
    private String imgURL;
    private String name;
    private String diaChi;
//    private String phoneNo;
}
