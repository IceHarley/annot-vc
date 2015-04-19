package com.bikesandwheels.persistence.converter;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;
import com.google.common.collect.*;

import java.util.*;

public class Converter {
    public List<Revision> convert(RevisedObject revisedObject) {
        if (revisedObject == null)
            return Lists.newArrayList();
        return convertObject(revisedObject);
    }

    private List<Revision> convertObject(RevisedObject revisedObject) {
        List<Revision> revisions = Lists.newArrayList();
        for (RevisionWrapper wrapped : revisedObject.getRevisions()) {
            Revision revision = new Revision();
            revision.setDate(wrapped.getDate());
            revision.setComment(wrapped.getComment());
            if (revisedObject instanceof RevisedClass) {
                Class classEntity = new Class();
                classEntity.setCanonicalName(((RevisedClass) revisedObject).getClassName());
                revision.setRevisedClass(classEntity);
            }
            else if (revisedObject instanceof RevisedMethod) {
                Method methodEntity = new Method();
                RevisedMethod revisedMethod = (RevisedMethod) revisedObject;
                methodEntity.setName(revisedMethod.getMethodName());
                methodEntity.setSignature(revisedMethod.getMethodSignature());

                Class declaringClassEntity = new Class();
                declaringClassEntity.setCanonicalName(revisedMethod.getDeclaringClassName());

                methodEntity.setDeclaringClass(declaringClassEntity);
                revision.setRevisedMethod(methodEntity);
            }
            revisions.add(revision);
        }
        return revisions;
    }
}
