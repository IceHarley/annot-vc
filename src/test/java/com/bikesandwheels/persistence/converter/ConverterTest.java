package com.bikesandwheels.persistence.converter;

import com.bikesandwheels.interactors.annotation_wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.persistence.model.Revision;
import com.google.common.collect.Sets;
import org.junit.*;

import java.lang.reflect.Method;

import static com.bikesandwheels.annotations.Revision.DEFAULT_COMMENT;
import static com.bikesandwheels.interactors.annotation_wrappers.WrapperUtils.*;
import static com.bikesandwheels.interactors.TestModel.BaseRevisedClass;
import static com.bikesandwheels.interactors.TestModel.MethodsModel.NotRevisedClassWithRevisedMethod;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ConverterTest {
    private Converter converter = new Converter();
    private Method method = null;

    @Before
    public void setUp() throws Exception {
        method = NotRevisedClassWithRevisedMethod.class.getDeclaredMethod("revisedMethod");
    }

    @Test
    public void givenNull_returnEmptySet() throws Exception {
        assertThat(converter.convert((RevisedObject) null), is(empty()));
    }

    @Test
    public void givenRevisedClassWithoutRevisions_returnEmptySet() throws Exception {
        RevisedClass revisedClass = new RevisedClass(Sets.<RevisionWrapper>newHashSet(), BaseRevisedClass.class);
        assertThat(converter.convert(revisedClass), is(empty()));
    }

    @Test
    public void givenRevisedMethodWithoutRevisions_returnEmptySet() throws Exception {
        RevisedMethod revisedMethod = new RevisedMethod(Sets.<RevisionWrapper>newHashSet(), method);
        assertThat(converter.convert(revisedMethod), is(empty()));
    }

    @Test
    public void givenRevisedClassWithOneRevision_returnOneRevision() throws Exception {
        RevisedClass revisedClass = new RevisedClass(Sets.newHashSet(DEFAULT_WRAPPED_REVISION), BaseRevisedClass.class);
        assertThat(converter.convert(revisedClass).size(), is(1));
    }

    @Test
    public void givenRevisedClassWithOneRevision_returnCorrespondingRevision() throws Exception {
        RevisedClass revisedClass = new RevisedClass(Sets.newHashSet(DEFAULT_WRAPPED_REVISION), BaseRevisedClass.class);
        Revision revisionEntity = converter.convert(revisedClass).get(0);
        assertDate(revisionEntity.getDate(), YEAR, MONTH, DAY);
        assertThat(revisionEntity.getComment(), is(DEFAULT_COMMENT));
        assertEquals(revisionEntity.getRevisedClass().getCanonicalName(), BaseRevisedClass.class.getCanonicalName());
    }

    @Test
    public void givenRevisedMethodWithOneRevision_returnCorrespondingRevision() throws Exception {
        RevisedMethod revisedMethod = new RevisedMethod(Sets.newHashSet(DEFAULT_WRAPPED_REVISION), method);
        Revision revisionEntity = converter.convert(revisedMethod).get(0);
        assertDate(revisionEntity.getDate(), YEAR, MONTH, DAY);
        assertThat(revisionEntity.getComment(), is(DEFAULT_COMMENT));
        assertEquals(revisionEntity.getRevisedMethod().getName(), method.getName());
    }

    @Test
    public void givenRevisedMethodWithTwoRevisions_returnTwoRevisions() throws Exception {
        RevisedMethod revisedMethod = new RevisedMethod(Sets.newHashSet(DEFAULT_WRAPPED_REVISION, DEFAULT_WRAPPED_REVISION2), method);
        assertThat(converter.convert(revisedMethod).size(), is(2));
    }

    @Test
    public void givenClassesRevisedObjectsMap_returnRevisions() throws Exception {
        ClassesRevisedObjectsMap map = new ClassesRevisedObjectsMap();
        RevisedObjects revisedObjects = new RevisedObjects();
        revisedObjects.add(new RevisedClass(Sets.newHashSet(DEFAULT_WRAPPED_REVISION), BaseRevisedClass.class));
        map.add(BaseRevisedClass.class, revisedObjects);

        revisedObjects = new RevisedObjects();
        revisedObjects.add(new RevisedMethod(Sets.newHashSet(DEFAULT_WRAPPED_REVISION), method));
        map.add(NotRevisedClassWithRevisedMethod.class, revisedObjects);

        assertThat(converter.convert(map).size(), is(2));
    }
}
