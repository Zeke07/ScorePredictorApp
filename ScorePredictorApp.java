/* ScorePredictorApp
//<Author: Zayn Khan>
//<Email: zaynalikhan@gmail.com>
//<Version: 05/17/2022>
 */
package com.example.scorepredictorapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * FX driver for ScorePredictor App
 *
 * @author zeke
 *
 */
public class ScorePredictorApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        List<Score> scores = new ArrayList<Score>();

        // load data from absolute path
        scores = Parser.DataLoad("/Users/zeke/Desktop/IntelliJ_Workspace/ScorePredictorApp/src/main/java/com/example/scorepredictorapp/GPA_BIG_final.csv");
        RegressionModel model = new RegressionModel();
        model.constructModel(scores); // create regression line using list of scores

        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 640, 480); // program panel
        stage.setTitle("SAT Score Predictor Application");
        Button exit = new Button("Quit");
        exit.setOnAction(event -> {Platform.exit();});
        pane.setBottom(exit);
        BorderPane.setAlignment(exit, Pos.BOTTOM_RIGHT);
        Button result = new Button("Process");  // the button that generates output
        result.setPrefSize( 150, 70);
        TextField field = new TextField("Enter your Weighted GPA"); // input field

        Text text = new Text();
        Pane p = new Pane(result, field, text);
        pane.getChildren().add(p);
        field.setPrefWidth(240);
        field.setLayoutX(200);
        field.setLayoutY(240);
        text.setFont(new Font("Verdana", 25));
        text.setLayoutX(130);
        text.setLayoutY(140);
        result.setLayoutX(240);
        result.setLayoutY(320);

        // the input field normally says "Enter here..."
        // this just empties it upon a user click
        List<Score> finalScores = scores;
        field.setOnMouseClicked(event -> {
            field.setText("");
            text.setText("");
        });

        // conversely, you can click the background to reset the input field text
        p.setOnMouseClicked(e -> {
            field.setText("Enter your Weighted GPA");
            text.setText("");
        });

        // the center button will probe the text-field for valid input
        // if it is pressed
        // if it is found, generate the predicted SAT score using the model
        result.setOnAction(event -> {
            try {
                Scanner input = new Scanner(field.getText());
                double user = input.nextDouble();
                if (user <= 5.0 && user >= 0.0) {
                    double toDisplay = model.predictScore(finalScores, user);
                    text.setText("Your expected SAT score: " + Integer.toString((int) toDisplay));
                } else
                    field.setText("Please enter a valid GPA (0.0-5.0)");
            }
            catch (Exception e)
            {
                field.setText("Please enter a valid GPA (0.0-5.0)");
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
