package com.bikesandwheels.main;

import com.bikesandwheels.config.*;
import com.bikesandwheels.gui.view.MainFrame;
import com.bikesandwheels.interactors.*;
import com.bikesandwheels.main.commandline.AvcParser;
import com.bikesandwheels.tools.FileUtils;
import org.apache.commons.cli.HelpFormatter;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class Application {
    private static Logger log = Logger.getLogger("AVC");
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
        ConfigurableApplicationContext context = getContext();

        try {
            context.start();
            Scanner scanner = (Scanner)context.getBean("scanner");
            scanner.scanAndSave(FileUtils.filePathToUrl(scanPath));
        }
        catch (FileNotFoundException e) {
            log.error(e);
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            log.error(e);
            e.printStackTrace();
        }
        finally {
            context.close();
        }
    }

    private static ConfigurableApplicationContext getContext() {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, String.format("%s, %s", Profiles.LIVE, Profiles.DB_FILE));
        return new AnnotationConfigApplicationContext(AppConfig.class);
    }

    private static void startGui() {
        ConfigurableApplicationContext context = getContext();

        context.start();
        MainFrame mainFrame = (MainFrame)context.getBean("mainFrame");
        mainFrame.setCaption("Annotations Version Control");
        mainFrame.startInEventDispatchThread();
    }


}
