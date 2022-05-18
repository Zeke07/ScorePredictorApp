/* Score
//<Author: Zayn Khan>
//<Email: zaynalikhan@gmail.com>
//<Version: 05/17/2022>
 */
package com.example.scorepredictorapp;

/**
 * Stores a data-pair from the set into an object
 * X: GPA
 * Y: SAT Score
 * @author zeke
 */
public class Score {
    private double gpa;
    private double satScore;
    public Score(double g, double sat)
    {
        gpa = g;
        satScore = sat;
    }
    public double getGPA()
    {
        return this.gpa;
    }
    public double getSatScore()
    {
        return this.satScore;
    }

    @Override
    public String toString()
    {
        return "GPA: " + gpa + " SAT Score: " + satScore;
    }


}
