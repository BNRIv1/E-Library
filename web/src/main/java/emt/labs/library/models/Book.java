package emt.labs.library.models;

import emt.labs.library.models.enumerations.Category;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer availableCopies;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    public Book(String name, Integer availableCopies, Category category, Author author) {
        this.name = name;
        this.availableCopies = availableCopies;
        this.category = category;
        this.author = author;
    }

    public Book(){

    }
}
