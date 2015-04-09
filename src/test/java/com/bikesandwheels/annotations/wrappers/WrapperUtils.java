package com.bikesandwheels.annotations.wrappers;

import com.bikesandwheels.annotations.*;
import com.bikesandwheels.annotations.Date;
import org.junit.Assert;

import java.lang.annotation.Annotation;
import java.util.*;

import static org.hamcrest.Matchers.is;

public class WrapperUtils {
    static final int YEAR = 2015;
    static final int MONTH = 1;
    static final int INVALID_MONTH = 13;
    static final int DAY = 1;
    static final Date DATE = createDate(YEAR, MONTH, DAY);
    static final Date INVALID_DATE = createDate(YEAR, INVALID_MONTH, DAY);

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

    static Revision createRevision(final Date date, final String comment, final Author... author) {
        return new Revision() {
            public Class<? extends Annotation> annotationType() {
                return Revision.class;
            }

            public Date date() {
                return date;
            }

            public Author[] authors() {
                return author;
            }

            public String comment() {
                return comment;
            }
        };
    }

    static History createHistory(final Revision... revisions) {
        return new History() {
            public Revision[] value() {
                return revisions;
            }

            public Class<? extends Annotation> annotationType() {
                return History.class;
            }
        };
    }

    static void assertDate(java.util.Date date, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Assert.assertThat(calendar.get(Calendar.YEAR), is(year));
        Assert.assertThat(calendar.get(Calendar.MONTH), is(month - 1)); //zero-based month
        Assert.assertThat(calendar.get(Calendar.DAY_OF_MONTH), is(day));
    }
}
