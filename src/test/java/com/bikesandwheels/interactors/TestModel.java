package com.bikesandwheels.interactors;

import com.bikesandwheels.annotations.*;

@SuppressWarnings({"ALL"})
public interface TestModel {
    class NotAnnotatedClass {}

    @Revision(date = @Date(year = 2015, month = 4, day = 1))
    class RevisedClass {}

    @Revision(date = @Date(year = 2015, month = 4, day = 9))
    class DerivedRevisedClass extends RevisedClass {}
    class DerivedNotRevisedClass extends RevisedClass {}
    @History({
        @Revision(date = @Date(year = 2015, month = 4, day = 6)),
        @Revision(date = @Date(year = 2015, month = 4, day = 7))
    })
    class DerivedHistoryRevisedClass extends RevisedClass {}

    @Revision(
            date = @Date(year = 2015, month = 4, day = 2),
            comment = "revision comment"
    )
    class RevisionWithCommentAnnotatedClass {}

    @Revision(
            date = @Date(year = 2015, month = 4, day = 3),
            authors = {@Author("class_author")}
    )
    class RevisionWithAuthorAnnotatedClass {}

    @Revision(
            date = @Date(year = 2015, month = 4, day = 4),
            authors = {@Author("class_author1"), @Author("class_author2")}
    )
    class RevisionWithAuthorsAnnotatedClass {}

    @Revision(
            date = @Date(year = 2015, month = 4, day = 5),
            comment = "revision comment",
            authors = {@Author("class_author1"), @Author("class_author2")}
    )
    class RevisionWithCommentAndAuthorsAnnotatedClass {}

    @History({
        @Revision(
                date = @Date(year = 2015, month = 4, day = 6),
                comment = "revision 1",
                authors = {@Author("class_author1"), @Author("class_author2")}
        ),
        @Revision(
                date = @Date(year = 2015, month = 4, day = 7),
                comment = "revision 2",
                authors = {@Author("class_author2"), @Author("class_author3")}
        )
    })
    class RevisionsHistoryAnnotatedClass {}

    @History
    class EmptyHistoryAnnotatedClass{};

    @History
    @Revision(date = @Date(year = 2015, month = 4, day = 1))
    class EmptyHistoryAndRevisionAnnotatedClass{};

    public interface MethodsModel {
        public class notRevisedClass {
            public void notRevisedMethod() {}
        }

        @Revision(date = @Date(year = 2015, month = 4, day = 1))
        public void revisionAnnotatedMethod();

        @Revision(
                date = @Date(year = 2015, month = 4, day = 2),
                comment = "revision comment"
        )
        public void revisionWithCommentAnnotatedMethod();

        @Revision(
                date = @Date(year = 2015, month = 4, day = 3),
                authors = {@Author("class_author")}
        )
        public void revisionWithAuthorAnnotatedMethod();

        @Revision(
                date = @Date(year = 2015, month = 4, day = 4),
                authors = {@Author("class_author1"), @Author("class_author2")}
        )
        public void revisionWithAuthorsAnnotatedMethod();

        @Revision(
                date = @Date(year = 2015, month = 4, day = 5),
                comment = "revision comment",
                authors = {@Author("class_author1"), @Author("class_author2")}
        )
        public void revisionWithCommentAndAuthorsAnnotatedMethod();

        @History({
                @Revision(
                        date = @Date(year = 2015, month = 4, day = 6),
                        comment = "revision 1",
                        authors = {@Author("class_author1"), @Author("class_author2")}
                ),
                @Revision(
                        date = @Date(year = 2015, month = 4, day = 7),
                        comment = "revision 2",
                        authors = {@Author("class_author2"), @Author("class_author3")}
                )
        })
        public void revisionsHistoryAnnotatedMethod();

        class NotRevisedClassWithRevisedMethod {
            @Revision(date = @Date(year = 2015, month = 4, day = 1))
            public void revisedMethod() {}
        }

        @Revision(date = @Date(year = 2015, month = 4, day = 1))
        class RevisedClassWithNotRevisedMethod {
            public void notRevisedMethod() {}
        }
    }
}
