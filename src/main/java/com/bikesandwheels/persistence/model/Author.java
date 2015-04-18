package com.bikesandwheels.persistence.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "AVC_AUTHOR")
public class Author implements Serializable {
    @GeneratedValue
    @Id @Column(name = "AVC_AUT_ID")
    private Long authorId;

    @Column(name = "AVC_AUT_NAME", unique = true)
    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<Revision> revisions = Lists.newArrayList();

    public Author() {
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<Revision> revisions) {
        this.revisions = revisions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return !(authorId != null ? !authorId.equals(author.authorId) : author.authorId != null);

    }

    @Override
    public int hashCode() {
        return authorId != null ? authorId.hashCode() : 0;
    }
}
