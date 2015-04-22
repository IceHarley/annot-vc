package com.bikesandwheels.config;

import com.bikesandwheels.gui.*;
import com.bikesandwheels.gui.model.RevisionsTableModel;
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
        mainFrame.setDefaultSearchPath(environment.getProperty(PROPERTY_GUI_DEFAULT_SEARCH_PATH));
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
}
