package com.bikesandwheels.interactors;

import com.bikesandwheels.tools.HierarchyAnnotatedClassesSearcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static com.bikesandwheels.interactors.TestModel.*;
import static com.bikesandwheels.tools.TestUtils.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class})
public class HierarchyRevisedClassesSearcherTest {
    @Autowired
    private HierarchyAnnotatedClassesSearcher annotatedClassesSearcher;

    @Test
    public void searchResultIsEmptyTest() throws Exception {
        annotatedClassesSearcher.setBaseClass(EmptyTestModel.class);
        assertThat(annotatedClassesSearcher.search(), IS_EMPTY);
    }

    @Test
    public void revisedClasses_shouldBeFound() throws Exception {
        annotatedClassesSearcher.setBaseClass(BaseRevisedClass.class);
        assertThat(annotatedClassesSearcher.search(),
                areDerivatives(
                        BaseRevisedClass.class,
                        DerivedRevisedClass.class,
                        DerivedNotRevisedClass.class,
                        DerivedHistoryRevisedClass.class));
    }

}
