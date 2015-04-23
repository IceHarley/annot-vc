package com.bikesandwheels.domain.annotation_wrappers;

import org.junit.Test;

import static com.bikesandwheels.domain.annotation_wrappers.WrapperUtils.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AuthorWrapperTest {
    private AuthorWrapper authorWrapper;

    @Test(expected = NullPointerException.class)
    public void whenAuthorIsNull_shouldThrowException() throws Exception {
        authorWrapper = new AuthorWrapper(null);
    }

    @Test(expected = NullPointerException.class)
    public void whenNameIsNull_shouldThrowException() throws Exception {
        authorWrapper = new AuthorWrapper(createAuthor(null));
    }

    @Test
    public void givenAuthor_shouldReturnAuthor() throws Exception {
        authorWrapper = new AuthorWrapper(createAuthor("John"));
        assertThat(authorWrapper.getName(), is("John"));
    }

}
