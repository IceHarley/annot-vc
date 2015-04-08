package com.bikesandwheels.annotations.wrappers;

import com.bikesandwheels.annotations.*;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AuthorWrapperTest {
    private AuthorWrapper authorWrapper;

    @Test(expected = NullPointerException.class)
    public void whenDateIsNull_shouldThrowException() throws Exception {
        authorWrapper = new AuthorWrapper(null);
    }

    @Test
    public void givenAuthor_shouldReturnAuthor() throws Exception {
        authorWrapper = new AuthorWrapper(createAuthor("John"));
        assertThat(authorWrapper.getName(), is("John"));
    }

    static Author createAuthor(final String name) {
        return new Author() {
            public String value() {
                return name;
            }

            public Class<? extends Annotation> annotationType() {
                return Author.class;
            }

        };
    }
}
