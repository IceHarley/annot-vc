package com.bikesandwheels.persistence.dao;

import com.bikesandwheels.config.AppConfig;
import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;
import com.google.common.collect.Lists;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@ActiveProfiles({"live", "db-in-memory"})
public class RevisionRepositoryTest {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    @Autowired
    RevisionRepository revisionRepository;
    @Autowired
    ClassRepository classRepository;
    @Autowired
    MethodRepository methodRepository;

    Revision revision;
    Class aClass;
    Method method;
//    Author author;

    @Before
    public void setUp() throws Exception {
        prepareSpringTestRunner();

//        author = new Author();
//        author.setName("Mike");

        aClass = new Class();
        aClass.setCanonicalName("com.test.RevisedClass");

        revision = new Revision();
        revision.setComment("comment");
        revision.setDate(new SimpleDateFormat(DATE_FORMAT).parse(String.format("%d.%d.%d", 1, 1, 2015)));
    }

    public class ClassRevisionTest {
        @Before
        public void setUp() throws Exception {
            revision.setRevisedClass(aClass);
            aClass.setRevisions(Lists.newArrayList(revision));

            classRepository.save(aClass);
            revisionRepository.save(revision);
        }

        @Test
        public void idsGeneratedTest() throws Exception {
            assertNotNull(revision.getId());
            assertNotNull(aClass.getClassId());
        }

        @Test
        public void revisionPersistTest() throws Exception {
            assertTrue(Lists.newArrayList(revisionRepository.findAll()).contains(revision));
        }

        @Test
        public void classIsLinkedToRevisionTest() throws Exception {
            assertTrue(aClass.getRevisions().contains(revision));
        }
    }

    public class MethodRevisionTest {
        @Before
        public void setUp() throws Exception {
            method = new Method();
            method.setName("revisedMethod");
            method.setSignature("Integer i, String s");
            method.setDeclaringClass(aClass);

            revision.setRevisedMethod(method);
            method.setRevisions(Lists.newArrayList(revision));

            classRepository.save(aClass);
            methodRepository.save(method);
            revisionRepository.save(revision);
        }

        @Test
        public void idsGeneratedTest() throws Exception {
            assertNotNull(revision.getId());
            assertNotNull(aClass.getClassId());
            assertNotNull(method.getMethodId());
        }

        @Test
        public void revisionPersistTest() throws Exception {
            assertTrue(Lists.newArrayList(revisionRepository.findAll()).contains(revision));
        }

        @Test
        public void methodIsLinkedToRevisionTest() throws Exception {
            assertTrue(method.getRevisions().contains(revision));
        }

        @Test
        public void classIsNotLinkedToRevisionTest() throws Exception {
            assertFalse(aClass.getRevisions().contains(revision));
        }
    }

    //This is substitution for @RunWith(SpringJUnit4ClassRunner.class)
    private void prepareSpringTestRunner() throws Exception {
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }
}
