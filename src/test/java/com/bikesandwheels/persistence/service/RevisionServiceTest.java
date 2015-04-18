package com.bikesandwheels.persistence.service;

import com.bikesandwheels.config.*;
import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;
import com.google.common.collect.Sets;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.persistence.PersistenceException;
import java.text.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@ActiveProfiles({Profiles.LIVE, Profiles.DB_IN_MEMORY})
public class RevisionServiceTest {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    @Autowired
    private RevisionService revisionService;

    private Revision revisionForClass;
    private Revision revisionForMethod;
    private Class aClass;
    private Method method;
    private Author author1;
    private Author author2;

    @Before
    public void setUp() throws Exception {
        aClass = new Class();
        aClass.setCanonicalName("com.test.TestClass");

        method = new Method();
        method.setName("revisedMethod");
        method.setSignature("Integer i, String s");
        method.setDeclaringClass(aClass);

        author1 = new Author();
        author1.setName("John");
        author2 = new Author();
        author2.setName("Jack");

        revisionForClass = createRevision();
        revisionForClass.setRevisedClass(aClass);

        revisionForMethod = createRevision();
        revisionForMethod.setRevisedMethod(method);
    }

    private Revision createRevision() throws ParseException {
        Revision revisionClass = new Revision();
        revisionClass.setDate(new SimpleDateFormat(DATE_FORMAT).parse(String.format("%d.%d.%d", 1, 1, 2015)));
        revisionClass.getAuthors().add(author1);
        revisionClass.getAuthors().add(author2);
        revisionClass.setComment("Revision comment");
        return revisionClass;
    }

    @After
    public void tearDown() throws Exception {
        revisionService.deleteAll();
    }

    @Test(expected = PersistenceException.class)
    public void revisionWithoutDateTest() throws Exception {
        revisionForClass.setDate(null);
        revisionService.save(revisionForClass);
    }

    @Test
    public void saveTest() throws Exception {
        revisionService.save(revisionForClass);
        assertNotNull(revisionForClass.getId());
        assertTrue(revisionService.getAll().contains(revisionForClass));
    }

    @Test
    public void mergeSameRevisionsTest() throws Exception {
        revisionService.save(revisionForClass);
        revisionService.save(revisionForClass);
        assertThat(revisionService.getAll().size(), is(1));
    }

    @Test
    public void mergeSameRevisionsInCollectionTest() throws Exception {
        Revision revisionClass2 = createRevision();
        revisionClass2.setRevisedClass(aClass);

        revisionService.save(Sets.newHashSet(revisionForClass, revisionClass2));
        assertThat(revisionService.getAll().size(), is(1));
    }

    @Test
    public void idsGeneratedTest() throws Exception {
        revisionService.save(revisionForClass);
        assertNotNull(revisionForClass.getId());
        assertNotNull(aClass.getClassId());
    }

    @Test
    public void revisionPersistTest() throws Exception {
        revisionService.save(revisionForClass);
        assertTrue(revisionService.getAll().contains(revisionForClass));
    }

    @Test
    public void classIsLinkedToRevisionTest() throws Exception {
        revisionService.save(revisionForClass);
        assertTrue(aClass.getRevisions().contains(revisionForClass));
    }

    @Test
    public void methodIsLinkedToRevisionTest() throws Exception {
        revisionService.save(revisionForMethod);
        assertTrue(method.getRevisions().contains(revisionForMethod));
    }

    @Test
    public void classIsNotLinkedToRevisionForMethodTest() throws Exception {
        revisionService.save(revisionForMethod);
        assertFalse(aClass.getRevisions().contains(revisionForMethod));
    }

    @Test
    public void methodIsNotLinkedToRevisionForClassTest() throws Exception {
        revisionService.save(revisionForClass);
        assertFalse(method.getRevisions().contains(revisionForClass));
    }

    @Test
    public void revisionWithSeveralAuthorsTest() throws Exception {
        revisionService.save(revisionForClass);
        assertTrue(revisionForClass.getAuthors().contains(author1));
        assertTrue(revisionForClass.getAuthors().contains(author2));
    }
}
