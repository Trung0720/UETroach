package uet.oop.bomberman.screen;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.graphics.Sprite;

import java.io.InputStream;

public class StatusBar {
    private static final String FONT_PATH = "/font/Retro Gaming.ttf";
    private static final int FONT_SIZE = 14;
    public static Text level, time, score;
    public static int countDown = 100000;

    public static void createStatusBar(Group root) {
        level = createText(100, 23);
        time = createText(300, 23);
        score = createText(500, 23);

        Pane pane = new Pane();
        pane.setMinWidth(Main.CAMERA_WIDTH * Sprite.SCALED_SIZE);
        pane.setPrefHeight(Main.STATUS_BAR_HEIGHT);
        pane.getChildren().addAll(level, time, score);
        pane.setLayoutY(Main.CAMERA_HEIGHT * Sprite.SCALED_SIZE - Main.STATUS_BAR_HEIGHT);
        pane.setStyle("-fx-background-color: #333232");

        root.getChildren().add(pane);
    }

    private static Text createText(double x, double y) {
        Text text = new Text("");
        text.setFill(Color.SNOW);
        text.setX(x);
        text.setY(y);

        InputStream fontStream = StatusBar.class.getResourceAsStream(FONT_PATH);

        if (fontStream != null) {
            text.setFont(Font.loadFont(fontStream, FONT_SIZE));
        } else {
            System.out.println("Status bar font not found!");
            text.setFont(Font.font("Arial", FONT_SIZE));
        }

        return text;
    }

    public static void updateStatusBar(long now) {
        level.setText("LEVEL: " + Main.currentLevel);
        time.setText("TIME: " + countDown / 100);
        score.setText("SCORE: " + Main.score);
        countDown -= 2; // Đếm ngược theo 60FPS
    }
}
