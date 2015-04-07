package com.bikesandwheels;

import com.bikesandwheels.emptymodel.EmptyTestModel;
import com.bikesandwheels.engine.AnnotatedClassesSearcher;
import com.bikesandwheels.model.TestModel;
import org.hamcrest.*;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.*;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class AnnotatedClassesSearcherTest {
    private static AnnotatedClassesSearcher searcher;

    public static class GivenTestModelWithoutAnnotations {
        @Before
        public void setUp() throws Exception {
            searcher = new AnnotatedClassesSearcher(EmptyTestModel.class);
        }

        @Test
        public void searchResultIsEmptyTest() throws Exception {
            searcher = new AnnotatedClassesSearcher(EmptyTestModel.class);
            Set<Class<?>> annotatedClasses = searcher.getAnnotatedClasses();
            assertThat(annotatedClasses, isEmpty);
        }
    }

    public static class GivenNonEmptyTestModel {
        @Before
        public void setUp() throws Exception {
            searcher = new AnnotatedClassesSearcher(TestModel.class);
        }

        @Test
        public void notAnnotatedClass_shouldNotBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = searcher.getAnnotatedClasses();
            assertThat(annotatedClasses, not(contains(TestModel.NotAnnotatedClass.class)));
        }

        @Test
        public void revisionAnnotatedClass_shouldBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = searcher.getAnnotatedClasses();
            assertThat(annotatedClasses, not(isEmpty));
            assertThat(annotatedClasses, contains(TestModel.RevisionAnnotatedClass.class));
        }

        @Test
        public void notAnnotatedClassWithAnnotatedMethod_shouldBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = searcher.getAnnotatedClasses();
            assertThat(annotatedClasses, contains(TestModel.NotAnnotatedClassWithAnnotatedMethod.class));
        }

        @Test
        public void historyAnnotatedClass_shouldBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = searcher.getAnnotatedClasses();
            assertThat(annotatedClasses, contains(TestModel.RevisionsHistoryAnnotatedClass.class));
        }
    }

    private static final BaseMatcher<Set<Class<?>>> isEmpty = new BaseMatcher<Set<Class<?>>>() {
        public boolean matches(Object o) {
            return ((Collection<?>) o).isEmpty();
        }

        public void describeTo(Description description) {
            description.appendText("empty collection");
        }
    };

    private abstract static class Match<T> extends BaseMatcher<T> {
        public void describeTo(Description description) {}
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
