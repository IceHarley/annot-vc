package com.bikesandwheels.interactors;

import com.bikesandwheels.model.TestModel;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.*;

import static com.bikesandwheels.TestUtils.*;
import static com.bikesandwheels.model.TestModel.*;
import static com.bikesandwheels.model.TestModel.MethodsModel.*;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class PackageRevisedClassesSearcherTest {
    private static RevisedClassesSearcher revisedClassesSearcher;

    public static class GivenTestModel {
        @Before
        public void setUp() throws Exception {
            revisedClassesSearcher = new PackageRevisedClassesSearcher(TestModel.class);
        }

        @Test
        public void notAnnotatedClass_shouldNotBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = revisedClassesSearcher.search();
            assertThat(annotatedClasses, not(contains(NotAnnotatedClass.class)));
        }

        @Test
        public void revisionAnnotatedClass_shouldBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = revisedClassesSearcher.search();
            assertThat(annotatedClasses, not(IS_EMPTY));
            assertThat(annotatedClasses, contains(RevisedClass.class));
        }

        @Test
        public void notAnnotatedClassWithAnnotatedMethod_shouldBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = revisedClassesSearcher.search();
            assertThat(annotatedClasses, contains(NotRevisedClassWithRevisedMethod.class));
        }

        @Test
        public void historyAnnotatedClass_shouldBeFound() throws Exception {
            Set<Class<?>> annotatedClasses = revisedClassesSearcher.search();
            assertThat(annotatedClasses, contains(RevisionsHistoryAnnotatedClass.class));
        }
    }
}
