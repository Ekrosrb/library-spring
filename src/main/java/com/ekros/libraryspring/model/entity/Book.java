package com.ekros.libraryspring.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "books", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "author"})})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 140)
    private String name;
    @Column(nullable = false, length = 140)
    private String author;
    @Column(nullable = false, length = 140)
    private String edition;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false, columnDefinition = "TEXT", name = "description_ru")
    private String descriptionRu;
    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private int count;

    @OneToMany(mappedBy = "book")
    private Set<Order> orders;

}