package com.bikesandwheels;

import org.hamcrest.*;

import java.util.*;

public class TestUtils {
    public static final BaseMatcher<Set<Class<?>>> isEmpty = new BaseMatcher<Set<Class<?>>>() {
        public boolean matches(Object o) {
            return ((Collection<?>) o).isEmpty();
        }

        public void describeTo(Description description) {
            description.appendText("empty collection");
        }
    };

    private abstract static class Match<T> extends BaseMatcher<T> {
        public void describeTo(Description description) { }
    }

    public static <T> Matcher<Set<? super T>> are(final T... ts) {
        final Collection<?> c1 = Arrays.asList(ts);
        return new Match<Set<? super T>>() {
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
}
