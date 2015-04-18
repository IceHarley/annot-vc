package com.bikesandwheels.persistence.service;

import com.bikesandwheels.config.*;
import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;
import com.bikesandwheels.persistence.repositories.*;
import com.google.common.collect.*;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@ActiveProfiles({Profiles.LIVE, Profiles.DB_IN_MEMORY})
public class RepositoryTest {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    @Autowired
    private RevisionRepository revisionRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private MethodRepository methodRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    private Revision revision;
    private Class aClass;

    @Before
    public void setUp() throws Exception {
        prepareSpringTestRunner();

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
            assertTrue(Sets.newHashSet(revisionRepository.findAll()).contains(revision));
        }

        @Test
        public void classIsLinkedToRevisionTest() throws Exception {
            assertTrue(aClass.getRevisions().contains(revision));
        }
    }

    public class MethodRevisionTest {
        private Method method;

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


    public class RevisionsAuthorsTest {
        private Author author1;
        private Author author2;
        private List<Author> authors;
        private List<Revision> revisions;
        @Before
        public void setUp() throws Exception {
            author1 = new Author();
            author1.setName("Mike");

            author2 = new Author();
            author2.setName("Jack");


            authors = Lists.newArrayList(author1, author2);
            revisions = Lists.newArrayList(revision);

            revision.setRevisedClass(aClass);
            aClass.setRevisions(revisions);

            revision.getAuthors().addAll(authors);
            author1.setRevisions(revisions);
            author2.setRevisions(revisions);

            classRepository.save(aClass);
            authorService.save(authors);
            revisionRepository.save(revision);
        }

        @Test
        public void idsGeneratedTest() throws Exception {
            assertNotNull(revision.getId());
            assertNotNull(author1.getAuthorId());
            assertNotNull(author2.getAuthorId());
        }

        @Test
        public void revisionWithSeveralAuthorsTest() throws Exception {
            assertTrue(revision.getAuthors().containsAll(authors));
        }
    }

    //This is substitution for @RunWith(SpringJUnit4ClassRunner.class)
    private void prepareSpringTestRunner() throws Exception {
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }
}
