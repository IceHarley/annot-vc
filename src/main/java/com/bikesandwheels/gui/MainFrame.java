package com.bikesandwheels.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MainFrame implements Runnable {
    private JTextField pathTextField;
    private JFrame mainFrame;
    private JButton selectPathButton;
    private JPanel topPanel;
    private JPanel areaPanel;
    private JTable table;

    public void run() {
        mainFrame = new JFrame("Annotations Version Control");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        pathTextField = new JTextField(50);

        selectPathButton = new JButton("...");
        selectPathButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        selectPath();
                    }
                });
        //selectPathButton.setBounds(100,150,80,30);

        topPanel = new JPanel(true);
        topPanel.add(pathTextField, BorderLayout.WEST);
        topPanel.add(selectPathButton, BorderLayout.EAST);

        table = new JTable();
        table.setSize(1000, 500);
        table.setAutoscrolls(true);

        areaPanel = new JPanel(true);
        areaPanel.add(table, BorderLayout.CENTER);
        areaPanel.setSize(1000, 500);

        mainFrame.add(topPanel, BorderLayout.BEFORE_FIRST_LINE);
        mainFrame.add(areaPanel, BorderLayout.CENTER);
        mainFrame.pack();

        mainFrame.setVisible(true);
    }

    private void selectPath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showDialog(mainFrame, "Select path") == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            pathTextField.setText(path);
        }

    }
}
