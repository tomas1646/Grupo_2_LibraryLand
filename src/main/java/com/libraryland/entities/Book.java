package com.libraryland.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@JsonIgnoreProperties(value = {"cartDetails", "orderDetails"})
public class Book extends Base {
    @NotEmpty(message = "El titulo no puede estar vacio")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message = "La sinopsis no puede estar vacia")
    @Size(max = 2000, message = "La sinopsis no puede tener mas de 2000 caracteres")
    @Column(name = "synopsis", length = 2000, nullable = false)
    private String synopsis;

    @NotNull(message = "El año de publicacion no puede estar vacio")
    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @NotNull(message = "El stock no puede estar vacio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name = "stock", nullable = false)
    private int stock;

    // TODO: Add image
    @Column(name = "image_src", length = 2048)
    private String imageSrc;

    @NotNull(message = "El precio no puede estar vacio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(name = "price", precision = 8, scale = 2, nullable = false)
    private float price;

    @NotNull(message = "El genero no puede estar vacio")
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "fk_book"),
            inverseJoinColumns = @JoinColumn(name = "fk_genre")
    )
    private List<Genre> genres = new ArrayList();

    @NotNull(message = "El autor no puede estar vacio")
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "fk_book"),
            inverseJoinColumns = @JoinColumn(name = "fk_author")
    )
    private List<Author> authors = new ArrayList();

    @OneToMany(mappedBy = "book", cascade = CascadeType.MERGE)
    //cascade type dudoso. Revisar. Que pas acon las ordenes cuando se borra el book?
    private List<CartDetail> cartDetails = new ArrayList<CartDetail>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.MERGE) //cascade type dudoso. Revisar
    private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

    public boolean hasGenre(String genreName) {
        return genres.stream().anyMatch(genre -> genre.getName().equals(genreName));
    }

    public boolean hasAuthor(String authorName) {
        return authors.stream().anyMatch(author -> author.getFullName().equals(authorName));
    }
}
