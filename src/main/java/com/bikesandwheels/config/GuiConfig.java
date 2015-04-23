package com.bikesandwheels.config;

import com.bikesandwheels.gui.controller.*;
import com.bikesandwheels.gui.model.*;
import com.bikesandwheels.gui.view.*;
import com.bikesandwheels.interactors.Scanner;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@Profile({Profiles.LIVE, Profiles.DB_FILE, Profiles.DB_IN_MEMORY})
public class GuiConfig {
    public static final String PROPERTY_GUI_DEFAULT_SEARCH_PATH = "gui.default_search_path";

    @Bean
    public MainFrame mainFrame(Environment environment) {
        MainFrame mainFrame = new MainFrame();
        return mainFrame;
    }

    @Bean
    public RevisionsTable revisionsTable() {
        return new RevisionsTable();
    }

    @Bean
    RevisionsTableModel revisionsTableModel() {
        return new RevisionsTableModel();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner();
    }

    @Bean
    public ScanPanel scanPanel() {
        return new ScanPanel();
    }

    @Bean
    public ScanModel scanModel(Environment environment) {
        ScanModel model = new ScanModel();
        model.setDefaultSearchPath(environment.getProperty(PROPERTY_GUI_DEFAULT_SEARCH_PATH));
        return model;
    }

    @Bean
    public ScanController scanController() {
        return new ScanController();
    }

    @Bean
    public RevisionsTableController revisionsTableController() {
        return new RevisionsTableController();
    }
}
