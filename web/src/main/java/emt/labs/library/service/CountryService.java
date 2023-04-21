package emt.labs.library.service;

import emt.labs.library.models.Country;
import emt.labs.library.models.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto countryDto);
    Optional<Country> edit(Long id, CountryDto countryDto);
    void deleteById(Long id);
}
