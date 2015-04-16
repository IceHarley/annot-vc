package com.bikesandwheels.persistence.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "AVC_CLASS")
public class Class implements Serializable {
    @Id @GeneratedValue
    @Column(name = "AVC_CLA_ID")
    private Long classId;

    @Column(name = "AVC_CLA_CANONICAL_NAME")
    private String canonicalName;

    @OneToMany(mappedBy = "revisedClass")
    private List<Revision> revisions = Lists.newArrayList();

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

    public List<Revision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<Revision> revisions) {
        this.revisions = revisions;
    }
}
