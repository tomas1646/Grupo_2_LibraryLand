package com.libraryland.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Audited
public class Address extends Base {
    @NotEmpty(message = "Ciudad no puede estar vacio")
    @Column(name = "city", nullable = false)
    private String city;

    @NotEmpty(message = "Calle no puede estar vacio")
    @Column(name = "street", nullable = false)
    private String street;

    @NotNull(message = "Numero no puede estar vacio")
    @Min(value = 1, message = "Numero debe ser mayor a 0")
    @Column(name = "number", nullable = false)
    private int number;
}
