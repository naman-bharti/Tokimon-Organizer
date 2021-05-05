package ca.cmpt213.as2;

import com.opencsv.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * CsvOutput class contains a function which outputs a csv file to a provided folder after writing an
 * ArrayList of String array in the file.
 */

public class CsvOutput {
    public void output (File outputPath, ArrayList<String[]> list) throws IOException {
        File finalOutputPath = new File (outputPath + "/team_info.csv");
        FileWriter fileWriter = new FileWriter(finalOutputPath);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        String[] firstRow = {"Team#", "From Toki", "To Toki", "Score", "Comment", "", "Extra"};
        csvWriter.writeNext(firstRow);
        for (int i = 0; i < list.size(); i++) {
            csvWriter.writeNext(list.get(i));
        }
        csvWriter.close();
    }
}
