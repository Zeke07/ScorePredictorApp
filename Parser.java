/* Parser
//<Author: Zayn Khan>
//<Email: zaynalikhan@gmail.com>
//<Version: 05/17/2022>
 */
package com.example.scorepredictorapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * parsing dataset for regression model
 *
 * @author zeke
 */
public class Parser {

    public static List<Score> DataLoad(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename), "utf-8");
        scanner.nextLine();
        String[] temp = new String[7];
        List<Score> toReturn = new ArrayList<Score>();
        while (scanner.hasNext())
        {
            temp = scanner.nextLine().split(",");
            toReturn.add(new Score(Double.parseDouble(temp[0]), Double.parseDouble(temp[1])));
        }

        return toReturn;


    }
}
