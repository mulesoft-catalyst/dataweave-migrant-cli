package com.mulesoft.tools.dataweave.migration;

import com.mulesoft.tools.dataweave.migration.exception.ConsoleOptionsException;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mulesoft.tools.dataweave.migration.DataweaveMigrationRunner.MigrationConsoleOptions.*;

public class DataweaveMigrationRunner {

    private List<String> filesPaths;
    private String filesDir;
    private String outputDir;

    public static void main(String args[]) throws Exception {
        DataweaveMigrationRunner dataweaveMigrationRunner = new DataweaveMigrationRunner();
        dataweaveMigrationRunner.initializeOptions(args);

        DataweaveMigrationJob dataweaveMigrationJob = new DataweaveMigrationJob();
        dataweaveMigrationJob.setFilesDir(dataweaveMigrationRunner.filesDir);
        dataweaveMigrationJob.setFilePaths(dataweaveMigrationRunner.filesPaths);
        dataweaveMigrationJob.setOutputDir(dataweaveMigrationRunner.outputDir);
        dataweaveMigrationJob.execute();
    }

    /**
     * Initialises the console options with Apache Command Line
     * @param args
     */
    private void initializeOptions(String[] args) {

        Options options = new Options();
		
        options.addOption(FILES,true,"List of dw scripts paths separated by ';' example: file1.dwl;file2.dwl...etc");
        options.addOption(FILES_DIR,true,"Root directory of the scripts to be migrated");
        options.addOption(OUTPUT_DIR,true,"output directory to place the migrated scripts");
        options.addOption(HELP,false,"Shows the help");

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine line = parser.parse(options, args);

            if(line.hasOption(FILES) && !line.hasOption(FILES_DIR)) {
                this.filesPaths = new ArrayList<>(Arrays.asList(line.getOptionValue(FILES).split(";")));
            } else if (!line.hasOption(FILES) && line.hasOption(FILES_DIR)) {
                this.filesDir = line.getOptionValue(FILES_DIR);
            } else {
                throw new ConsoleOptionsException("You must specify a root directory of the files to be migrated OR a list " +
                        "of paths separated by ';' example: path1;path2...etc");
            }

            if(line.hasOption(OUTPUT_DIR)) {
                this.outputDir = line.getOptionValue(OUTPUT_DIR);
            } else {
                throw new ConsoleOptionsException("You must specify an output directory to place the migrated scripts");
            }

            if(line.hasOption(HELP)) {
                printHelp(options);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (ConsoleOptionsException e) {
            printHelp(options);
            System.exit(-1);
        }
    }

    private void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("dataweave-migration-tool - Help", options);
    }

    static class MigrationConsoleOptions {
        public final static String FILES= "files";
        public final static String FILES_DIR= "filesDir";
        public final static String OUTPUT_DIR= "outputDir";
        public final static String HELP= "help";
    }
}