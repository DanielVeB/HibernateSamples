package com.kurosz.hibernatesamples.n1problem.fix.repository

import io.github.joke.spockoutputcapture.CapturedOutput
import io.github.joke.spockoutputcapture.OutputCapture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification

@DataJpaTest
@Sql(["/database/n1problem/schema.sql", "/database/n1problem/data.sql"])
class FixedBookRepositoryTest extends Specification {

    @Autowired
    private FixedBookRepository bookRepository

    @OutputCapture
    CapturedOutput logs

    def "Should not generate n+1 problem while searching all books"() {
        when: "Searching all books in repository"
        def books = bookRepository.findAll()
        then: "Hibernate should execute only one select statement"
        logs.lines.count { it.contains("select") } == 1
    }
}
