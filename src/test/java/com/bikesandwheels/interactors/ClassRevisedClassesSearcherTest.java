package com.bikesandwheels.interactors;

import com.bikesandwheels.model.TestModel;
import org.junit.*;

import java.util.Set;

import static com.bikesandwheels.TestUtils.are;
import static com.bikesandwheels.model.TestModel.*;
import static org.junit.Assert.assertThat;

public class ClassRevisedClassesSearcherTest {
    private static RevisedClassesSearcher revisedClassesSearcher;

    @Before
    public void setUp() throws Exception {
        revisedClassesSearcher = new ClassRevisedClassesSearcher(TestModel.class);
    }

    @Ignore @Test
    //TODO
    public void notAnnotatedClass_shouldNotBeFound() throws Exception {
        Set<Class<?>> annotatedClasses = revisedClassesSearcher.search();
        assertThat(annotatedClasses, are(RevisionAnnotatedClass.class, DerivedRevisionAnnotatedClass.class));
    }
}
