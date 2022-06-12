/* RegressionModel
//<Author: Zayn Khan>
//<Email: zaynalikhan@gmail.com>
//<Version: 05/17/2022>
 */
package com.example.scorepredictorapp;

import java.util.List;

/**
 * Simple regression model, extrapolated from parsed dataset
 * Objective: predict SAT score given weighted GPA
 *
 * @author zeke
 */
public class RegressionModel {
    private double slope;
    private double yIntercept;

    public RegressionModel()
    {
        slope = 0.0;
        yIntercept = 0.0;
    }
    /**
     * Explanatory variable: GPA
     * Regression Equation for slope of best-fit line (m) -->
     * Summation of(x - meanofX) * Summationof(y - mean of y)/ (Summation of(x - mean ofX))^2
     * Y = mx + b, find remaining vars
     *
     * Model is done by hand, naively, following the above procedure
     *
     * @param list the GPA/SAT score pairs from the data
     *
     */
    public void constructModel(List<Score> list)
    {
        this.slope= 0.0;
        double xMean = calculateMean(list, "GPA");
        double yMean = calculateMean(list, "SAT");
        double summationCalc = calculateSummation(list, xMean, yMean, "calcSum");
        double denominator = (calculateSummation(list, xMean, yMean, "denominator"));
        this.slope = summationCalc/denominator;
        this.yIntercept = yMean-(xMean*this.slope);
    }

    /**
     * The output of the final program
     * Calculates the expected SAT score (y-axis on the regression line)
     * based on a given 'x' and other variables known
     *
     * @param list the dataset
     * @param gpaGiven user input from the FX display
     * @return
     */
    public double predictScore(List<Score> list, double gpaGiven)
    {
      return (slope*gpaGiven) + yIntercept;
    }

    public double calculateMean(List<Score> list, String type)
    {
        double mean = 0.0;
       if (type.equals("GPA"))
       {
         for (Score s: list)
             mean += s.getGPA();
         mean /= list.size();
       }
       else if (type.equals("SAT"))
        {
            for (Score s: list)
                mean += s.getSatScore();
            mean /= list.size();
        }
       return mean;
    }
    public double calculateSummation(List<Score> list, double xMean, double yMean, String parameter)
    {
        double summation = 0.0;
        if (parameter.equals("calcSum")) {
            for (Score s : list) {
                summation += ((s.getGPA() - xMean) * (s.getSatScore() - yMean));
            }
        }
        else if (parameter.equals("denominator"))
        {  for (Score s : list)
            summation += Math.pow((s.getGPA() - xMean), 2);
        }

        return summation;
    }
}
