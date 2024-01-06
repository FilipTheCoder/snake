package com.trampota.snakegame;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        int width = 640;
        int height = 640;

        int numberOfRows = 32;
        int numberOfColumns = 32;
        int rowLength = width / numberOfRows;
        int colLength = height / numberOfColumns;
        Snake snake = new Snake(rowLength, colLength, Color.BLACK, width, height);
        ArrayList<Food> foods = new ArrayList<>();
        Random random = new Random();
        Interval foodInterval = new Interval(2000);
        Pane mainPane = new Pane();
        snake.drawOnPane(mainPane);
        Timeline foodTimer = new Timeline();
        Scene scene = new Scene(mainPane, width, height);

        KeyFrame key = new KeyFrame(Duration.millis(foodInterval.getInterval() + 1), (event) -> {

            foodInterval.setInterval(foodInterval.getInterval()*0.8);
            KeyFrame newKeyFrame = new KeyFrame(Duration.millis(foodInterval.getInterval()), ev2 -> {
                Food food = new Food(random.nextInt(numberOfRows + 1) * rowLength, random.nextInt(numberOfColumns + 1) * colLength, Color.RED, rowLength, colLength);
                foods.add(food);
                mainPane.getChildren().add(food.getRect());
            });
            foodTimer.getKeyFrames().setAll(newKeyFrame);
            foodTimer.play();


        });

        foodTimer.setCycleCount(Animation.INDEFINITE);
        var changeTimer = new Timeline(key);
        changeTimer.setCycleCount(Animation.INDEFINITE);
        changeTimer.play();

        new AnimationTimer() {

            @Override
            public void handle(long l) {
                if(snake.died()) {
                    changeTimer.stop();
                    foodTimer.stop();
                    Label score = new Label("Score: " + snake.tailLength());
                    BorderPane pane = new BorderPane();
                    pane.setCenter(score);
                    stage.setScene(new Scene(pane,width,height));

                    return;


                }
                snake.move(rowLength);

                var foodIterator = foods.iterator();
                while(foodIterator.hasNext()) {
                    Food food = foodIterator.next();
                    if(snake.canEat(food)) {
                        snake.eatAndDraw(mainPane);
                        mainPane.getChildren().remove(food.getRect());
                        foodIterator.remove();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        }.start();



        scene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.DOWN) {
                snake.setDirection(Direction.DOWN);
            } else if(event.getCode() == KeyCode.UP) {
                snake.setDirection(Direction.UP);
            }else if(event.getCode() == KeyCode.RIGHT) {
                snake.setDirection(Direction.RIGHT);
            }else if(event.getCode() == KeyCode.LEFT) {
                snake.setDirection(Direction.LEFT);
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(HelloApplication.class);
    }

}