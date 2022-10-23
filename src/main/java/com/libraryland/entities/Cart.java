package com.libraryland.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Audited
public class Cart extends Base {
    @Column(name = "quantity", nullable = false)
    private int quantity; //cantidad de libros en el carrito

    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "fk_user", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @Builder.Default
    private List<CartDetail> details = new ArrayList<>();

    //Esto es para establecer la bidireccionalidad. Con el metodo generico solo no funciona.
    public void addCartDetails(List<CartDetail> details) {
        for (CartDetail detail : details) {
            detail.setCart(this);
        }
    }
}
