package com.kurosz.hibernatesamples.n1problem.fix.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authors")
@NamedEntityGraph(name = "author-books-graph",
        attributeNodes = @NamedAttributeNode(value = "books"))
public class FixedAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fullname")
    private String fullName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Set<FixedBook> books;

    @OneToMany()
    @Fetch(FetchMode.SUBSELECT)
    private Set<FixedBook> lazyLoadedBooks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<FixedBook> getBooks() {
        return books;
    }

    public void setBooks(Set<FixedBook> books) {
        this.books = books;
    }

    public Set<FixedBook> getLazyLoadedBooks() {
        return lazyLoadedBooks;
    }

    public void setLazyLoadedBooks(Set<FixedBook> lazyLoadedBooks) {
        this.lazyLoadedBooks = lazyLoadedBooks;
    }
}
