package com.bikesandwheels.interactors;

import com.bikesandwheels.emptymodel.EmptyTestModel;
import org.junit.*;

import java.util.Set;

import static com.bikesandwheels.TestUtils.areDerivatives;
import static com.bikesandwheels.TestUtils.IS_EMPTY;
import static com.bikesandwheels.model.TestModel.*;
import static org.junit.Assert.assertThat;

public class ClassRevisedClassesSearcherTest {
    private static RevisedClassesSearcher revisedClassesSearcher;

    public static class GivenTestModelWithoutAnnotations {
        @Before
        public void setUp() throws Exception {
            revisedClassesSearcher = new ClassRevisedClassesSearcher(EmptyTestModel.class);
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
            revisedClassesSearcher = new ClassRevisedClassesSearcher(RevisedClass.class);
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
