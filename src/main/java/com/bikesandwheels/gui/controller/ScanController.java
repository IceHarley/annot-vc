package com.bikesandwheels.gui.controller;

import com.bikesandwheels.gui.model.*;
import com.bikesandwheels.gui.view.ScanPanel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Paths;

public class ScanController {
    private static Logger log = Logger.getLogger(ScanController.class);
    @Autowired
    private ScanPanel view;
    @Autowired
    private ScanModel model;
    @Autowired
    private RevisionsTableModel tableModel;

    public boolean scan() {
        try {
            if (!validateSelectedFile())
                return false;
            model.scan();
            tableModel.refresh();
            return true;
        }
        catch (Exception e) {
            log.error("Scan error: ", e);
            return false;
        }
    }

    private boolean validateSelectedFile() {
        return !(model.getSelectedFile() == null || !model.getSelectedFile().exists());
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
