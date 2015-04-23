package com.bikesandwheels.domain;

import com.bikesandwheels.domain.annotation_wrappers.RevisionWrapper;
import com.bikesandwheels.tools.*;
import com.google.common.collect.*;

import java.util.*;

public class RevisedObjects {
    public static final RevisedObjects EMPTY = new RevisedObjects();
    private final Set<RevisedObject> items = Sets.newHashSet();

    public void add(RevisedObject item) {
        if (item.hasRevisions())
            items.add(item);
    }

    public void addAll(RevisedObjects revisedObjects) {
        items.addAll(revisedObjects.items);
    }

    public Set<RevisionWrapper> getAllRevisions() {
        Set<RevisionWrapper> allRevisions = Sets.newHashSet();
        for (RevisedObject item : items)
            allRevisions.addAll(item.getRevisions());
        return allRevisions;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean contains(RevisedObject revisedObject) {
        return items.contains(revisedObject);
    }

    public boolean containsAll(RevisedObjects that) {
        return this.items.containsAll(that.items);
    }

    public Set<RevisedObject> getAll() {
        return Sets.newHashSet(items);
    }

    @Override
    public String toString() {
        List<String> convertedItems = Lists.newArrayList();
        for (RevisedObject item : Lists.newArrayList(items))
            convertedItems.add(item.toString());
        return StringUtils.join(convertedItems);
    }
}
