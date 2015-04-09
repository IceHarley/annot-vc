package com.bikesandwheels.interactors;

import com.bikesandwheels.emptymodel.EmptyTestModel;
import com.bikesandwheels.model.TestModel;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.*;

import static com.bikesandwheels.TestUtils.*;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class PackageRevisedClassesSearcherTest {
    private static RevisedClassesSearcher revisedClassesSearcher;

    public static class GivenTestModelWithoutAnnotations {
        @Before
        public void setUp() throws Exception {
            revisedClassesSearcher = new PackageRevisedClassesSearcher(EmptyTestModel.class);
        }

        @Test
        public void searchResultIsEmptyTest() throws Exception {
            revisedClassesSearcher = new PackageRevisedClassesSearcher(EmptyTestModel.class);
            Set<Class<?>> annotatedClasses = revisedClassesSearcher.search();
            assertThat(annotatedClasses, isEmpty);
        }
    }

    public static class GivenNonEmptyTestModel {
        @Before
        public void setUp() throws Exception {
            revisedClassesSearcher = new PackageRevisedClassesSearcher(TestModel.class);
        }

        @Test
        public void notAnnotatedClass_shouldNotBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = revisedClassesSearcher.search();
            assertThat(annotatedClasses, not(contains(TestModel.NotAnnotatedClass.class)));
        }

        @Test
        public void revisionAnnotatedClass_shouldBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = revisedClassesSearcher.search();
            assertThat(annotatedClasses, not(isEmpty));
            assertThat(annotatedClasses, contains(TestModel.RevisionAnnotatedClass.class));
        }

        @Test
        public void notAnnotatedClassWithAnnotatedMethod_shouldBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = revisedClassesSearcher.search();
            assertThat(annotatedClasses, contains(TestModel.NotAnnotatedClassWithAnnotatedMethod.class));
        }

        @Test
        public void historyAnnotatedClass_shouldBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = revisedClassesSearcher.search();
            assertThat(annotatedClasses, contains(TestModel.RevisionsHistoryAnnotatedClass.class));
        }
    }
}
