package ca.cmpt213.as2;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * JsonOrganizer class contains two functions that sorts the ArrayList of String array and organizes
 * the elements according to different teams.
 */

public class JsonOrganizer {
    public void jsonSort(ArrayList<String[]> list) {
        Comparator<String[]> comparator = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return (o1[1].substring(4,6).compareTo(o2[1].substring(4,6)));
            }
        };
        java.util.Collections.sort(list, comparator);
    }

    public void jsonOrganize(ArrayList<String[]> list) {
        String[] firstTeamNumber = {"Team 1", "", "", "", "", "", "" };
        list.add(0, firstTeamNumber);
        int teamNumber = 2;
        for (int i = 1; i < list.size() - 1; i++) {
            String first = list.get(i)[1].substring(4,6);
            String second = list.get(i+1)[1].substring(4,6);
            if (!second.equalsIgnoreCase(first)) {
                String[] anotherTeamNumber = {"Team "+ teamNumber, "", "", "", "", "", ""};
                list.add(i+1, anotherTeamNumber);
                teamNumber++;
                int temp = i + 1;
                if (temp < list.size() - 2) {
                    i++;
                }
            }
        }
    }
}
