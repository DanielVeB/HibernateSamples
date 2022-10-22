package com.kurosz.hibernatesamples.n1problem.fix.entity;

import javax.persistence.*;

@Entity
@Table(name = "books")
@NamedEntityGraph(name = "books-author-graph",
        attributeNodes = @NamedAttributeNode(value = "author"))
public class FixedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private FixedAuthor author;

    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FixedAuthor getAuthor() {
        return author;
    }

    public void setAuthor(FixedAuthor author) {
        this.author = author;
    }

}
