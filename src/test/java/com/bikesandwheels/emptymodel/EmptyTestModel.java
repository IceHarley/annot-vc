package com.bikesandwheels.emptymodel;

@SuppressWarnings({"ALL"})
public interface EmptyTestModel {
    public class C1 {};

    class C2 {
        void M1() {}
    }

    interface I1 {}

    class C3 {}

    class C4 extends C3 implements I1 {}
    class C5 extends C3 {}

    class C6 extends C4 {}
    class C7 extends C4 {}

    @Deprecated
    class ClassAnnotatedWithDifferentAnnotation {}
}
