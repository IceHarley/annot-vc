package com.bikesandwheels.interactors;


import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.ClassesRevisedObjectsMap;
import com.bikesandwheels.main.Config;
import com.google.common.collect.Sets;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Set;

import static com.bikesandwheels.annotations.wrappers.WrapperUtils.createDate;
import static com.bikesandwheels.annotations.wrappers.WrapperUtils.createDefaultRevision;
import static com.bikesandwheels.interactors.TestModel.*;
import static com.bikesandwheels.tools.TestUtils.*;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

@RunWith(HierarchicalContextRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {Config.class})
public class RevisedObjectsSearcherForClassesTest {
    @Autowired
    private RevisedSearcher searcher;

    @Before
    public void setUp() throws Exception {
        prepareSpringTestRunner();
    }

    @Test
    public void givenEmptyFilesList_SearchResultIsEmpty() throws Exception {
        searcher.setClasses(Sets.<Class<?>>newHashSet());
        assertThat(searcher.findAllRevisedObjects(), IS_EMPTY_REVISED_OBJECTS_COLLECTION);
    }

    @Test
    public void givenNoAnnotatedFiles_SearchResultIsEmpty() throws Exception {
        searcher.setClasses(Sets.<Class<?>>newHashSet(EmptyTestModel.class));
        assertThat(searcher.findAllRevisedObjects(), IS_EMPTY_REVISED_OBJECTS_COLLECTION);
    }

    public class GivenRevisedClassHierarchyTest {
        @Before
        public void setUp() throws Exception {
            searcher.setClasses(Sets.<Class<?>>newHashSet(
                    BaseRevisedClass.class,
                    DerivedRevisedClass.class,
                    DerivedNotRevisedClass.class,
                    DerivedHistoryRevisedClass.class));
        }

        @Test
        public void revisedClass_containsRevisions() throws Exception {
            assertThat(searcher.findAllRevisedObjects(), not(IS_EMPTY_REVISED_OBJECTS_COLLECTION));
        }

        @Test
        public void notRevisedClass_IsNotReturned() throws Exception {
            ClassesRevisedObjectsMap revisedObjects = searcher.findAllRevisedObjects();
            assertFalse(revisedObjects.containsClass(DerivedNotRevisedClass.class));
        }

        @Test
        public void revisedClasses_AreReturned() throws Exception {
            assertThat(searcher.findAllRevisedObjects().getClasses(),
                    are(
                        BaseRevisedClass.class,
                        DerivedRevisedClass.class,
                        DerivedHistoryRevisedClass.class));
        }

        @Test
        public void revisedClass_HaveCorrectRevision() throws Exception {
            assertThat(searcher.findAllRevisedObjects().getRevisions(BaseRevisedClass.class),
                    are(new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 1)))));
        }

        @Test
        public void historyRevisedClass_HaveCorrectRevisions() throws Exception {
            assertThat(searcher.findAllRevisedObjects().getRevisions(DerivedHistoryRevisedClass.class),
                are(
                    new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 6))),
                    new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 7)))));
        }
    }

    @Test
    public void givenEmptyHistoryAndRevision_revisionIsReturned() throws Exception {
        searcher.setClasses(Sets.<Class<?>>newHashSet(EmptyHistoryAndRevisionAnnotatedClass.class));
        Set<Class<?>> classes = searcher.findAllRevisedObjects().getClasses();
        assertThat(classes, are(EmptyHistoryAndRevisionAnnotatedClass.class));
    }

    @Test
    public void givenEmptyHistory_NoRevisedObjects() throws Exception {
        searcher.setClasses(Sets.<Class<?>>newHashSet(EmptyHistoryRevisedClass.class));
        ClassesRevisedObjectsMap allRevisedObjects = searcher.findAllRevisedObjects();
        assertThat(allRevisedObjects, IS_EMPTY_REVISED_OBJECTS_COLLECTION);
    }

    //This is substitution for @RunWith(SpringJUnit4ClassRunner.class)
    private void prepareSpringTestRunner() throws Exception {
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }
}
