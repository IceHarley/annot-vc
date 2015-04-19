package com.bikesandwheels.main;

import com.bikesandwheels.config.*;
import com.bikesandwheels.main.commandline.*;
import org.apache.commons.cli.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

public class Application {

    private static AvcParser parser;

    public static void main(String... args) {

        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, String.format("%s, %s", Profiles.LIVE, Profiles.DB_FILE));
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        parseArgsAndSelectAction(args);

        ((AnnotationConfigApplicationContext) context).close();
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
        //TODO
    }

    private static void startGui() {
        //TODO
    }


}
