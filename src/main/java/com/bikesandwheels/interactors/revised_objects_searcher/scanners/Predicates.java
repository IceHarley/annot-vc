package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.*;
import com.google.common.base.Predicate;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;

public enum Predicates implements Predicate<Annotation> {

    REVISION_PREDICATE  {
        public boolean apply(@Nullable Annotation annotation) {
            return annotation instanceof Revision;
        }
    },

    HISTORY_PREDICATE {
        public boolean apply(@Nullable Annotation annotation) {
            return annotation instanceof History;
        }
    }
}
