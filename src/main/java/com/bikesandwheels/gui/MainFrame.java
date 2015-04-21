package com.bikesandwheels.gui;

import com.bikesandwheels.interactors.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Paths;

public class MainFrame implements Runnable {
    private final String caption;
    private JTextField pathTextField;
    private JFrame mainFrame;
    private JButton selectPathButton;
    private JButton scanButton;
    private JPanel topPanel;
    private JPanel topLeftPanel;
    private JPanel topRightPanel;
    private JPanel areaPanel;
    private JTable table;
    private File selectedFile;

    public MainFrame(String caption) {
        this.caption = caption;
    }

    public void run() {
        selectedFile = Paths.get("").toFile();

        mainFrame = new JFrame(caption);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pathTextField = new JTextField(50);
        pathTextField.setText(selectedFile.getPath());

        selectPathButton = new JButton("...");
        selectPathButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        selectPath();
                    }
                });

        topLeftPanel = new JPanel(true);
        topLeftPanel.add(pathTextField, BorderLayout.WEST);
        topLeftPanel.add(selectPathButton, BorderLayout.EAST);

        scanButton = new JButton("Scan");
        scanButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        scan();
                    }
                });

        topRightPanel = new JPanel(true);
        topRightPanel.add(scanButton, BorderLayout.CENTER);

        topPanel = new JPanel(true);
        topPanel.add(topLeftPanel, BorderLayout.WEST);
        topPanel.add(topRightPanel, BorderLayout.EAST);


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

    private void scan() {
        new Scanner().scanAndSave(selectedFile.getPath());
    }

    private void selectPath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setSelectedFile(Paths.get("").toAbsolutePath().toFile());
        if (fileChooser.showDialog(mainFrame, "Select class path") == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            pathTextField.setText(selectedFile.getPath());
        }
    }

    public static void create(String caption) {
        MainFrame runnable = new MainFrame(caption);
        SwingUtilities.invokeLater(runnable);
    }
}
