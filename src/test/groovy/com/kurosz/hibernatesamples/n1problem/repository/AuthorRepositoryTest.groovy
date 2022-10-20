package com.kurosz.hibernatesamples.n1problem.repository

import io.github.joke.spockoutputcapture.CapturedOutput
import io.github.joke.spockoutputcapture.OutputCapture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification
import spock.lang.Stepwise

@DataJpaTest
@Sql(["/database/n1problem/schema.sql", "/database/n1problem/data.sql"])
@Stepwise
class AuthorRepositoryTest extends Specification {

    @Autowired
    private AuthorRepository repository

    @OutputCapture CapturedOutput logs

    def "Should generate n+1 problem"(){
        when: "Searching all authors in repository"
        def authors = repository.findAll()

        and: "Searching size of books of each author"
        authors.forEach{
            print(it.books.size())
        }

        then: "Hibernate should execute select statement per each author"
        logs.lines.count {it.contains("select")} == 6
    }
}
