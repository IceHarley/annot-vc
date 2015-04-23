package com.bikesandwheels.persistence.converter;

import com.bikesandwheels.domain.annotation_wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.Converter;
import com.bikesandwheels.persistence.model.Class;
import com.bikesandwheels.persistence.model.*;
import com.google.common.collect.Lists;

import java.util.*;

public class ConverterImpl implements RevisedObjectVisitor, Converter {
    private Revision revision;

    @Override
    public List<Revision> convert(ClassesRevisedObjectsMap revisedObjectsMap) {
        List<Revision> revisions = Lists.newArrayList();
        for (java.lang.Class<?> aClass : revisedObjectsMap.getClasses())
            revisions.addAll(convert(revisedObjectsMap.getClassRevisedObjects(aClass)));
        return revisions;
    }

    @Override
    public Collection<? extends Revision> convert(RevisedObjects revisedObjects) {
        List<Revision> revisions = Lists.newArrayList();
        for (RevisedObject revisedObject : revisedObjects.getAll())
            revisions.addAll(convert(revisedObject));
        return revisions;
    }

    @Override
    public List<Revision> convert(RevisedObject revisedObject) {
        if (revisedObject == null)
            return Lists.newArrayList();
        return convertObject(revisedObject);
    }

    private List<Revision> convertObject(RevisedObject revisedObject) {
        List<Revision> revisions = Lists.newArrayList();
        for (RevisionWrapper wrapped : revisedObject.getRevisions())
            revisions.add(createRevision(revisedObject, wrapped));
        return revisions;
    }

    private Revision createRevision(RevisedObject revisedObject, RevisionWrapper wrapped) {
        revision = new Revision();
        revision.setDate(wrapped.getDate());
        revision.setComment(wrapped.getComment());
        fillAuthors(wrapped);
        revisedObject.accept(this);
        return revision;
    }

    private void fillAuthors(RevisionWrapper wrapped) {
        List<Author> authors = Lists.newArrayList();
        for (String name : wrapped.getAuthors()) {
            Author author = new Author();
            author.setName(name);
            authors.add(author);
        }
        revision.setAuthors(authors);
    }

    private Method getMethod(String declaringClassName, String methodName, String signature, String returnType) {
        Class declaringClassEntity = createClass(declaringClassName);
        Method methodEntity = new Method();
        methodEntity.setName(methodName);
        methodEntity.setSignature(signature);
        methodEntity.setReturnType(returnType);
        methodEntity.setDeclaringClass(declaringClassEntity);
        return methodEntity;
    }

    private Class createClass(String className) {
        Class classEntity = new Class();
        classEntity.setCanonicalName(className);
        return classEntity;
    }

    public void visit(RevisedClass revisedClass) {
        Class classEntity = createClass(revisedClass.getClassName());
        revision.setRevisedClass(classEntity);
    }

    public void visit(RevisedMethod revisedMethod) {
        Method methodEntity = getMethod(
                revisedMethod.getDeclaringClassName(),
                revisedMethod.getMethodName(),
                revisedMethod.getMethodSignature(),
                revisedMethod.getMethodReturnType());
        revision.setRevisedMethod(methodEntity);
    }
}
