package com.bikesandwheels.domain.annotation_wrappers;

import org.junit.*;

import static com.bikesandwheels.domain.annotation_wrappers.WrapperUtils.*;

public class DateWrapperTest {
    private DateWrapper dateWrapper;

    @Test(expected = NullPointerException.class)
    public void whenDateIsNull_shouldThrowException() throws Exception {
        dateWrapper = new DateWrapper(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidDate_shouldThrowException() throws Exception {
        dateWrapper = new DateWrapper(INVALID_DATE);
    }

    @Test
    public void givenValidDate_shouldReturnValidDate() throws Exception {
        dateWrapper = new DateWrapper(DATE);

        assertDate(dateWrapper.getDate(), 2015, 1, 1);
    }
}
