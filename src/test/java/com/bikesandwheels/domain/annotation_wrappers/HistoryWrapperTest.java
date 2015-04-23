package com.bikesandwheels.domain.annotation_wrappers;

import com.bikesandwheels.annotations.Revision;
import org.junit.*;

import static com.bikesandwheels.domain.annotation_wrappers.WrapperUtils.*;
import static org.hamcrest.Matchers.*;

public class HistoryWrapperTest {
    private HistoryWrapper historyWrapper;

    @Test(expected = NullPointerException.class)
    public void whenHistoryIsNull_shouldThrowException() throws Exception {
        historyWrapper = new HistoryWrapper(null);
    }

    @SuppressWarnings("NullArgumentToVariableArgMethod")
    @Test
    public void givenHistoryWithNoRevisions_shouldHaveEmptyRevisionList() throws Exception {
        historyWrapper = new HistoryWrapper(createEmptyHistory());
        Assert.assertThat(historyWrapper.getRevisions(), is(empty()));
    }

    @Test
    public void givenHistoryWithRevision_shouldReturnRevision() throws Exception {
        Revision revision = createRevision(createDate(YEAR, MONTH, DAY), "", createAuthor("John"));
        historyWrapper = new HistoryWrapper(createHistory(revision));
        Assert.assertThat(historyWrapper.getRevisions().size(), is(1));
        Assert.assertThat(historyWrapper.getRevisions(), contains(new RevisionWrapper(revision)));
    }
}
