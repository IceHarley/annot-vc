package com.bikesandwheels.interactors;

import com.bikesandwheels.domain.RevisedObjects;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisedObjectsSearcher;
import com.google.common.collect.Sets;
import org.junit.*;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static com.bikesandwheels.TestUtils.*;
import static com.bikesandwheels.interactors.TestModel.MethodsModel.*;

public class RevisedObjectsSearcherForMethodsTest {
    private static RevisedObjectsSearcher searcher;

    public static class RevisedClassesTest {
        @Before
        public void setUp() throws Exception {
            searcher = new RevisedObjectsSearcher(Sets.<Class<?>>newHashSet(
                    NotRevisedClassWithRevisedMethod.class));
        }

        @Test
        public void revisedClass_containsRevisions() throws Exception {
            RevisedObjects revisedObjects = searcher.findAllRevisedObjects();
            assertThat(revisedObjects, not(IS_EMPTY_REVISED_OBJECTS_COLLECTION));
        }

        @Ignore @Test
        //TODO
        public void notRevisedMethod_IsNotReturned() throws Exception {
            RevisedObjects revisedObjects = searcher.findAllRevisedObjects();
            assertThat(revisedObjects.getClasses(), contains(RevisedClassWithNotRevisedMethod.class));
            //assertThat(revisedObjects.getRevisions(RevisedClassWithNotRevisedMethod.class), IS_EMPTY_REVISED_OBJECTS_COLLECTION);
        }

        /*@Test
        public void revisedClasses_AreReturned() throws Exception {
            assertThat(searcher.findAllRevisedObjects().getClasses(),
                    are(
                            TestModel.RevisedClass.class,
                            TestModel.DerivedRevisedClass.class,
                            TestModel.DerivedHistoryRevisedClass.class));
        }

        @Test
        public void revisedClass_HaveCorrectRevision() throws Exception {
            assertThat(searcher.findAllRevisedObjects().getRevisions(TestModel.RevisedClass.class),
                    are(new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 1)))));
        }

        @Test
        public void historyRevisedClass_HaveCorrectRevisions() throws Exception {
            assertThat(searcher.findAllRevisedObjects().getRevisions(TestModel.DerivedHistoryRevisedClass.class),
                    are(
                            new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 6))),
                            new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 7)))));
        }*/
    }
}
