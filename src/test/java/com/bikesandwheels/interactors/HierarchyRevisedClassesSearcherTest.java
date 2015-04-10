package com.bikesandwheels.interactors;

import com.bikesandwheels.interactors.revised_classes_searcher.*;
import org.junit.*;

import java.util.Set;

import static com.bikesandwheels.TestUtils.areDerivatives;
import static com.bikesandwheels.TestUtils.IS_EMPTY;
import static com.bikesandwheels.interactors.TestModel.*;
import static org.junit.Assert.assertThat;

public class HierarchyRevisedClassesSearcherTest {
    private static RevisedClassesSearcher revisedClassesSearcher;

    public static class GivenTestModelWithoutAnnotations {
        @Before
        public void setUp() throws Exception {
            revisedClassesSearcher = new HierarchyRevisedClassesSearcher(EmptyTestModel.class);
        }

        @Test
        public void searchResultIsEmptyTest() throws Exception {
            Set<Class<?>> revisedClasses = revisedClassesSearcher.search();
            assertThat(revisedClasses, IS_EMPTY);
        }
    }

    @SuppressWarnings("unchecked")
    public static class GivenTestModelWithAnnotations {
        @Before
        public void setUp() throws Exception {
            revisedClassesSearcher = new HierarchyRevisedClassesSearcher(RevisedClass.class);
        }

        @Test
        public void revisedClasses_shouldBeFound() throws Exception {
            Set<Class<?>> revisedClasses = revisedClassesSearcher.search();
            assertThat(revisedClasses,
                    areDerivatives(
                            RevisedClass.class,
                            DerivedRevisedClass.class,
                            DerivedNotRevisedClass.class,
                            DerivedHistoryRevisedClass.class));
        }
    }
}
