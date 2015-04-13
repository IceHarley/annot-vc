package com.bikesandwheels.interactors;

import com.bikesandwheels.main.Config;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static com.bikesandwheels.interactors.TestModel.*;
import static com.bikesandwheels.interactors.TestModel.MethodsModel.NotRevisedClassWithRevisedMethod;
import static com.bikesandwheels.tools.TestUtils.contains;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.reflections.util.ClasspathHelper.forClass;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {Config.class})
public class PathAnnotatedClassesSearcherTest {
    @Autowired
    private AnnotatedClassesSearcher annotatedClassesSearcher;
    private TestContextManager testContextManager;

    @Before
    public void setUp() throws Exception {
        annotatedClassesSearcher.setUrl(forClass(TestModel.class));
    }

    @Test
    public void verifyingAutoWiring() throws Exception {
        assertNotNull(annotatedClassesSearcher);
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
