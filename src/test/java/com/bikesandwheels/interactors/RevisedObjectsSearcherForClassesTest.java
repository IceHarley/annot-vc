package com.bikesandwheels.interactors;


import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisedObjectsSearcher;
import com.google.common.collect.Sets;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static com.bikesandwheels.TestUtils.are;
import static com.bikesandwheels.annotations.wrappers.WrapperUtils.*;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static com.bikesandwheels.interactors.TestModel.*;
import static com.bikesandwheels.TestUtils.*;

@RunWith(Enclosed.class)
public class RevisedObjectsSearcherForClassesTest {
    private static RevisedObjectsSearcher searcher;

    public static class GivenTestModelWithoutAnnotations {
        @Before
        public void setUp() throws Exception {
            searcher = new RevisedObjectsSearcher(Sets.<Class<?>>newHashSet());
        }

        @Test
        public void ifNoAnnotatedFiles_SearchResultIsEmpty() throws Exception {
            assertThat(searcher.findAllRevisedObjects(), IS_EMPTY_REVISED_OBJECTS_COLLECTION);
        }
    }

    public static class RevisedClassesTest {
        @Before
        public void setUp() throws Exception {
            searcher = new RevisedObjectsSearcher(Sets.<Class<?>>newHashSet(
                    RevisedClass.class,
                    DerivedRevisedClass.class,
                    DerivedNotRevisedClass.class,
                    DerivedHistoryRevisedClass.class));
        }

        @Test
        public void annotatedClass_containsRevisions() throws Exception {
            assertThat(searcher.findAllRevisedObjects(), not(IS_EMPTY_REVISED_OBJECTS_COLLECTION));
        }

        @Test
        public void notRevisedClass_IsNotReturned() throws Exception {
            RevisedObjects revisedObjects = searcher.findAllRevisedObjects();
            assertFalse(revisedObjects.containsClass(DerivedNotRevisedClass.class));
        }

        @Test
        public void revisedClasses_AreReturned() throws Exception {
            assertThat(searcher.findAllRevisedObjects().getClasses(),
                are(
                    RevisedClass.class,
                    DerivedRevisedClass.class,
                    DerivedHistoryRevisedClass.class));
        }

        @Test
        public void revisedClass_HaveCorrectRevision() throws Exception {
            assertThat(searcher.findAllRevisedObjects().getRevisions(RevisedClass.class),
                    are(new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 1)))));
        }

        @Test
        public void historyAnnotatedClass_HaveCorrectRevisions() throws Exception {
            assertThat(searcher.findAllRevisedObjects().getRevisions(DerivedHistoryRevisedClass.class),
                are(
                    new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 6))),
                    new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 7)))));
        }
    }

    public static class EmptyHistoryTest {
        @Test
        public void givenEmptyHistory_NoRevisedObjects() throws Exception {
            searcher = new RevisedObjectsSearcher(Sets.<Class<?>>newHashSet(EmptyHistoryAnnotatedClass.class));
            assertThat(searcher.findAllRevisedObjects(), IS_EMPTY_REVISED_OBJECTS_COLLECTION);
        }

        @Test
        public void givenEmptyHistoryAndRevision_revisionIsReturned() throws Exception {
            searcher = new RevisedObjectsSearcher(Sets.<Class<?>>newHashSet(EmptyHistoryAndRevisionAnnotatedClass.class));
            assertThat(searcher.findAllRevisedObjects().getClasses(), are(EmptyHistoryAndRevisionAnnotatedClass.class));
        }
    }
}
