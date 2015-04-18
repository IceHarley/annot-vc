package com.bikesandwheels.persistence.service;

import com.bikesandwheels.config.*;
import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@ActiveProfiles({Profiles.LIVE, Profiles.DB_IN_MEMORY})
public class RevisionServiceTest {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    @Autowired
    private RevisionService revisionService;

    private Revision revision;

    @Before
    public void setUp() throws Exception {
        Class aClass = new Class();
        aClass.setCanonicalName("com.test.TestClass");

        Method method = new Method();
        method.setName("revisedMethod");
        method.setSignature("Integer i, String s");
        method.setDeclaringClass(aClass);

        revision = new Revision();
        revision.setRevisedClass(aClass);
        revision.setRevisedMethod(method);
        revision.setDate(new SimpleDateFormat(DATE_FORMAT).parse(String.format("%d.%d.%d", 1, 1, 2015)));
    }

    @Test
    public void saveTest() throws Exception {
        revisionService.save(revision);
        assertNotNull(revision.getId());
        assertTrue(revisionService.getAll().contains(revision));
    }

    @Test
    public void mergeClassesWithSameNameTest() throws Exception {
        revisionService.save(revision);
        revisionService.save(revision);
        assertThat(revisionService.getAll().size(), is(1));
    }
}
