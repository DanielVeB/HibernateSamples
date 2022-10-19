package com.kurosz.hibernatesamples.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification

@DataJpaTest
@Sql(["/database/schema.sql", "/database/countries.sql"])
class CountryRepositoryTest extends Specification {

    @Autowired
    private CountryRepository repository

    def "Should return list of 6 countries"(){
        when: "Get all countries from repository"
        def countries = repository.findAll()

        then: "result should have 6 elements"
        countries.size() == 6
    }
}
