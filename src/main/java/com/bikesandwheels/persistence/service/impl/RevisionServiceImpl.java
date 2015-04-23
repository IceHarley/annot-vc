package com.bikesandwheels.persistence.service.impl;

import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;
import com.bikesandwheels.persistence.repositories.RevisionRepository;
import com.bikesandwheels.persistence.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public class RevisionServiceImpl extends BaseServiceImpl<Revision> implements RevisionService {
    @Autowired
    RevisionRepository revisionRepository;
    @Autowired
    AuthorService authorService;
    @Autowired
    ClassService classService;
    @Autowired
    MethodService methodService;

    @Override
    protected CrudRepository<Revision, Long> getRepository() {
        return revisionRepository;
    }

    @Override
    protected void fillIdIfExists(Revision revision) {
        if (revision.getId() == null) {
            Revision existedRevision = getByClassMethodAndDate(revision.getRevisedClass(), revision.getRevisedMethod(), revision.getDate());
            if (existedRevision != null)
                revision.setId(existedRevision.getId());
        }
    }

    public Revision getByClassMethodAndDate(Class revisedClass, Method revisedMethod, Date date) {
        return revisionRepository.findByClassMethodAndDate(revisedClass, revisedMethod, date);
    }

    public Revision save(Revision revision) {
        if (revision.getRevisedClass() == null && revision.getRevisedMethod() == null)
            throw new IllegalArgumentException();
        processAuthors(revision);
        processRevisedClass(revision);
        processRevisedMethod(revision);
        fillIdIfExists(revision);
        return super.save(revision);
    }

    private void processRevisedMethod(Revision revision) {
        if (revision.getRevisedMethod() != null) {
            addToMethod(revision);
            methodService.save(revision.getRevisedMethod());
        }
    }

    private void processRevisedClass(Revision revision) {
        if (revision.getRevisedClass() != null) {
            addToClass(revision);
            classService.save(revision.getRevisedClass());
        }
    }

    private void processAuthors(Revision revision) {
        if (revision.getAuthors().size() > 0) {
            addToAuthor(revision);
            authorService.save(revision.getAuthors());
        }
    }

    private void addToClass(Revision revision) {
        if (revision.getRevisedClass() != null)
            revision.getRevisedClass().getRevisions().add(revision);
    }

    private void addToMethod(Revision revision) {
        if (revision.getRevisedMethod() != null)
            revision.getRevisedMethod().getRevisions().add(revision);
    }

    private void addToAuthor(Revision revision) {
        for (Author author : revision.getAuthors())
            if (!author.getRevisions().contains(revision))
                author.getRevisions().add(revision);
    }
}
