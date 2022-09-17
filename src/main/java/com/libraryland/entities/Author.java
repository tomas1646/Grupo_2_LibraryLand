package com.libraryland.entities;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "author")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Audited
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
}
