package com.bikesandwheels.gui;

import com.bikesandwheels.gui.model.RevisionsTableModel;
import com.bikesandwheels.persistence.service.RevisionService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;

@Component
public class RevisionsTable extends JPanel {
    @Autowired
    private RevisionsTableModel tableModel;

    public RevisionsTable() {
        super(new GridLayout(1, 0));
    }

    public void init() {
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        TableRowSorter sorter = new TableRowSorter(table.getModel());

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);

        refresh();
    }

    public void refresh() {
        tableModel.refresh();
    }

}
