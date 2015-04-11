package com.bikesandwheels.interactors;

import com.bikesandwheels.annotations.*;

@SuppressWarnings({"ALL"})
public interface TestModel {
    class NotRevisedClass {}

    @Revision(date = @Date(year = 2015, month = 4, day = 1))
    class BaseRevisedClass {}

    @Revision(date = @Date(year = 2015, month = 4, day = 9))
    class DerivedRevisedClass extends BaseRevisedClass {}
    class DerivedNotRevisedClass extends BaseRevisedClass {}
    @History({
        @Revision(date = @Date(year = 2015, month = 4, day = 6)),
        @Revision(date = @Date(year = 2015, month = 4, day = 7))
    })
    class DerivedHistoryRevisedClass extends BaseRevisedClass {}

    @Revision(
            date = @Date(year = 2015, month = 4, day = 2),
            comment = "revision comment"
    )
    class CommentRevisedClass {}

    @Revision(
            date = @Date(year = 2015, month = 4, day = 3),
            authors = {@Author("class_author")}
    )
    class AuthorRevisedClass {}

    @Revision(
            date = @Date(year = 2015, month = 4, day = 4),
            authors = {@Author("class_author1"), @Author("class_author2")}
    )
    class AuthorsRevisedClass {}

    @Revision(
            date = @Date(year = 2015, month = 4, day = 5),
            comment = "revision comment",
            authors = {@Author("class_author1"), @Author("class_author2")}
    )
    class CommentAndAuthorsRevisedClass {}

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
    class HistoryRevisedClass {}

    @History
    class EmptyHistoryRevisedClass {};

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
            @Revision(date = @Date(year = 2015, month = 4, day = 19))
            public void revisedMethod() {}
        }

        @Revision(date = @Date(year = 2015, month = 4, day = 20))
        class RevisedClassWithNotRevisedMethod {
            public void notRevisedMethod() {}
        }

        @Revision(date = @Date(year = 2015, month = 4, day = 21))
        class RevisedClassWithRevisedMethod {
            @Revision(date = @Date(year = 2015, month = 4, day = 22))
            public void revisedMethod() {}
        }
    }
}
