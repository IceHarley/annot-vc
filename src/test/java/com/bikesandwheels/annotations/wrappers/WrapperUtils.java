package com.bikesandwheels.annotations.wrappers;

import com.bikesandwheels.annotations.*;

import java.lang.annotation.Annotation;

public class WrapperUtils {
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
}
