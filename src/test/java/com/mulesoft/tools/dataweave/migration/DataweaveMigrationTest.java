package com.mulesoft.tools.dataweave.migration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataweaveMigrationTest {
   
    private String INPUT_FILE_DIR = "src/test/resources/dw/input";
    private String OUTPUT_DIR = "src/test/resources/dw/output";

    private DataweaveMigrationJob dataweaveMigrationJob;

    @Before
    public void setUp() throws Exception {
        dataweaveMigrationJob = new DataweaveMigrationJob();
        dataweaveMigrationJob.setFilesDir(INPUT_FILE_DIR);
        dataweaveMigrationJob.setOutputDir(OUTPUT_DIR);
    }

    @Test
    public void jobWithFilePaths() throws Exception {
        dataweaveMigrationJob.execute();
    }

    @After
    public void restoreFileState() throws Exception {
        //Do nothing
    }
}
