package ca.cmpt213.as2;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * JsonReader class contains a function which reads a json file and saves its several elements in a
 * ArrayList of String arrays.
 */

public class JsonReader {

    public void readJson (File jsonFile, ArrayList<String[]> tokimons) throws FileNotFoundException {
        String firstTokimon = null;
        int numberOfTokimons = 0;
        int numericScore;
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(new FileReader(jsonFile));
        String extra = object.get("extra_comments").getAsString();
        JsonArray teamArray = object.getAsJsonArray("team");
        for (int i = 0; i < teamArray.size(); i++) {
            JsonObject toki = (JsonObject) teamArray.get(i);
            String toToki;
            String fromToki;
            if (numberOfTokimons == 0) {
                fromToki = toki.get("id").getAsString();
                firstTokimon = fromToki;
                toToki = "-";
            }
            else {
                fromToki = firstTokimon;
                toToki = toki.get("id").getAsString();
                extra = "";
            }
            JsonObject comp = (JsonObject) toki.get("compatibility");
            String comment = comp.get("comment").getAsString();
            numericScore = comp.get("score").getAsInt();
            if (numericScore < 0) {
                System.out.println("Score of a tokimon with id " + fromToki + " in" + jsonFile + " is less than 0");
                System.exit(-1);
            }
            String score = comp.get("score").getAsString();
            numberOfTokimons++;

            String[] newTokimon = {"", fromToki, toToki, score, comment, "", extra};
            tokimons.add(newTokimon);
        }
    }
}
