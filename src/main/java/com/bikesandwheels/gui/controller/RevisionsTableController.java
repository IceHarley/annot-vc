package com.bikesandwheels.gui.controller;

import com.bikesandwheels.gui.model.*;
import com.bikesandwheels.gui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

public class RevisionsTableController {
    @Autowired
    private RevisionsTable view;
    @Autowired
    private RevisionsTableModel model;

    public void init() {
        model.refresh();
    }
}
