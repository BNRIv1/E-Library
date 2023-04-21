package emt.labs.library.service;


import emt.labs.library.models.Author;
import emt.labs.library.models.Country;
import emt.labs.library.models.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDto authorDto);
    Optional<Author> edit(Long id, AuthorDto authorDto);
    void deleteById(Long id);

}
