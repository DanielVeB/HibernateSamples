package com.kurosz.hibernatesamples.n1problem.broken.repository

import com.kurosz.hibernatesamples.n1problem.broken.repository.BookRepository
import io.github.joke.spockoutputcapture.CapturedOutput
import io.github.joke.spockoutputcapture.OutputCapture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification

@DataJpaTest
@Sql(["/database/n1problem/schema.sql", "/database/n1problem/data.sql"])
class BookRepositoryTest extends Specification {

    @Autowired
    private BookRepository bookRepository

    @OutputCapture
    CapturedOutput logs

    def "Should generate n+1 problem while searching all books"() {
        when: "Searching all books in repository"
        def books = bookRepository.findAll()
        books.first().author.getBooks()

        then: "Hibernate should execute select statement once to get books and twice to get authors data"
        logs.lines.count { it.contains("select book") } == 1
        logs.lines.count { it.contains("select author") } == 2

    }
}
