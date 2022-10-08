package com.libraryland.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
public class Order extends Base{
    @Column(name = "number")
    private int number;

    @Column(name = "total")
    private double total;

    @Column(name = "date")
    private Date date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_user")
    private User user;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @Builder.Default
    private List<OrderDetail> details = new ArrayList<>();
}
