package com.bikesandwheels.interactors;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.main.Config;
import com.google.common.collect.Sets;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Set;

import static com.bikesandwheels.annotations.wrappers.WrapperUtils.*;
import static com.bikesandwheels.interactors.TestModel.MethodsModel.*;
import static com.bikesandwheels.tools.TestUtils.*;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

@RunWith(HierarchicalContextRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {Config.class})
public class RevisedObjectsSearcherForMethodsTest {
    @Autowired
    private RevisedSearcher searcher;

    private ClassesRevisedObjectsMap revisedObjects;
    private Class<?> aClass;
    private Set<RevisionWrapper> revisions;
    private RevisedObjects classRevisedObjects;

    @Before
    public void setUp() throws Exception {
        prepareSpringTestRunner();
    }

    private void init() {
        searcher.setClasses(Sets.<Class<?>>newHashSet(aClass));
        revisedObjects = searcher.findAllRevisedObjects();
        revisions = revisedObjects.getRevisions(aClass);
        classRevisedObjects = revisedObjects.getClassRevisedObjects(aClass);
    }

    public class GivenNotRevisedClassWithRevisedMethod {
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

    private RevisedMethod getRevisedMethod(Set<RevisionWrapper> revisions, String methodName) throws NoSuchMethodException {
        return new RevisedMethod(revisions, aClass.getMethod(methodName));
    }

    public class GivenRevisedClassWithRevisedMethod {
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

    public class GivenRevisedClassWithNotRevisedMethod {
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

    public class GivenHistoryRevisedClassWithHistoryRevisedMethod {
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

    //This is substitution for @RunWith(SpringJUnit4ClassRunner.class)
    private void prepareSpringTestRunner() throws Exception {
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }
}