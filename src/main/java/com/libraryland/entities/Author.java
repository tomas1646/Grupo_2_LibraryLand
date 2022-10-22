package com.libraryland.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
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
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "biography", length = 2000, nullable = false)
    private String biography;

    @ManyToMany(mappedBy = "authors")
    @Builder.Default
    private List<Book> books = new ArrayList<>();
}
