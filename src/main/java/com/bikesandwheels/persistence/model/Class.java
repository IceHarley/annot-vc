package com.bikesandwheels.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AVC_CLASS")
public class Class implements Serializable {
    @Id @GeneratedValue
    @Column(name = "AVC_CLA_ID")
    private Long classId;

    @Column(name = "AVC_CLA_CANONICAL_NAME")
    private String canonicalName;

    public Class() {
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }
}
