package com.bikesandwheels.gui.view;

import com.bikesandwheels.gui.controller.RevisionsTableController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;

public class MainFrame implements Runnable {
    private String caption;
    private JFrame mainFrame;
    @Autowired
    private ScanPanel scanPanel;
    @Autowired
    private RevisionsTable revisionsTable;
    @Autowired
    private RevisionsTableController revisionsTableController;

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void run() {
        createMainFrame();
        initComponents();
        initMainFrame();
    }

    private void createMainFrame() {
        mainFrame = new JFrame(caption);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initMainFrame() {
        mainFrame.getContentPane().setLayout(new BorderLayout());
        mainFrame.setLocationByPlatform(true);
        mainFrame.getContentPane().add(scanPanel, BorderLayout.NORTH);
        mainFrame.getContentPane().add(revisionsTable, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setSize(1000, 500);
        mainFrame.setVisible(true);
    }

    private void initComponents() {
        revisionsTable.init();
        scanPanel.init();
        revisionsTableController.init();
    }

    public void startInEventDispatchThread() {
        SwingUtilities.invokeLater(this);
    }


}