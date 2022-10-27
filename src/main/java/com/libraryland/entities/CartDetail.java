package com.libraryland.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
public class CartDetail extends Base {
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fk_cart", nullable = false)
    @JsonIncludeProperties(value = {"id"})
    private Cart cart;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fk_book", nullable = false)
    @JsonIncludeProperties(value = {"id", "title"})
    private Book book;
}
