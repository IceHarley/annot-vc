package com.bikesandwheels.main.commandline;

import org.apache.commons.cli.*;

public class AvcParser {
    private final Options options;
    private final String[] args;
    private CommandLine commandLine;

    public AvcParser(String[] args) {
        this.args = args;
        options = new AvcOptions().getOptions();
    }

    public void parse() {
        CommandLineParser parser = new GnuParser();
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException exp) {
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }
    }

    public boolean doShowHelp() {
        return commandLine.hasOption("help");
    }

    public boolean doStartGui() {
        return commandLine.hasOption("gui");
    }

    public boolean doScan() {
        return commandLine.hasOption("scan");
    }

    public String getScanPath() {
        return commandLine.getOptionValue("scan");
    }

    public Options getOptions() {
        return options;
    }
}
