package com.bikesandwheels.interactors;


import com.bikesandwheels.tools.RevisedObjectSearcherFactory;
import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisedObjectsSearcher;
import com.google.common.collect.Sets;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static com.bikesandwheels.annotations.wrappers.WrapperUtils.*;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static com.bikesandwheels.interactors.TestModel.*;
import static com.bikesandwheels.tools.TestUtils.*;

@RunWith(Enclosed.class)
public class RevisedObjectsSearcherForClassesTest {
    private static RevisedObjectsSearcher searcher;

    public static class GivenTestModelWithoutAnnotations {
        @Before
        public void setUp() throws Exception {
            searcher = RevisedObjectSearcherFactory.make(Sets.<Class<?>>newHashSet());
        }

        @Test
        public void ifNoAnnotatedFiles_SearchResultIsEmpty() throws Exception {
            assertThat(searcher.findAllRevisedObjects(), IS_EMPTY_REVISED_OBJECTS_COLLECTION);
        }
    }

    public static class GivenRevisedClassHierarchyTest {
        @Before
        public void setUp() throws Exception {
            searcher = RevisedObjectSearcherFactory.make(Sets.<Class<?>>newHashSet(
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

    public static class HistoryTest {
        @Test
        public void givenEmptyHistory_NoRevisedObjects() throws Exception {
            searcher = RevisedObjectSearcherFactory.make(Sets.<Class<?>>newHashSet(EmptyHistoryRevisedClass.class));
            ClassesRevisedObjectsMap allRevisedObjects = searcher.findAllRevisedObjects();
            assertThat(allRevisedObjects, IS_EMPTY_REVISED_OBJECTS_COLLECTION);
        }

        @Test
        public void givenEmptyHistoryAndRevision_revisionIsReturned() throws Exception {
            searcher = RevisedObjectSearcherFactory.make(Sets.<Class<?>>newHashSet(EmptyHistoryAndRevisionAnnotatedClass.class));
            assertThat(searcher.findAllRevisedObjects().getClasses(), are(EmptyHistoryAndRevisionAnnotatedClass.class));
        }
    }
}
