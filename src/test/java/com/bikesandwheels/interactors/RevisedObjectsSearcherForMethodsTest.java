package com.bikesandwheels.interactors;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisedObjectsSearcher;
import com.google.common.collect.Sets;
import org.junit.*;

import static com.bikesandwheels.TestUtils.*;
import static com.bikesandwheels.annotations.wrappers.WrapperUtils.*;
import static com.bikesandwheels.interactors.TestModel.MethodsModel.*;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class RevisedObjectsSearcherForMethodsTest {
    private static RevisedObjectsSearcher searcher;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        searcher = new RevisedObjectsSearcher(Sets.newHashSet(
                NotRevisedClassWithRevisedMethod.class,
                RevisedClassWithNotRevisedMethod.class,
                RevisedClassWithRevisedMethod.class));
    }

    @Test
    public void revisedClass_containsRevisions() throws Exception {
        ClassesRevisedObjectsMap revisedObjects = searcher.findAllRevisedObjects();
        assertThat(revisedObjects, not(IS_EMPTY_REVISED_OBJECTS_COLLECTION));
    }

    @Test
    public void givenNotRevisedClassWithRevisedMethod_shouldFindMethodRevision() throws Exception {
        ClassesRevisedObjectsMap revisedObjects = searcher.findAllRevisedObjects();
        assertThat(revisedObjects.getRevisions(NotRevisedClassWithRevisedMethod.class),
                are(new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 19)))));
    }

    @Ignore @Test //TODO
    public void givenNotRevisedClassWithRevisedMethod_shouldFindMethod() throws Exception {
        ClassesRevisedObjectsMap revisedObjects = searcher.findAllRevisedObjects();
//        assertThat(revisedObjects.getClassRevisedObjects(NotRevisedClassWithRevisedMethod.class),
//                are(new RevisedMethod(revisedObjects.getRevisions(NotRevisedClassWithRevisedMethod.class),
//                        NotRevisedClassWithRevisedMethod.class.getMethod("revisedMethod"))));
    }

    @Test
    public void givenRevisedClassWithRevisedMethod_shouldFindClassAndMethodRevisions() throws Exception {
        ClassesRevisedObjectsMap revisedObjects = searcher.findAllRevisedObjects();
        assertThat(revisedObjects.getRevisions(RevisedClassWithRevisedMethod.class),
                are(
                    new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 21))),
                    new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 22)))));
    }

    @Test
    public void givenRevisedClassWithNotRevisedMethod_shouldFindClassRevision() throws Exception {
        ClassesRevisedObjectsMap revisedObjects = searcher.findAllRevisedObjects();
        assertThat(revisedObjects.getRevisions(RevisedClassWithNotRevisedMethod.class),
                are(new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 20)))));
    }

        /*@Test
        public void revisedClasses_AreReturned() throws Exception {
            assertThat(searcher.findAllRevisedObjects().getClasses(),
                    are(
                            TestModel.BaseRevisedClass.class,
                            TestModel.DerivedRevisedClass.class,
                            TestModel.DerivedHistoryRevisedClass.class));
        }

        @Test
        public void revisedClass_HaveCorrectRevision() throws Exception {
            assertThat(searcher.findAllRevisedObjects().getRevisions(TestModel.BaseRevisedClass.class),
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
