package com.bikesandwheels.persistence.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "AVC_REVISION")
public class Revision implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "AVC_REV_ID")
    private Long Id;

    @ManyToOne
    @JoinColumn(name="AVC_REV_CLASSID")
    private Class revisedClass;

    @ManyToOne
    @JoinColumn(name="AVC_REV_METHODID")
    private Method revisedMethod;

    @ManyToMany
    @JoinTable(name="AVC_REVISION_AUTHORS")
    private List<Author> authors = Lists.newArrayList();

    @Temporal(TemporalType.DATE)
    @Column(name = "AVC_REV_DATE", nullable = false)
    private Date date;

    @Column(name = "AVC_REV_COMMENT")
    private String comment;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Class getRevisedClass() {
        return revisedClass;
    }

    public void setRevisedClass(Class revisedClass) {
        this.revisedClass = revisedClass;
    }

    public Method getRevisedMethod() {
        return revisedMethod;
    }

    public void setRevisedMethod(Method revisedMethod) {
        this.revisedMethod = revisedMethod;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Revision revision = (Revision) o;

        return !(Id != null ? !Id.equals(revision.Id) : revision.Id != null);

    }

    @Override
    public int hashCode() {
        return Id != null ? Id.hashCode() : 0;
    }
}
