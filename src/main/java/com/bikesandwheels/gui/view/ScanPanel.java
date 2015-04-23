package com.bikesandwheels.gui.view;

import com.bikesandwheels.gui.controller.ScanController;
import com.bikesandwheels.gui.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ScanPanel extends JPanel {
    @Autowired
    private ScanModel model;
    @Autowired
    private ScanController controller;

    private JTextField pathTextField;
    private JButton selectPathButton;
    private JButton scanButton;
    private JPanel pathPanel;
    private JLabel pathHeader;
    private JLabel pathFooter;

    public ScanPanel() {
        super(true);
    }

    public void init() {
        initHeader();
        initPathPanel();
        initFooter();

        initLayout();

        addListeners();
        updateSelectedFile();
    }

    private void initLayout() {
        setLayout(new BorderLayout());
        add(pathHeader, BorderLayout.NORTH);
        add(pathPanel, BorderLayout.CENTER);
        add(pathFooter, BorderLayout.SOUTH);
    }

    private void initFooter() {
        pathFooter = new JLabel(" ");
    }

    private void initHeader() {
        pathHeader = new JLabel("Select assembly:");
        pathHeader.setPreferredSize(new Dimension(0, 30));
    }

    private void initPathPanel() {
        initPathTextField();
        scanButton = new JButton("Scan");
        pathPanel = new JPanel(true);
        pathPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pathPanel.add(pathTextField);
        pathPanel.add(scanButton);
    }

    private void initPathTextField() {
        selectPathButton = new JButton("...");
        pathTextField = new JTextField(40);
        pathTextField.setLayout(new BorderLayout());
        pathTextField.add(selectPathButton, BorderLayout.EAST);
    }

    private void addListeners() {
        addSelectPathButtonListener();
        addScanButtonListener();
        addTableModelListener();
    }

    private void addSelectPathButtonListener() {
        selectPathButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        controller.selectPath();
                    }
                });
    }

    private void addScanButtonListener() {
        scanButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        controller.scan();
                    }
                });
    }

    private void addTableModelListener() {
        model.addTableModelListener(new ScanModelListener() {
            @Override
            public void selectedPathChanged() {
                updateSelectedFile();
            }
        });
    }

    private void updateSelectedFile() {
        File selectedFile = model.getSelectedFile();
        String path = model.getDefaultSelectedPath();
        if (selectedFile != null)
            path = selectedFile.getPath();
        pathTextField.setText(path);
    }
}
