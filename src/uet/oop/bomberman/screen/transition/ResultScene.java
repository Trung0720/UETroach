package uet.oop.bomberman.screen.transition;

import javafx.animation.PauseTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.io.InputStream;

public class ResultScene {
    private static final int WIDTH = Main.CAMERA_WIDTH * Sprite.SCALED_SIZE;
    private static final int HEIGHT = Main.CAMERA_HEIGHT * Sprite.SCALED_SIZE;
    private static final String FONT_PATH = "/font/Pixel Game.otf";
    private static final String BACKGROUND_IMAGE = "/levels/blurred_background.png";
    private static final String LEVEL_UP_SOUND = "res/sound/level_up.mp3";
    private static final String GAME_OVER_SOUND = "res/sound/game_over.mp3";
    private static final String WIN_SOUND = "res/sound/win.mp3";
    private static Pane pane;
    public static Text levelUp, gameOver, win, score;
    private static final int TIME = 2;

    public static void renderScene(ResultType resultType) {
        pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);
        createBackGround();

        switch (resultType) {
            case LEVEL_UP -> renderLevelUp();
            case WIN -> renderWin();
            case LOSE -> renderGameOver();
        }

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

        InputStream fontStream = ResultScene.class.getResourceAsStream(FONT_PATH);
        if (fontStream != null) {
            text.setFont(Font.loadFont(fontStream, size));
        } else {
            System.out.println("After level scene font not found!");
            text.setFont(new Font("Arial", size));
        }

        return text;
    }

    private static void renderLevelUp() {
        Sound.playSoundTillEnd(LEVEL_UP_SOUND);
        levelUp = createText("LEVEL " + GameLoop.nextLevel, 200);
        levelUp.setX(WIDTH / 2.0 - 250);
        levelUp.setY(HEIGHT / 2.0);
        pane.getChildren().add(levelUp);
    }

    private static void renderGameOver() {
        Sound.playSoundTillEnd(GAME_OVER_SOUND);
        renderScore();
        gameOver = createText("GAME OVER!", 200);
        gameOver.setX(WIDTH / 2.0 - 400);
        gameOver.setY(HEIGHT / 2.0 - 50);

        pane.getChildren().addAll(gameOver, score);
    }

    private static void renderWin() {
        Sound.playSoundTillEnd(WIN_SOUND);
        renderScore();
        win = createText("YOU WIN!", 200);
        win.setX(WIDTH / 2.0 - 320);
        win.setY(HEIGHT / 2.0 - 50);

        pane.getChildren().addAll(win, score);
    }

    private static void renderScore() {
        int setBack;
        if (GameLoop.score > 999) {
            setBack = 50;
        } else if (GameLoop.score > 99) {
            setBack = 30;
        } else if (GameLoop.score > 9) {
            setBack = 10;
        } else {
            setBack = -5;
        }
        score = createText("SCORE: " + GameLoop.score, 100);
        score.setX(WIDTH / 2.0 - 150 - setBack);
        score.setY(HEIGHT / 2.0 + 50);
    }
}
