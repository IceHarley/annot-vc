package com.bikesandwheels.interactors;

import com.bikesandwheels.tools.*;
import org.junit.*;

import java.util.Set;

import static com.bikesandwheels.tools.TestUtils.areDerivatives;
import static com.bikesandwheels.tools.TestUtils.IS_EMPTY;
import static com.bikesandwheels.interactors.TestModel.*;
import static org.junit.Assert.assertThat;

public class HierarchyRevisedClassesSearcherTest {
    private static HierarchyAnnotatedClassesSearcher annotatedClassesSearcher;

    public static class GivenTestModelWithoutAnnotations {
        @Before
        public void setUp() throws Exception {
            annotatedClassesSearcher = AnnotatedClassesSearcherFactory.make(EmptyTestModel.class);
        }

        @Test
        public void searchResultIsEmptyTest() throws Exception {
            Set<Class<?>> revisedClasses = annotatedClassesSearcher.search();
            assertThat(revisedClasses, IS_EMPTY);
        }
    }

    @SuppressWarnings("unchecked")
    public static class GivenTestModelWithAnnotations {
        @Before
        public void setUp() throws Exception {
            annotatedClassesSearcher = AnnotatedClassesSearcherFactory.make(BaseRevisedClass.class);
        }

        @Test
        public void revisedClasses_shouldBeFound() throws Exception {
            Set<Class<?>> revisedClasses = annotatedClassesSearcher.search();
            assertThat(revisedClasses,
                    areDerivatives(
                            BaseRevisedClass.class,
                            DerivedRevisedClass.class,
                            DerivedNotRevisedClass.class,
                            DerivedHistoryRevisedClass.class));
        }
    }
}
