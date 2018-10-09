package wundernutvol9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author taina
 *
 * in response to https://github.com/wunderdogsw/wunderpahkina-vol9
 *
 */
public class WunderNutVol9 {

    private static List<boolean[]> rows;
    private static final boolean PRINTDEBUG = false;
    private static int n;

    public static void main(String[] args) {

        // testing just one
        String readRow = ("...##...");
        //System.out.println("" + searchForPattern(readRow));
        
        // reading the file
        String fileName  = "./patterns.txt" ; 
        readFileAndLoopRows(fileName);

    } // end method main

    public static void readFileAndLoopRows(String fileName) {
        // read the pattern to start with
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String readRow = scanner.nextLine();
                //     String readRow = ("...#.###...");
                System.out.println("" + searchForPattern(readRow));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } // end method readFileAndLoopRows

    public static String searchForPattern(String readRow) {
        rows = new ArrayList<>();
        boolean[] row = new boolean[readRow.length()];
        int place = 0;
        for (char ch : readRow.toCharArray()) {
            row[place] = (ch == '#');
            place++;
        }
        rows.add(row);
        n = row.length;
        addRow(1);
        printRow(0);
        printRow(1);
        // default for the pattern if we don't find anything else
        String patternIs = "other";
        // maximum line number needed to examine is 100 minus the given one
        for (int i = 2; i < 100; i++) {
            // this is the order in which the patterns needs to be checked
            // and yes, would be more efficient to chech them all in one.. and not to loop each one on its own
            if (isItVanishing()) {
                patternIs = "vanishing";
                break;
            }
            if (isItBlinking()) {
                patternIs = "blinking";
                break;
            }
            if (isItGliding()) {
                patternIs = "gliding";
                break;
            }
            addRow(i);
            printRow(i);
        }
        return patternIs;
    } // end method searchForPattern

    private static void addRow(int rowNo) {
        boolean[] previousRow = rows.get(rowNo - 1);
        boolean[] newRow = new boolean[n];
        for (int i = 0; i < n; i++) {
            int neighbours = countNeighboursAt(previousRow, i);
            // Rule #1, the square above is blank
            if (!previousRow[i]) {
                //If there are 2 or 3 filled squares in total next to it (taking into account 4 squares, 2 on each sides) it will be filled. If not, it will be left blank.
                newRow[i] = (neighbours == 2 || neighbours == 3);
            } // Rule #2, the square above is filled
            else {
                // If there are 2 or 4 squares filled in total next to it (taking into account 4 squares, 2 on each sides) it will be filled. If not, it will be left blank.
                newRow[i] = (neighbours == 2 || neighbours == 4);
            }
        }
        rows.add(newRow);
    } // end method addRow

    // The pattern and location of colored squares is exactly the same as in some of the preceding 
    //
    // . # . # # . . 
    // . . # # . # . 
    // . # . # # . . 
    //
    private static boolean isItBlinking() {
        boolean[] currentRow = rows.get(rows.size() - 1);
        for (int lastRowNo = rows.size() - 2; lastRowNo >= 0; lastRowNo--) {
            boolean[] lastRow = rows.get(lastRowNo);
            boolean readWholeRow = true;
            for (int i = 0; i < n; i++) {
                if (lastRow[i] != currentRow[i]) {
                    readWholeRow = false;
                    break;
                }
            }
            if (readWholeRow) {
                return true;
            }
        }
        return false;
    } // end method isItBlinking

    // The pattern of colored squares is the same as in some of the preceding lines, but is located in different position. 
    //
    // . # . # # # . .
    // . . # . # # # .
    //
    private static boolean isItGliding() {
        boolean[] currentRow = rows.get(rows.size() - 1);
        for (int lastRowNo = rows.size() - 2; lastRowNo >= 0; lastRowNo--) {
            boolean[] lastRow = rows.get(lastRowNo);
            boolean readWholeRow = true;
            int i = 0;
            int j = 0;
            while (!lastRow[i]) {
                i++;
            }
            while (!currentRow[j]) {
                j++;
            }
            while (i < n && j < n) {
                if (lastRow[i] != currentRow[j]) {
                    readWholeRow = false;
                    break;
                }
                i++;
                j++;
            }
            if (readWholeRow) {
                return true;
            }
        }
        return false;
    } // end method isItGliding

    // There are no colored squares on a line
    //
    // . # . # .
    // . . # . . 
    // . . . . .
    //
    private static boolean isItVanishing() {
        boolean[] lastRow = rows.get(rows.size() - 1);
        for (int i = 0; i < n; i++) {
            if (lastRow[i]) {
                return false;
            }
        }
        return true;
    } // end method isItVanishing

    private static int countNeighboursAt(boolean[] previousRow, int i) {
        int n = previousRow.length;
        int neighbours = 0;
        if (i - 2 >= 0) {
            if (previousRow[i - 2]) {
                neighbours++;
            }
        }
        if (i - 1 >= 0) {
            if (previousRow[i - 1]) {
                neighbours++;
            }
        }
        if (i + 1 < n) {

            if (previousRow[i + 1]) {
                neighbours++;
            }
        }
        if (i + 2 < n) {
            if (previousRow[i + 2]) {
                neighbours++;
            }
        }
        return neighbours;
    } // end method countNeighboursAt 

    private static void printRow(int rowToPrint) {
        if (!PRINTDEBUG) {
            return;
        }
        boolean[] printRow = rows.get(rowToPrint);
        for (int i = 0; i < n; i++) {
            if (printRow[i]) {
                System.out.print("#");
            } else {
                System.out.print(".");
            }
            System.out.print(" ");
        }
        System.out.println("");
    } // end method printRow 

} // end class WunderNutVol9 
