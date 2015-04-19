package com.bikesandwheels.domain;

public interface RevisedObjectVisitor {
    void visit(RevisedClass revisedObject);
    void visit(RevisedMethod revisedObject);
}
