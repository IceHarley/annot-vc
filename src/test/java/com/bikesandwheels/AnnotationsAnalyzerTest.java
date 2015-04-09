package com.bikesandwheels;

import com.bikesandwheels.domain.RevisedObject;
import com.bikesandwheels.emptymodel.EmptyTestModel;
import com.bikesandwheels.interactors.*;
import com.bikesandwheels.model.TestModel;
import org.hamcrest.*;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

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
            assertThat(analyzer.getRevisedObjects(), isEmpty);
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
            assertThat(analyzer.getRevisedObjects(), not(isEmpty));
        }

        @Ignore @Test
        public void annotatedClassRevision_containsDate() throws Exception {
            RevisedObject revisedObject = analyzer.getRevisedObjects().iterator().next();
            //revisedObject.getRevisions()
            assertNotNull(revisedObject);
        }
    }

    private static final BaseMatcher<Set<RevisedObject>> isEmpty = new BaseMatcher<Set<RevisedObject>>() {
        public boolean matches(Object o) {
            return ((Collection<?>) o).isEmpty();
        }

        public void describeTo(Description description) {
            description.appendText("empty collection");
        }
    };
}
