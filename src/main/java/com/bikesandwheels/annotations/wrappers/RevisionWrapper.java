package com.bikesandwheels.annotations.wrappers;

import com.bikesandwheels.annotations.*;
import com.google.common.collect.Sets;

import java.util.*;

public class RevisionWrapper {
    private DateWrapper dateWrapper;
    private String comment;
    private Set<String> authors;

    public RevisionWrapper(Revision revision) {
        if (revision == null)
            throw new NullPointerException();
        dateWrapper = new DateWrapper(revision.date());
        comment = revision.comment();
        authors = Sets.newHashSet();
        fillAuthors(revision);
    }

    private void fillAuthors(Revision revision) {
        if (revision.authors() != null)
            for (Author author : revision.authors())
                authors.add(new AuthorWrapper(author).getName());
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public String getComment() {
        return comment;
    }

    public java.util.Date getDate() {
        return dateWrapper.getDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RevisionWrapper that = (RevisionWrapper) o;

        if (!authors.equals(that.authors)) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (!dateWrapper.equals(that.dateWrapper)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dateWrapper.hashCode();
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + authors.hashCode();
        return result;
    }
}
