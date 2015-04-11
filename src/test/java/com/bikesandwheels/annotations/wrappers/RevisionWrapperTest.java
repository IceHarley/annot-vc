package com.bikesandwheels.annotations.wrappers;

import org.junit.*;

import static com.bikesandwheels.annotations.wrappers.WrapperUtils.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class RevisionWrapperTest {
    private RevisionWrapper revisionWrapper;

    @Test(expected = NullPointerException.class)
    public void whenRevisionIsNull_shouldThrowException() throws Exception {
        revisionWrapper = new RevisionWrapper(null);
    }

    @Test(expected = NullPointerException.class)
    public void whenRevisionWithNullDate_shouldThrowException() throws Exception {
        revisionWrapper = new RevisionWrapper(createRevision(null, "", createAuthor("John")));
    }

    @Test(expected = NullPointerException.class)
    public void whenRevisionWithNullAuthor_shouldThrowException() throws Exception {
        revisionWrapper = new RevisionWrapper(createRevision(DATE, "", createAuthor(null)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenRevisionWithInvalidDate_shouldThrowException() throws Exception {
        revisionWrapper = new RevisionWrapper(createRevision(INVALID_DATE, "", createAuthor("John")));
        assertThat(revisionWrapper.getComment(), isEmptyString());
    }

    @SuppressWarnings("NullArgumentToVariableArgMethod")
    @Test
    public void whenRevisionWithoutAuthor_shouldHaveEmptyAuthorsList() throws Exception {
        revisionWrapper = new RevisionWrapper(createRevision(DATE, "", null));
        assertThat(revisionWrapper.getAuthors(), is(empty()));
    }

    @Test
    public void givenRevisionWithComment_shouldReturnComment() throws Exception {
        revisionWrapper = new RevisionWrapper(createRevision(DATE, "comment", createAuthor("John")));
        assertThat(revisionWrapper.getComment(), is("comment"));
    }

    @Test
    public void givenRevisionWithDate_shouldReturnDate() throws Exception {
        revisionWrapper = new RevisionWrapper(createRevision(DATE, "", createAuthor("John")));
        assertDate(revisionWrapper.getDate(), YEAR, MONTH, DAY);
    }

    @Test
    public void givenRevisionWithAuthor_shouldReturnAuthor() throws Exception {
        revisionWrapper = new RevisionWrapper(createRevision(DATE, "", createAuthor("John")));
        assertThat(revisionWrapper.getAuthors().size(), is(1));
        assertThat(revisionWrapper.getAuthors(), contains("John"));
    }

    @Test
    public void givenRevisionWithAuthors_shouldReturnAuthors() throws Exception {
        revisionWrapper = new RevisionWrapper(createRevision(DATE, "", createAuthor("John"), createAuthor("Paul")));
        assertThat(revisionWrapper.getAuthors().size(), is(2));
        assertThat(revisionWrapper.getAuthors(), containsInAnyOrder("John", "Paul"));
    }
}
