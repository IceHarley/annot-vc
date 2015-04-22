package com.bikesandwheels.gui;

import com.bikesandwheels.gui.model.RevisionsTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class RevisionsTable extends JPanel {
    @Autowired
    private RevisionsTableModel tableModel;
    private JScrollPane scrollPane;

    public RevisionsTable() {
        super(new GridLayout(1, 0));
    }

    public void init() {
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
//        TableRowSorter sorter = new TableRowSorter(table.getModel());

        scrollPane = new JScrollPane(table);
        add(scrollPane);

        refresh();
    }

    public void refresh() {
        tableModel.refresh();
        scrollPane.repaint();
    }
}
