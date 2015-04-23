package com.bikesandwheels.gui.controller;

import com.bikesandwheels.gui.model.*;
import com.bikesandwheels.gui.view.ScanPanel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Paths;

public class ScanController {
    @Autowired
    private ScanPanel view;
    @Autowired
    private ScanModel model;
    @Autowired
    private RevisionsTableModel tableModel;

    public void scan() {
        model.scan();
        tableModel.refresh();
    }

    public void selectPath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Jar files", "jar"));
        fileChooser.setSelectedFile(Paths.get("").toAbsolutePath().toFile());
        if (fileChooser.showDialog(view, "Select jar") == JFileChooser.APPROVE_OPTION)
            model.setSelectedFile(fileChooser.getSelectedFile());
    }
}
