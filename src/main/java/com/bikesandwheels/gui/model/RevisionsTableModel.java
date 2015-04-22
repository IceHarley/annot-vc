package com.bikesandwheels.gui.model;

import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;
import com.bikesandwheels.persistence.service.*;
import com.bikesandwheels.tools.StringUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.table.AbstractTableModel;
import java.util.*;

public class RevisionsTableModel extends AbstractTableModel {
    @Autowired
    private RevisionService revisionService;
    @Autowired
    private AuthorService authorService;
    private Set<Revision> revisions;

    public void refresh() {
        revisions = revisionService.getAll();
        data = Lists.newArrayList(revisions);
    }

    private enum Column {
        DATE(0, "Date"),
        AUTHORS(1, "Authors"),
        CLASS(2, "Class"),
        METHOD(3, "Method"),
        COMMENT(4, "Comment");

        private int index;
        private String title;

        private Column(int index, String title) {
            this.index = index;
            this.title = title;
        }

        static Column getByIndex(int index){
            for (Column col : Column.values())
                if (col.index == index)
                    return col;
            return null;
        }
    }

    List<Revision> data = Lists.newArrayList();

    public RevisionsTableModel() {
        Class aClass = new Class();
        aClass.setCanonicalName("com.bikesandwheels.RevisedClass");

        Author author1 = new Author();
        author1.setName("John");

        Author author2 = new Author();
        author2.setName("Jack");

        Revision r1 = new Revision();
        r1.setId(1L);
        r1.setComment("revision 1");
        r1.setDate(Calendar.getInstance().getTime());
        r1.setRevisedClass(aClass);
        r1.setAuthors(Lists.newArrayList(author1));
        this.data.add(0, r1);

        Revision r2 = new Revision();
        r2.setId(1L);
        r2.setComment("revision 2");
        r2.setDate(Calendar.getInstance().getTime());
        r2.setRevisedClass(aClass);
        r2.setAuthors(Lists.newArrayList(author1, author2));
        this.data.add(1, r2);
    }

    public void setData(List<Revision> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return Column.values().length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return Column.getByIndex(col).title;
    }

    public Object getValueAt(int row, int col) {
        Revision revision = data.get(row);
        Column column = Column.getByIndex(col);
        switch (column) {
            case DATE: return revision.getDate();
            case AUTHORS: return getAuthors(revision);
            case CLASS: return getClass(revision);
            case METHOD: return getName(revision);
            case COMMENT: return revision.getComment();
            default: return null;
        }
    }

    private String getName(Revision revision) {
        Method revisedMethod = revision.getRevisedMethod();
        if (revisedMethod == null)
            return "";
        return String.format("%s(%s)", revisedMethod.getName(), revisedMethod.getSignature());
    }

    private String getClass(Revision revision) {
        Class revisedClass = revision.getRevisedClass();
        if (revisedClass == null) {
            revisedClass = revision.getRevisedMethod().getDeclaringClass();
        }
        return revisedClass.getCanonicalName();
    }

    private String getAuthors(Revision revision) {
        List<String> names = Lists.newArrayList();
        for (Author author : revision.getAuthors())
            names.add(author.getName());
        return StringUtils.join(names, ", ");
    }

    public java.lang.Class getColumnClass(int c) {
        java.lang.Class<?> aClass = getValueAt(0, c).getClass();
        return aClass;
    }
}
