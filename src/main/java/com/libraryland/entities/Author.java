package com.libraryland.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@JsonIgnoreProperties(value = {"books"})
public class Author extends Base {
    @NotEmpty(message = "El nombre del autor no puede estar vacío")
    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    @NotEmpty(message = "La biografía del autor no puede estar vacía")
    @Size(max = 2000, message = "La biografía del autor no puede tener más de 2000 caracteres")
    @Column(name = "biography", length = 2000, nullable = false)
    private String biography;

    @ManyToMany(mappedBy = "authors")
    @Builder.Default
    private List<Book> books = new ArrayList<>();
}
