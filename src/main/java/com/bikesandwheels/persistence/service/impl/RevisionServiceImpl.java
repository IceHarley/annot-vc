package com.bikesandwheels.persistence.service.impl;

import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;
import com.bikesandwheels.persistence.repositories.RevisionRepository;
import com.bikesandwheels.persistence.service.RevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public class RevisionServiceImpl extends BaseServiceImpl<Revision> implements RevisionService {
    @Autowired
    RevisionRepository revisionRepository;

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
}
