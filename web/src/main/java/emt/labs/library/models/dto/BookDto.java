package emt.labs.library.models.dto;

import emt.labs.library.models.enumerations.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class BookDto {

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;
    private Long authorId;
    private Integer availableCopies;

    public BookDto(){

    }

    public BookDto(String name, Category category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}
