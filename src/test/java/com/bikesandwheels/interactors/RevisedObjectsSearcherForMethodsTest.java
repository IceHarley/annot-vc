package com.bikesandwheels.interactors;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisedObjectsSearcher;
import com.google.common.collect.Sets;
import org.junit.*;

import java.util.Set;

import static com.bikesandwheels.TestUtils.*;
import static com.bikesandwheels.annotations.wrappers.WrapperUtils.*;
import static com.bikesandwheels.interactors.TestModel.MethodsModel.*;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class RevisedObjectsSearcherForMethodsTest {
    private static ClassesRevisedObjectsMap revisedObjects;
    private static Class<?> aClass;
    private static Set<RevisionWrapper> revisions;
    private static RevisedObjects classRevisedObjects;

    private static void init() {
        RevisedObjectsSearcher searcher = new RevisedObjectsSearcher(Sets.<Class<?>>newHashSet(aClass));
        revisedObjects = searcher.findAllRevisedObjects();
        revisions = revisedObjects.getRevisions(aClass);
        classRevisedObjects = revisedObjects.getClassRevisedObjects(aClass);
    }

    public static class GivenNotRevisedClassWithRevisedMethod {
        @SuppressWarnings("unchecked")
        @Before
        public void setUp() throws Exception {
            aClass = NotRevisedClassWithRevisedMethod.class;
            init();
        }

        @Test
        public void shouldFindMethodRevision() throws Exception {
            assertThat(revisions, are(new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 19)))));
        }

        @Test
        public void shouldFindMethod() throws Exception {
            RevisedMethod revisedMethod = getRevisedMethod(revisions, "revisedMethod");
            assertThat(classRevisedObjects, are(revisedMethod));
        }

        @Test
        public void revisedClass_containsRevisions() throws Exception {
            assertThat(revisedObjects, not(IS_EMPTY_REVISED_OBJECTS_COLLECTION));
        }
    }

    private static RevisedMethod getRevisedMethod(Set<RevisionWrapper> revisions, String methodName) throws NoSuchMethodException {
        return new RevisedMethod(revisions, aClass.getMethod(methodName));
    }

    public static class GivenRevisedClassWithRevisedMethod {
        @SuppressWarnings("unchecked")
        @Before
        public void setUp() throws Exception {
            aClass = RevisedClassWithRevisedMethod.class;
            init();
        }

        @Test
        public void givenRevisedClassWithRevisedMethod_shouldFindClassAndMethodRevisions() throws Exception {
            assertThat(revisions,
                    are(
                            new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 21))),
                            new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 22)))));
        }

        @Test
        public void givenRevisedClassWithRevisedMethod_shouldFindMethod() throws Exception {
            RevisedMethod revisedMethod = getRevisedMethod(
                    Sets.newHashSet(new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 22)))), "revisedMethod");
            assertThat(classRevisedObjects, contains(revisedMethod));
        }
    }

    public static class GivenRevisedClassWithNotRevisedMethod {
        @SuppressWarnings("unchecked")
        @Before
        public void setUp() throws Exception {
            aClass = RevisedClassWithNotRevisedMethod.class;
            init();
        }

        @Test
        public void givenRevisedClassWithNotRevisedMethod_shouldFindClassRevision() throws Exception {
            assertThat(revisions, are(new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 20)))));
        }

        @Test
        public void givenRevisedClassWithNotRevisedMethod_shouldNotFindMethod() throws Exception {
            RevisedMethod revisedMethod = getRevisedMethod(Sets.<RevisionWrapper>newHashSet(), "notRevisedMethod");
            assertThat(classRevisedObjects, not(contains(revisedMethod)));
        }
    }

    public static class GivenHistoryRevisedClassWithHistoryRevisedMethod {
        @SuppressWarnings("unchecked")
        @Before
        public void setUp() throws Exception {
            aClass = HistoryRevisedClassWithHistoryRevisedMethod.class;
            init();
        }

        @Test
        public void historyRevisedClassWithHistoryRevisedMethod_returnsClassAndMethodsRevisions() throws Exception {
            assertThat(revisions,
                    are(
                        new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 16))),
                        new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 17)))));
        }

        @Test
        public void givenRevisedClassWithNotRevisedMethod_shouldNotFindMethod() throws Exception {
            RevisionWrapper methodRevision = new RevisionWrapper(createDefaultRevision(createDate(2015, 4, 17)));
            RevisedMethod revisedMethod = getRevisedMethod(Sets.newHashSet(methodRevision), "historyRevisedMethod");
            assertThat(classRevisedObjects, contains(revisedMethod));
        }
    }
}