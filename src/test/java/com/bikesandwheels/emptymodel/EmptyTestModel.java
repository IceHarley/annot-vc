package com.bikesandwheels.emptymodel;

@SuppressWarnings({"ALL"})
public interface EmptyTestModel {
    class NotAnnotatedClass {};

    class NotAnnotatedClassWithNotAnnotatedMethod {
        void NotAnnotatedMethod() {}
    }

    @Deprecated
    class ClassAnnotatedWithDifferentAnnotation {}
}
