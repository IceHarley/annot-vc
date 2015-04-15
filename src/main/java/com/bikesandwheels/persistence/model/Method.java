package com.bikesandwheels.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AVC_METHOD")
public class Method implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "AVC_MET_ID")
    private Long methodId;

    @Column(name = "AVC_MET_NAME")
    private String name;

    @Column(name = "AVC_MET_SIGNATURE")
    private String signature;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="AVC_MET_CLASSID")
    private Class declaringClass;

    public Long getMethodId() {
        return methodId;
    }

    public void setMethodId(Long methodId) {
        this.methodId = methodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Class getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(Class declaringClass) {
        this.declaringClass = declaringClass;
    }
}
