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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@ActiveProfiles({Profiles.LIVE, Profiles.DB_IN_MEMORY})
public class MethodServiceTest {
    @Autowired
    private MethodService methodService;

    private Method method;

    @Before
    public void setUp() throws Exception {
        method = new Method();
        method.setName("com.test.TestClass");
        method.setSignature("Integer i, String s");
    }

    @Test
    public void saveTest() throws Exception {
        methodService.save(method);
        assertNotNull(method.getMethodId());
        assertTrue(methodService.getAll().contains(method));
    }

    @Test
    public void mergeClassesWithSameNameTest() throws Exception {
        methodService.save(method);
        methodService.save(method);
        assertThat(methodService.getAll().size(), is(1));
    }
}
