package com.kurosz.hibernatesamples.n1problem.fix.repository;

import com.kurosz.hibernatesamples.n1problem.fix.entity.FixedBook;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FixedBookRepository extends JpaRepository<FixedBook, Integer> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            value = "books-author-graph")
    List<FixedBook> findAll();

}
