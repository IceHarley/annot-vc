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

import javax.persistence.PersistenceException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@ActiveProfiles({Profiles.LIVE, Profiles.DB_IN_MEMORY})
public class MethodServiceTest {
    @Autowired
    private MethodService methodService;

    private Method method;
    private Class aClass;

    @Before
    public void setUp() throws Exception {
        aClass = new Class();
        aClass.setCanonicalName("com.test.TestClass");

        method = new Method();
        method.setName("com.test.TestClass.testMethod");
        method.setSignature("Integer i, String s");
        method.setDeclaringClass(aClass);
    }

    @After
    public void tearDown() throws Exception {
        methodService.deleteAll();
    }

    @Test(expected = PersistenceException.class)
    public void methodWithoutNameTest() throws Exception {
        method.setName(null);
        methodService.save(method);
    }

    @Test(expected = Exception.class)
    public void methodWithoutDeclaringClassTest() throws Exception {
        method.setDeclaringClass(null);
        methodService.save(method);
    }

    @Test
    public void saveTest() throws Exception {
        methodService.save(method);
        assertNotNull(method.getMethodId());
        assertTrue(methodService.getAll().contains(method));
    }

    @Test
    public void mergeMethodsWithSameNameTest() throws Exception {
        methodService.save(method);
        methodService.save(method);
        assertThat(methodService.getAll().size(), is(1));
    }

    @Test
    public void declaringClassIsSavedTest() throws Exception {
        methodService.save(method);
        assertNotNull(method.getDeclaringClass().getClassId());
    }
}
