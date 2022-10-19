package com.kurosz.hibernatesamples;

import com.kurosz.hibernatesamples.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Sql({"/database/schema.sql", "/database/countries.sql"})
public class CountryRepositoryIT {

    @Autowired
    private CountryRepository repository;

    @Test
    void shouldReturnListOfCountries(){
        var result = repository.findAll();
        assertEquals(6, result.size());

    }
}
