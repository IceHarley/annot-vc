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
    enum Column {
        DATE(0, "Date"),
        AUTHORS(1, "Authors"),
        CLASS(2, "Class"),
        METHOD(3, "Method"),
        COMMENT(4, "Comment");

        private int index;
        private String title;

        Column(int index, String title) {
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

    @Autowired
    private RevisionService revisionService;
    List<Revision> data = Lists.newArrayList();

    public void refresh() {
        data = Lists.newArrayList(revisionService.getAll());
        fireTableDataChanged();
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
        return String.format("%s %s(%s)",
                revisedMethod.getReturnType(),
                revisedMethod.getName(),
                revisedMethod.getSignature());
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
        java.lang.Class<?> aClass = Object.class;
        if (!data.isEmpty())
            aClass = getValueAt(0, c).getClass();
        return aClass;
    }
}
