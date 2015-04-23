package com.bikesandwheels.gui.view;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

@Component
public class RevisionsTable extends JPanel {
    @Autowired
    private TableModel tableModel;
    private JScrollPane scrollPane;
    private JTable table;

    public RevisionsTable() {
        super(new BorderLayout());
    }

    public void init() {
        addListener();
        initTable();
        initPanel();

        initScrollPane();
        setColumnsWidth(1000, 10, 10, 40, 30, 10);
    }

    private void initTable() {
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 0));
        setSorting();
    }

    private void initPanel() {
        JLabel tableTitle = new JLabel("Revisions log:");
        tableTitle.setPreferredSize(new Dimension(0, 30));
        add(tableTitle, BorderLayout.NORTH);
        initScrollPane();
    }

    private void initScrollPane() {
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void setSorting() {
        table.setAutoCreateRowSorter(true);
        TableRowSorter sorter = new TableRowSorter(table.getModel());
        table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = Lists.newArrayList(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }

    private void addListener() {
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tableModelEvent) {
                refresh();
            }
        });
    }

    public void refresh() {
        scrollPane.repaint();
    }

    public void setColumnsWidth(int tablePreferredWidth, double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++)
            total += percentages[i];

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
        }
    }
}
