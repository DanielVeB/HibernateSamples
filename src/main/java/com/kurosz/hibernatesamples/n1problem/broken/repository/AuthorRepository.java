package com.kurosz.hibernatesamples.n1problem.broken.repository;

import com.kurosz.hibernatesamples.n1problem.broken.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
