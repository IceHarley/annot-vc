package com.bikesandwheels.persistance.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AVC_AUTHOR")
public class Author implements Serializable {
    @Id @GeneratedValue
    @Column(name = "AVC_AUT_ID")
    private int authorId;

    @Column(name = "AVC_AUT_NAME")
    private String name;

    public Author() {
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
