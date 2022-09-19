package com.libraryland.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;   
    
    @ManyToOne()
    @JoinColumn(name = "fk_order")
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "fk_book")
    private Book book;
}
