package com.kurosz.hibernatesamples.n1problem.broken.repository;

import com.kurosz.hibernatesamples.n1problem.broken.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
