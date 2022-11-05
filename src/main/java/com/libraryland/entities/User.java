package com.libraryland.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.envers.Audited;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
public class User extends Base {
    @NotEmpty(message = "Primer Nombre no puede estar vacío")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "Apellido no puede estar vacío")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address", nullable = false)
    private Address address;

    @NotEmpty(message = "Usuario no puede estar vacío")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotEmpty(message = "Email no puede estar vacío")
    @Email(message = "Email no es valido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Contraseña no puede estar vacío")
    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "roles")
    private String roles;

    @OneToOne(cascade = {CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "user")
    @JoinColumn(name = "fk_cart", unique = true)
    @JsonIncludeProperties(value = {"id"})
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = false)
    //no quiero que la orden pendiente se me elimine no?
    @Builder.Default
    @JsonIncludeProperties(value = {"id"})
    private List<Order> orders = new ArrayList<Order>();
}
