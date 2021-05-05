package ca.cmpt213.as2;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Launches application and implements a function to filter json files from folders.
 */

public class TokimonProcessor {

    private static void filterInputFolder(File folder, ArrayList<File> files) {
        FileFilter filter = new JsonFilter();
        if (folder.isDirectory()) {
            File[] newFolder = folder.listFiles();
            if (newFolder != null) {
                for (int i = 0; i < newFolder.length; i++) {
                    filterInputFolder(newFolder[i], files);
                    File[] list = newFolder[i].listFiles(filter);
                    if (list != null) {
                        for (int j = 0; j < list.length; j++) {
                            files.add(list[j]);
                        }
                    }
                }
            }
            else {
                System.out.println("The " + folder + "has no files");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Error: Please provide 2 paths only");
            return;
        }

        File inputFolder = new File(args[0]);
        if (!inputFolder.isDirectory()) {
            System.out.println("Error: The input folder does not exist");
            return;
        }

        File outputFolder = new File(args[1]);
        if (!outputFolder.isDirectory()) {
            System.out.println("Error: The output folder does not exist");
            return;
        }

        ArrayList<File> jsonFiles = new ArrayList<>();
        filterInputFolder(inputFolder, jsonFiles);
        if (jsonFiles.size() == 0) {
            System.out.println("Error: There are no Json files in the input folder");
            return;
        }

        JsonReader jsonReader = new JsonReader();
        ArrayList<String[]> tokimons = new ArrayList<>();
        for (int i = 0; i < jsonFiles.size(); i++) {
            jsonReader.readJson(jsonFiles.get(i), tokimons);
        }

        JsonOrganizer jsonOrganizer = new JsonOrganizer();
        jsonOrganizer.jsonSort(tokimons);
        jsonOrganizer.jsonOrganize(tokimons);

        CsvOutput csvOutput = new CsvOutput();
        csvOutput.output(outputFolder, tokimons);
    }
}
