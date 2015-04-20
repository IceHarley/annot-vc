package com.bikesandwheels.main;

import com.bikesandwheels.annotations.Author;
import com.bikesandwheels.config.*;
import com.bikesandwheels.gui.MainFrame;
import com.bikesandwheels.main.commandline.*;
import org.apache.commons.cli.*;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import javax.swing.*;

public class Application {

    private static AvcParser parser;

    public static void main(String... args) {
        parseArgsAndSelectAction(args);
    }

    private static void parseArgsAndSelectAction(String[] args) {
        parser = new AvcParser(args);
        parser.parse();
        selectAction(parser);
    }

    private static void selectAction(AvcParser parser) {
        if (parser.doShowHelp())
            showHelp();
        else if (parser.doScan())
            scan(parser.getScanPath());
        else
            startGui();
    }

    private static void showHelp() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("Annotations Version Control", parser.getOptions());
    }

    private static void scan(String scanPath) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, String.format("%s, %s", Profiles.LIVE, Profiles.DB_FILE));
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        try {
            context.start();
            context.getBean("appRunner", Runnable.class).run();
        }
        finally {
            context.close();
        }
    }

    private static void startGui() {
        SwingUtilities.invokeLater(new MainFrame());
    }


}
