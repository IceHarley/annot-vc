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
}
