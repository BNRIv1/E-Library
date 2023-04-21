package emt.labs.library.bootstrap;

import emt.labs.library.models.Author;
import emt.labs.library.models.Country;
import emt.labs.library.models.dto.AuthorDto;
import emt.labs.library.models.dto.BookDto;
import emt.labs.library.models.dto.CountryDto;
import emt.labs.library.models.enumerations.Category;
import emt.labs.library.service.AuthorService;
import emt.labs.library.service.BookService;
import emt.labs.library.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataHolder {

    private final CountryService countryService;
    private final BookService bookService;
    private final AuthorService authorService;

    public DataHolder(CountryService countryService, BookService bookService, AuthorService authorService) {
        this.countryService = countryService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostConstruct
    public void init(){

        Category[] categories = Category.values();

        for (int i = 1; i <= 3; i++){
            this.countryService.save(new CountryDto("Country" + i, "Continent" + i));
        }

        for (int i = 1; i <= 3; i++){
            this.authorService.save(new AuthorDto("Name" + i, "Surname" + i, Long.valueOf(i)));
        }

        for (int i = 1; i <= 3; i++){
            this.bookService.save(new BookDto("Book" + i, categories[i], Long.valueOf(i), i + 2));
        }

    }

}
