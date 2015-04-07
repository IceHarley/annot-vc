package com.bikesandwheels;

import com.bikesandwheels.emptymodel.EmptyTestModel;
import com.bikesandwheels.engine.*;
import com.bikesandwheels.model.TestModel;
import org.hamcrest.*;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.lang.annotation.Annotation;
import java.util.*;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class AnnotationsAnalyzerTest {
    private static AnnotationsAnalyzer analyzer;
    private static AnnotatedClassesSearcher searcher;

    public static class GivenTestModelWithoutAnnotations {
        @Before
        public void setUp() throws Exception {
            searcher = new AnnotatedClassesSearcher(EmptyTestModel.class);
            analyzer = new AnnotationsAnalyzer(searcher.getAnnotatedClasses());
        }

        @Test
        public void ifNoAnnotatedFiles_AnalyzeResultIsEmpty() throws Exception {
            Set<Annotation> revisions = analyzer.getRevisions();
            assertThat(revisions, isEmpty);
        }
    }

    public static class GivenTestModelWithAnnotations {
        @Before
        public void setUp() throws Exception {
            searcher = new AnnotatedClassesSearcher(TestModel.class);
            analyzer = new AnnotationsAnalyzer(searcher.getAnnotatedClasses());
        }

        @Test
        public void annotatedClass_containsRevisions() throws Exception {
            Set<Annotation> revisions = analyzer.getRevisions();
            assertThat(revisions, not(isEmpty));
        }

        @Ignore @Test
        //TODO
        public void annotatedClassRevision_containsDate() throws Exception {
            Set<Annotation> revisions = analyzer.getRevisions();
            Annotation revision = revisions.iterator().next();
            //Assert.assertThat(revision., not(isEmpty));
            assertNotNull(revision);
        }
    }

    private static final BaseMatcher<Set<Annotation>> isEmpty = new BaseMatcher<Set<Annotation>>() {
        public boolean matches(Object o) {
            return ((Collection<?>) o).isEmpty();
        }

        public void describeTo(Description description) {
            description.appendText("empty collection");
        }
    };
}
