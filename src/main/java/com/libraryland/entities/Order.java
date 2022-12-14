package com.libraryland.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
// Note: the table name is "order" but it is a reserved word in SQL, so we need to use the @Table annotation to specify the table name
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Audited
public class Order extends Base {
    @Column(name = "number", nullable = false, unique = true)
    private int number;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @Builder.Default
    private List<OrderDetail> details = new ArrayList<>();

    //Esto es para establecer la bidireccionalidad. Con el metodo generico solo no funciona.
    public void addOrderDetails(List<OrderDetail> details) {
        for (OrderDetail detail : details) {
            detail.setOrder(this);
        }
    }
}
