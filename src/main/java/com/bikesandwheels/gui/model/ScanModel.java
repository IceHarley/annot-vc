package com.bikesandwheels.gui.model;

import com.bikesandwheels.gui.view.ScanModelListener;
import com.bikesandwheels.interactors.Scanner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.event.*;
import java.io.File;
import java.net.MalformedURLException;

public class ScanModel {
    protected EventListenerList listenerList = new EventListenerList();
    @Autowired
    private Scanner scanner;
    private File selectedFile;
    private String defaultSelectedPath;

    public void addTableModelListener(ScanModelListener listener) {
        this.listenerList.add(ScanModelListener.class, listener);
    }

    public File getSelectedFile() {
        if (selectedFile == null)
            selectedFile = new File(defaultSelectedPath);
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
        fireSelectedFileChanged();
    }

    private void fireSelectedFileChanged() {
        Object[] listeners = this.listenerList.getListenerList();
        for (Object listener : listeners)
            if (listener instanceof ScanModelListener)
                ((ScanModelListener) listener).selectedPathChanged();
    }

    public void setDefaultSearchPath(String defaultSelectedPath) {
        this.defaultSelectedPath = defaultSelectedPath;
    }

    public String getDefaultSelectedPath() {
        return defaultSelectedPath;
    }

    public void scan() {
        try {
            scanner.scanAndSave(getSelectedFile().toURI().toURL());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
