package emt.labs.library.service;

import emt.labs.library.models.Author;
import emt.labs.library.models.Book;
import emt.labs.library.models.dto.BookDto;
import emt.labs.library.models.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();
    Optional<Book> findById(Long id);
    void deleteById(Long id);
    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id, BookDto bookDto);
    Optional<Book> borrow(Long id);

}
