package com.libraryland.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
public class CartDetail extends Base{
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_cart")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_book")
    private Book book;
}
