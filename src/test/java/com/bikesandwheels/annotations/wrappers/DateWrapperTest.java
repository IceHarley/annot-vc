package com.bikesandwheels.annotations.wrappers;

import com.bikesandwheels.annotations.Date;
import org.junit.*;

import java.util.*;

import static com.bikesandwheels.annotations.wrappers.WrapperUtils.*;
import static org.hamcrest.Matchers.is;

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
        dateWrapper = new DateWrapper(createDate(2015, 1, 1));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateWrapper.getDate());

        Assert.assertThat(calendar.get(Calendar.YEAR), is(2015));
        Assert.assertThat(calendar.get(Calendar.MONTH), is(Calendar.JANUARY));
        Assert.assertThat(calendar.get(Calendar.DAY_OF_MONTH), is(1));
    }

    private final static Date INVALID_DATE = createDate(2015, 13, 1);
}
