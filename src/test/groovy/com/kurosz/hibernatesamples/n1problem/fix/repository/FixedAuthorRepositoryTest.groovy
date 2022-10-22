package com.kurosz.hibernatesamples.n1problem.fix.repository

import io.github.joke.spockoutputcapture.CapturedOutput
import io.github.joke.spockoutputcapture.OutputCapture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification

@DataJpaTest
@Sql(["/database/n1problem/schema.sql", "/database/n1problem/data.sql"])
class FixedAuthorRepositoryTest extends Specification {

    @Autowired
    private FixedAuthorRepository repository

    @OutputCapture
    CapturedOutput logs

    def "Should not generate n+1 problem"() {
        when: "Searching all authors in repository"
        def authors = repository.findAll()

        and: "Get how many books each author has written"
        authors.forEach {
            print(it.books.size())
        }

        then:
        """ Hibernate should execute one select with left outer join

        select fixedautho0_.id as id1_0_0_, books1_.id as id1_2_1_, 
        fixedautho0_.fullname as fullname2_0_0_, 
        books1_.author_id as author_i3_2_1_, books1_.title as title2_2_1_, books1_.author_id as author_i3_2_0__, books1_.id as id1_2_0__ 
        from authors fixedautho0_ 
        left outer join books books1_ 
        on fixedautho0_.id=books1_.author_id

        """
        logs.lines.count { it.contains("select") } == 1
        logs.lines.count { it.contains("left outer join") } == 1

    }

    def "Should not generate n+1 problem (lazy loading enabled)"() {
        when: "Searching all authors in repository"
        def authors = repository.findAll()

        and: "Get how many books each author has written"
        authors.forEach {
            print(it.lazyLoadedBooks.size())
        }

        then: "Hibernate should execute one select to get authors and then another select to load books"
        logs.lines.count { it.contains("select") } == 2

    }
}