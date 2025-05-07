package uet.oop.bomberman.screen;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.io.InputStream;

public class StatusBar {
    private static final String FONT_PATH = "/font/Retro Gaming.ttf";
    private static final int FONT_SIZE = 14;
    public static Text level, time, score, masterVolume;
    public static Slider volumeSlider;
    public static int countDown = 100000;

    public static void createStatusBar(Group root) {
        level = createText(100, 22);
        time = createText(300, 22);
        score = createText(500, 22);
        masterVolume = createText(820, 22);
        masterVolume.setText("MASTER VOLUME:");
        volumeSlider = createSoundSlider();

        Pane pane = new Pane();
        pane.setMinWidth(Main.CAMERA_WIDTH * Sprite.SCALED_SIZE);
        pane.setPrefHeight(Main.STATUS_BAR_HEIGHT);
        pane.getChildren().addAll(level, time, score, masterVolume, volumeSlider);
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

    public static Slider createSoundSlider() {
        Slider volumeSlider = new Slider(0, 1, Sound.getSoundVolume());
        volumeSlider.setPrefWidth(300);
        volumeSlider.setLayoutX(970);
        volumeSlider.setLayoutY(10);
        volumeSlider.setStyle("""
        -fx-control-inner-background: #cdcbc9;
        -fx-base: #5e5d5d;
        """);

        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            Sound.setSoundVolume(newVal.doubleValue());
        });

        volumeSlider.setFocusTraversable(false);
        volumeSlider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
            if (!isChanging) {
                Platform.runLater(() -> {
                    volumeSlider.getScene().getRoot().requestFocus();
                });
            }
        });
        return volumeSlider;
    }

    public static void updateStatusBar(long now) {
        level.setText("LEVEL: " + GameLoop.currentLevel);
        time.setText("TIME: " + countDown / 100);
        score.setText("SCORE: " + GameLoop.score);
        countDown -= 2;// Đếm ngược theo 60FPS
        if (countDown == 0) {
            GameLoop.gameStatus = GameLoop.STATUS_GAME_OVER;
        }
    }
}
