package com.bikesandwheels.tools;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import org.hamcrest.*;

import java.util.*;

public class TestUtils {
    public static final BaseMatcher<Set<Class<?>>> IS_EMPTY = new BaseMatcher<Set<Class<?>>>() {
        public boolean matches(Object o) {
            return ((Collection<?>) o).isEmpty();
        }

        public void describeTo(Description description) {
            description.appendText("empty collection");
        }
    };

    public static final BaseMatcher<ClassesRevisedObjectsMap> IS_EMPTY_REVISED_OBJECTS_COLLECTION = new BaseMatcher<ClassesRevisedObjectsMap>() {
        public boolean matches(Object o) {
            return ((ClassesRevisedObjectsMap) o).isEmpty();
        }

        public void describeTo(Description description) {
            description.appendText("empty collection");
        }
    };

    private abstract static class Match<T> extends BaseMatcher<T> {
        public void describeTo(Description description) { }

    }

    public static <T> Matcher<Set<? super T>> areDerivatives(final T... ts) {
        final Collection<?> c1 = Arrays.asList(ts);
        return new Match<Set<? super T>>() {
            public boolean matches(Object o) {
                Collection<?> c2 = (Collection<?>) o;
                return c1.containsAll(c2) && c2.containsAll(c1);
            }
        };
    }

    public static Match<Set<Class<?>>> are(final Class<?>... classes) {
        final Collection<?> c1 = Arrays.asList(classes);
        return new Match<Set<Class<?>>>() {
            public boolean matches(Object o) {
                Collection<?> c2 = (Collection<?>) o;
                return c1.containsAll(c2) && c2.containsAll(c1);
            }
        };
    }

    public static Match<RevisedObjects> are(final RevisedObject... revisedObjects) {
        final RevisedObjects c1 = new RevisedObjects();
        for (RevisedObject revisedObject : revisedObjects)
            c1.add(revisedObject);
        return new Match<RevisedObjects>() {
            public boolean matches(Object o) {
                RevisedObjects c2 = (RevisedObjects) o;
                return c1.containsAll(c2) && c2.containsAll(c1);
            }
        };
    }

    public static Match<Set<RevisionWrapper>> are(final RevisionWrapper... revisionWrappers) {
        final Collection<?> c1 = Arrays.asList(revisionWrappers);
        return new Match<Set<RevisionWrapper>>() {
            public boolean matches(Object o) {
                Collection<?> c2 = (Collection<?>) o;
                return c1.containsAll(c2) && c2.containsAll(c1);
            }
        };
    }

    public static <T> Matcher<Set<? super T>> contains(final T ts) {
        return new Match<Set<? super T>>() {
            public boolean matches(Object o) {
                Collection<?> c = (Collection<?>) o;
                return c.contains(ts);
            }
        };
    }

    public static Matcher<RevisedObjects> contains(final RevisedObject revisedObject) {
        return new Match<RevisedObjects>() {
            public boolean matches(Object o) {
                RevisedObjects revisedObjects = (RevisedObjects) o;
                return revisedObjects.contains(revisedObject);
            }
        };
    }
}
