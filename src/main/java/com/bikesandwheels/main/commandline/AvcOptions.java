package com.bikesandwheels.main.commandline;

import org.apache.commons.cli.*;

public class AvcOptions {
    private Options options = new Options();

    public AvcOptions() {
        Option help = new Option("help", "print this message");
        Option gui = new Option("gui", "[Default] open Annotations Version Control GUI");
        Option path   = OptionBuilder.withArgName( "path" )
                .hasArg()
                .withDescription("class path for revision annotation scanning. Revision will be saved to DB")
                .create( "scan" );
        options.addOption(help);
        options.addOption(gui);
        options.addOption(path);
    }

    public Options getOptions() {
        return options;
    }
}
