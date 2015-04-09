package com.bikesandwheels;

import com.bikesandwheels.emptymodel.EmptyTestModel;
import org.hamcrest.*;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.*;

import java.util.*;

import static com.bikesandwheels.emptymodel.EmptyTestModel.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class ReflectionsTest {
    static Reflections reflections;

    @BeforeClass
    public static void setUp() throws Exception {
        reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(Collections.singletonList(ClasspathHelper.forClass(EmptyTestModel.class)))
                .setScanners(
                        new SubTypesScanner(false),
                        new TypeAnnotationsScanner(),
                        new MethodAnnotationsScanner()));
    }

    @SuppressWarnings("unchecked")
    public static class getSubTypesTest {

        @Test
        public void givenTypeWithNoDerivatives_ReturnsEmptyList() throws Exception {
            assertTrue(reflections.getSubTypesOf(C1.class).isEmpty());
        }

        @Test
        public void givenTypeWithDerivatives_ReturnsDerivatives() throws Exception {
            assertThat(reflections.getSubTypesOf(C3.class), are(C4.class, C5.class, C6.class, C7.class));
        }

        @Test
        public void givenInterface_ReturnsImplementersAndTheirDerivatives() throws Exception {
            assertThat(reflections.getSubTypesOf(I1.class), are(C4.class, C6.class, C7.class));
        }

        private abstract static class Match<T> extends BaseMatcher<T> {
            public void describeTo(Description description) { }
        }

        public static <T> Matcher<Set<? super T>> are(final T... ts) {
            final Collection<?> c1 = Arrays.asList(ts);
            return new Match<Set<? super T>>() {
                public boolean matches(Object o) {
                    Collection<?> c2 = (Collection<?>) o;
                    return c1.containsAll(c2) && c2.containsAll(c1);
                }
            };
        }
    }


}
