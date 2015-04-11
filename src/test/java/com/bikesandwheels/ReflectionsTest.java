package com.bikesandwheels;

import com.bikesandwheels.interactors.*;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.*;

import java.util.*;

import static com.bikesandwheels.TestUtils.areDerivatives;
import static com.bikesandwheels.interactors.EmptyTestModel.*;
import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
@RunWith(Enclosed.class)
public class ReflectionsTest {
    private static Reflections reflections;

    public static class getSubTypesTestWithEmptyModel {
        @BeforeClass
        public static void setUp() throws Exception {
            reflections = new Reflections(new ConfigurationBuilder()
                    .setUrls(Collections.singletonList(ClasspathHelper.forClass(EmptyTestModel.class)))
                    .setScanners(
                            new SubTypesScanner(true),
                            new TypeAnnotationsScanner(),
                            new MethodAnnotationsScanner()));
        }

        @Test
        public void givenTypeWithNoDerivatives_ReturnsEmptyList() throws Exception {
            assertTrue(reflections.getSubTypesOf(C1.class).isEmpty());
        }

        @Test
        public void givenTypeWithDerivatives_ReturnsDerivatives() throws Exception {
            assertThat(reflections.getSubTypesOf(C3.class), areDerivatives(C4.class, C5.class, C6.class, C7.class));
        }

        @Test
        public void givenInterface_ReturnsImplementersAndTheirDerivatives() throws Exception {
            assertThat(reflections.getSubTypesOf(I1.class), areDerivatives(C4.class, C6.class, C7.class));
        }
    }
}
