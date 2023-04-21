package emt.labs.library.service.impl;

import emt.labs.library.models.Author;
import emt.labs.library.models.Country;
import emt.labs.library.models.dto.AuthorDto;
import emt.labs.library.models.exceptions.AuthorNotFoundException;
import emt.labs.library.models.exceptions.CountryNotFoundException;
import emt.labs.library.repository.AuthorRepository;
import emt.labs.library.repository.CountryRepository;
import emt.labs.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountryId()).orElseThrow(
                CountryNotFoundException::new
        );
        return Optional.of(this.authorRepository.save(new Author(authorDto.getName(), authorDto.getSurname(), country)));
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountryId()).orElseThrow(
                CountryNotFoundException::new
        );
        Author author = this.authorRepository.findById(id).orElseThrow(
                AuthorNotFoundException::new
        );

        author.setCountry(country);
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
