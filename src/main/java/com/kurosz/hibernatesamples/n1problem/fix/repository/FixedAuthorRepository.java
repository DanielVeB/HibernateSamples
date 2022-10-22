package com.kurosz.hibernatesamples.n1problem.fix.repository;

import com.kurosz.hibernatesamples.n1problem.fix.entity.FixedAuthor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FixedAuthorRepository extends JpaRepository<FixedAuthor, Integer> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
    value = "author-books-graph")
    List<FixedAuthor> findAll();
}
