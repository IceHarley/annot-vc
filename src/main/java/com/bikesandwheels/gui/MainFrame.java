package com.bikesandwheels.gui;

import com.bikesandwheels.interactors.Scanner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Paths;

public class MainFrame implements Runnable {
    private String caption;
    private JTextField pathTextField;
    private JFrame mainFrame;
    private JButton selectPathButton;
    private JButton scanButton;
    private JPanel topPanel;
    private JPanel areaPanel;
    private File selectedFile;
    @Autowired
    private Scanner scanner;
    @Autowired
    private RevisionsTable revisionsTable;
    private String defaultSearchPath = "";

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void run() {
        selectedFile = Paths.get(defaultSearchPath).toFile();

        mainFrame = new JFrame(caption);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainFrame.getContentPane().setLayout(new BorderLayout());
        mainFrame.setLocationByPlatform(true);

        selectPathButton = new JButton("...");
        selectPathButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        selectPath();
                    }
                });

        pathTextField = new JTextField(40);
        pathTextField.setText(selectedFile.getPath());
        pathTextField.setLayout(new BorderLayout());
        pathTextField.add(selectPathButton, BorderLayout.EAST);

        scanButton = new JButton("Scan");
        scanButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        scan();
                    }
                });

        JPanel pathPanel = new JPanel(true);
        pathPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pathPanel.add(pathTextField);
        pathPanel.add(scanButton);

        topPanel = new JPanel(true);
        topPanel.setLayout(new BorderLayout());
        JLabel pathTitle = new JLabel("Select assembly:");
        JLabel pathFooter = new JLabel(" ");
        pathTitle.setPreferredSize(new Dimension(0, 30));
        topPanel.add(pathTitle, BorderLayout.NORTH);
        topPanel.add(pathPanel);
        topPanel.add(pathFooter, BorderLayout.SOUTH);

        areaPanel = new JPanel(true);
        areaPanel.setLayout(new BorderLayout());
        JLabel tableTitle = new JLabel("Revisions log:");
        tableTitle.setPreferredSize(new Dimension(0, 30));
        areaPanel.add(tableTitle, BorderLayout.NORTH);
        areaPanel.add(revisionsTable);

        revisionsTable.init();

        mainFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
        mainFrame.getContentPane().add(areaPanel, BorderLayout.CENTER);
        mainFrame.pack();

        mainFrame.setSize(1000, 500);
        mainFrame.setVisible(true);
    }

    private void scan() {
        try {
            scanner.scanAndSave(selectedFile.toURI().toURL());
            revisionsTable.refresh();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void selectPath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Jar files", "jar"));
        fileChooser.setSelectedFile(Paths.get("").toAbsolutePath().toFile());
        if (fileChooser.showDialog(mainFrame, "Select jar") == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            pathTextField.setText(selectedFile.getPath());
        }
    }

    public void startInEventDispatchThread() {
        SwingUtilities.invokeLater(this);
    }

    public void setDefaultSearchPath(String defaultSearchPath) {
        this.defaultSearchPath = defaultSearchPath;
    }
}