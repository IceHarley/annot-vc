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
    class EmptyHistoryRevisedClass {}

    @History
    @Revision(date = @Date(year = 2015, month = 4, day = 1))
    class EmptyHistoryAndRevisionAnnotatedClass{}

    public interface MethodsModel {
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

        @History({@Revision(date = @Date(year = 2015, month = 4, day = 16))})
        class HistoryRevisedClassWithHistoryRevisedMethod {
            @History({@Revision(date = @Date(year = 2015, month = 4, day = 17))})
            public void historyRevisedMethod() {};
        }
    }
}
