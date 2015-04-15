package com.bikesandwheels.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AVC_AUTHOR")
public class Author implements Serializable {
    @Id @GeneratedValue
    @Column(name = "AVC_AUT_ID")
    private Long authorId;

    @Column(name = "AVC_AUT_NAME")
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (!authorId.equals(author.authorId)) return false;
        if (name != null ? !name.equals(author.name) : author.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorId.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
