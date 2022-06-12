/* Parser
//<Author: Zayn Khan>
//<Email: zaynalikhan@gmail.com>
//<Version: 05/17/2022>
 */
package com.example.scorepredictorapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * parsing dataset for regression model
 * https://www.kaggle.com/datasets/toniabiru/gpa-dataset
 * (renamed GPA_BIG.csv)
 * slightly modified because I noticed the value threshold was rather low (ex: 1280 for 5.0 weighted gpa)
 * so I skewed the set a little
 *
 * @author zeke
 */
public class Parser {

    public static List<Score> DataLoad(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename), "utf-8");
        scanner.nextLine();
        String[] temp = new String[7]; // seven columns in the CSV, indices 0 and 1 contain the wanted information
        List<Score> toReturn = new ArrayList<Score>();
        while (scanner.hasNext())
        {
            temp = scanner.nextLine().split(",");
            toReturn.add(new Score(Double.parseDouble(temp[0]), Double.parseDouble(temp[1])));
        }

        return toReturn;


    }
}
