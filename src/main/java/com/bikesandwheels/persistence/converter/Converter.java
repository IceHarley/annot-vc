package com.bikesandwheels.persistence.converter;

import com.bikesandwheels.interactors.annotation_wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.persistence.model.Class;
import com.bikesandwheels.persistence.model.*;
import com.google.common.collect.Lists;

import java.util.*;

public class Converter implements RevisedObjectVisitor {
    private Revision revision;

    public List<Revision> convert(ClassesRevisedObjectsMap revisedObjectsMap) {
        List<Revision> revisions = Lists.newArrayList();
        for (java.lang.Class<?> aClass : revisedObjectsMap.getClasses())
            revisions.addAll(convert(revisedObjectsMap.getClassRevisedObjects(aClass)));
        return revisions;
    }

    public Collection<? extends Revision> convert(RevisedObjects revisedObjects) {
        List<Revision> revisions = Lists.newArrayList();
        for (RevisedObject revisedObject : revisedObjects.getAll())
            revisions.addAll(convert(revisedObject));
        return revisions;
    }

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
        revisedObject.accept(this);
        return revision;
    }

    private Method getMethod(String declaringClassName, String methodName, String methodSignature) {
        Class declaringClassEntity = createClass(declaringClassName);
        Method methodEntity = new Method();
        methodEntity.setName(methodName);
        methodEntity.setSignature(methodSignature);
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
        Method methodEntity = getMethod(revisedMethod.getDeclaringClassName(), revisedMethod.getMethodName(), revisedMethod.getMethodSignature());
        revision.setRevisedMethod(methodEntity);
    }
}
