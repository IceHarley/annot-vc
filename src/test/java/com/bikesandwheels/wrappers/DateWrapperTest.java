package com.bikesandwheels.wrappers;

import com.bikesandwheels.annotations.Date;
import com.bikesandwheels.annotations.wrappers.DateWrapper;
import org.junit.*;

import java.lang.annotation.Annotation;
import java.util.*;

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

    static Date createDate(final int year, final int month, final int day) {
        return new Date() {
            public Class<? extends Annotation> annotationType() {
                return Date.class;
            }

            public int year() {
                return year;
            }

            public int month() {
                return month;
            }

            public int day() {
                return day;
            }
        };
    }
}
