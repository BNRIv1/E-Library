package emt.labs.library.service.impl;

import emt.labs.library.models.Author;
import emt.labs.library.models.Book;
import emt.labs.library.models.dto.BookDto;
import emt.labs.library.models.enumerations.Category;
import emt.labs.library.models.exceptions.AuthorNotFoundException;
import emt.labs.library.models.exceptions.BookNotFoundException;
import emt.labs.library.repository.AuthorRepository;
import emt.labs.library.repository.BookRepository;
import emt.labs.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElseThrow(
                AuthorNotFoundException::new
        );
        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(), bookDto.getAvailableCopies(),
                bookDto.getCategory(), author)));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(
                BookNotFoundException::new
        );
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElseThrow(
                AuthorNotFoundException::new
        );
        book.setAuthor(author);
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> borrow(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(
                BookNotFoundException::new
        );
        if (book.getAvailableCopies() == 0){
            throw new RuntimeException();
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);

        return Optional.of(this.bookRepository.save(book));
    }
}
