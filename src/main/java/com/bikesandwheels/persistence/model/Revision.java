package com.bikesandwheels.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "AVC_REVISION")
public class Revision implements Serializable {
    @Id @GeneratedValue
    @Column(name = "AVC_REV_ID")
    private int Id;

    @Column(name = "AVC_REV_CLASSID")
    private Class revisedClass;

    @Column(name = "AVC_REV_METHODID")
    private Method revisedMethod;

    @Temporal(TemporalType.DATE)
    @Column(name = "AVC_REV_DATE")
    private Date date;

    @Column(name = "AVC_REV_COMMENT")
    private String comment;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
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
}
