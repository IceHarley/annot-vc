package com.bikesandwheels.interactors;

import com.bikesandwheels.annotations.*;
import com.bikesandwheels.interactors.annotated_classes_searcher.*;
import com.bikesandwheels.interactors.annotated_classes_searcher.scanners.*;
import com.bikesandwheels.tools.*;
import com.google.common.collect.Sets;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.reflections.Reflections;

import static com.bikesandwheels.tools.TestUtils.*;
import static com.bikesandwheels.interactors.TestModel.*;
import static com.bikesandwheels.interactors.TestModel.MethodsModel.*;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class PathAnnotatedClassesSearcherTest {
    private static PathAnnotatedClassesSearcher annotatedClassesSearcher;

    public static class GivenTestModel {
        @Before
        public void setUp() throws Exception {
            Reflections reflections = new ReflectionsBuilder(TestModel.class).make();
            annotatedClassesSearcher = new PathAnnotatedClassesSearcher(reflections,
                    Sets.newHashSet(
                            new ClassesAnnotatedScanner(Revision.class, History.class),
                            new MethodsAnnotatedScanner(Revision.class, History.class)));
        }

        @Test
        public void notRevisedClass_shouldNotBeFound() throws Exception {
            assertThat(annotatedClassesSearcher.search(), not(contains(NotRevisedClass.class)));
        }

        @Test
        public void revisedClass_shouldBeFound() throws Exception {
            assertThat(annotatedClassesSearcher.search(), contains(BaseRevisedClass.class));
        }

        @Test
        public void notRevisedClassWithAnnotatedMethod_shouldBeFound() throws Exception {
            assertThat(annotatedClassesSearcher.search(), contains(NotRevisedClassWithRevisedMethod.class));
        }

        @Test
        public void historyRevisedClass_shouldBeFound() throws Exception {
            assertThat(annotatedClassesSearcher.search(), contains(HistoryRevisedClass.class));
        }
    }
}
