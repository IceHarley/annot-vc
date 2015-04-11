package com.bikesandwheels.interactors;

import com.bikesandwheels.interactors.revised_classes_searcher.*;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static com.bikesandwheels.TestUtils.*;
import static com.bikesandwheels.interactors.TestModel.*;
import static com.bikesandwheels.interactors.TestModel.MethodsModel.*;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class PathRevisedClassesSearcherTest {
    private static RevisedClassesSearcher revisedClassesSearcher;

    public static class GivenTestModel {
        @Before
        public void setUp() throws Exception {
            revisedClassesSearcher = new PathRevisedClassesSearcher(TestModel.class);
        }

        @Test
        public void notRevisedClass_shouldNotBeFound() throws Exception {
            assertThat(revisedClassesSearcher.search(), not(contains(NotRevisedClass.class)));
        }

        @Test
        public void revisedClass_shouldBeFound() throws Exception {
            assertThat(revisedClassesSearcher.search(), contains(BaseRevisedClass.class));
        }

        @Test
        public void notRevisedClassWithAnnotatedMethod_shouldBeFound() throws Exception {
            assertThat(revisedClassesSearcher.search(), contains(NotRevisedClassWithRevisedMethod.class));
        }

        @Test
        public void historyRevisedClass_shouldBeFound() throws Exception {
            assertThat(revisedClassesSearcher.search(), contains(HistoryRevisedClass.class));
        }
    }
}
