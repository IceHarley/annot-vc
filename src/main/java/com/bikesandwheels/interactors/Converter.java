package com.bikesandwheels.interactors;

import com.bikesandwheels.domain.*;
import com.bikesandwheels.persistence.model.Revision;

import java.util.*;

public interface Converter {
    List<Revision> convert(ClassesRevisedObjectsMap revisedObjectsMap);

    Collection<? extends Revision> convert(RevisedObjects revisedObjects);

    List<Revision> convert(RevisedObject revisedObject);
}
