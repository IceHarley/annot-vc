package com.bikesandwheels.main;

import com.bikesandwheels.config.*;
import com.bikesandwheels.gui.MainFrame;
import com.bikesandwheels.interactors.*;
import com.bikesandwheels.main.commandline.AvcParser;
import org.apache.commons.cli.HelpFormatter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

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
            Scanner scanner = (Scanner)context.getBean("scanner");
            scanner.scanAndSave(scanPath);
        }
        finally {
            context.close();
        }
    }

    private static void startGui() {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "live, db-file");
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        try {
            context.start();
            MainFrame.create("Annotations Version Control");
            AnnotatedClassesSearcher s = (AnnotatedClassesSearcher) context.getBean("annotatedClassesSearcher");
            s.search();
        }
        finally {
            //context.close();
        }

    }


}
