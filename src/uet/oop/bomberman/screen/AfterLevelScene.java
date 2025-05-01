package uet.oop.bomberman.screen;

import javafx.animation.PauseTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.graphics.Sprite;

import java.io.InputStream;

public class AfterLevelScene {
    private static final int WIDTH = Main.CAMERA_WIDTH * Sprite.SCALED_SIZE;
    private static final int HEIGHT = Main.CAMERA_HEIGHT * Sprite.SCALED_SIZE;
    private static final String FONT_PATH = "/font/Pixel Game.otf";
    private static final String BACKGROUND_IMAGE = "/levels/blurred_background.png";
    private static Pane pane;
    public static Text levelUp, gameOver, win, score;
    private static final int TIME = 2;

    public static void renderScene() {
        pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);
        createBackGround();

        // missing render

        Main.root.getChildren().add(pane);

        PauseTransition delay = new PauseTransition(Duration.seconds(TIME));
        delay.setOnFinished(event -> {
            pane.setVisible(false);
        });
        delay.play();
    }

    private static void createBackGround() {
        Image image = new Image(BACKGROUND_IMAGE);
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        imageView.setPreserveRatio(false);

        pane.getChildren().addFirst(imageView);
    }

    private static Text createText(String content, double size) {
        Text text = new Text(content);
        text.setFill(Color.SNOW);
        text.setEffect(new DropShadow());

        InputStream fontStream = AfterLevelScene.class.getResourceAsStream(FONT_PATH);
        if (fontStream != null) {
            text.setFont(Font.loadFont(fontStream, size));
        } else {
            System.out.println("After level scene font not found!");
            text.setFont(new Font("Arial", size));
        }

        return text;
    }

    private static void renderLevelUp() {
        levelUp = createText("LEVEL " + Main.nextLevel, 200);
        levelUp.setX(WIDTH / 2.0 - 250);
        levelUp.setY(HEIGHT / 2.0);
        pane.getChildren().add(levelUp);
    }

    private static void renderGameOver() {
        renderScore();
        gameOver = createText("GAME OVER!", 200);
        gameOver.setX(WIDTH / 2.0 - 400);
        gameOver.setY(HEIGHT / 2.0 - 50);

        pane.getChildren().addAll(gameOver, score);
    }

    private static void renderWin() {
        renderScore();
        win = createText("YOU WIN!", 200);
        win.setX(WIDTH / 2.0 - 320);
        win.setY(HEIGHT / 2.0 - 50);

        pane.getChildren().addAll(win, score);
    }

    private static void renderScore() {
        int setBack;
        if (Main.score > 999) {
            setBack = 50;
        } else if (Main.score > 99) {
            setBack = 30;
        } else if (Main.score > 9) {
            setBack = 10;
        } else {
            setBack = -5;
        }
        score = createText("SCORE: " + Main.score, 100);
        score.setX(WIDTH / 2.0 - 150 - setBack);
        score.setY(HEIGHT / 2.0 + 50);
    }
}
