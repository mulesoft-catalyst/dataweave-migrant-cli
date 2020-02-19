package com.mulesoft.tools.dataweave.migration;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mule.weave.v2.V2LangMigrant.migrateToV2;

public class DataweaveMigrationJob {

    private List<String> filePaths;
    private String filesDir;
    private String outputDir;

    public void execute() throws Exception {

        if (null != this.filesDir) {
            this.filePaths = getFilePaths(this.filesDir);
        }

        try {
            Path originalPath;
            String dwOriginalScript;
            String dwMigratedScript;

            System.out.println("Executing...");
            for (String filePath : this.filePaths){

                originalPath = Paths.get(filePath);
                System.out.println("Migrating:" + originalPath.getFileName());
                dwOriginalScript = new String(Files.readAllBytes(originalPath));

                dwMigratedScript = migrateToV2(dwOriginalScript);

                Files.write(Paths.get(outputDir + File.separator + originalPath.getFileName()), dwMigratedScript.getBytes());
                System.out.println("Migrated:" + originalPath.getFileName());
            }
        } catch (Exception ex) {
            throw new Exception("Failed to migrate the file: " + ". " + ex.getMessage() + "/n" + ex.getStackTrace());
        }
    }

    public List<String> getFilePaths(String rootDirectoryPath) throws Exception {

        File rootDirectory = new File(rootDirectoryPath);

        List<File> files = (List<File>) FileUtils.listFiles(rootDirectory, new String[] { "dwl" }, true);

        List<String> filesPaths = new ArrayList<>();

        for (File file : files) {
            filesPaths.add(file.getAbsolutePath());
        }

        return filesPaths;
    }

    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    public void setFilesDir(String filesDir) {
        this.filesDir = filesDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }
}
