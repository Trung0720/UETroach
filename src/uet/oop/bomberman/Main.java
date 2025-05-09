package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.screen.StatusBar;
import uet.oop.bomberman.screen.menu.MenuView;
import uet.oop.bomberman.sound.Sound;

public class Main extends Application {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int CAMERA_WIDTH = 21;
    public static final int CAMERA_HEIGHT = 13;
    public static final int STATUS_BAR_HEIGHT = 32;
    public static final String ICON = "/sprites/Bomber/koala_down.png";
    public static final String BACKGROUND_MUSIC = "res/sound/back_ground_sound.wav";
    public static int cameraX = 0;
    public static int cameraY = 0;
    public static Scene scene;
    public static Group root = new Group();
    public static Canvas canvas;
    public static GraphicsContext graphicsContext;
    public static MenuView menuView;


    public static void main(String[] args) {
        Application.launch(Main.class);
    }

    public void start(Stage stage) {
        stage.setResizable(false);
        stage.getIcons().add(new Image(ICON));

        scene = new Scene(
                root,
                Sprite.SCALED_SIZE * CAMERA_WIDTH,
                Sprite.SCALED_SIZE * CAMERA_HEIGHT
        );
        stage.setScene(scene);
        stage.show();

        canvas = new Canvas(
                Sprite.SCALED_SIZE * WIDTH,
                Sprite.SCALED_SIZE * HEIGHT
        );
        graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        StatusBar.createStatusBar(root);

        Sound.playSoundForever(BACKGROUND_MUSIC);

        GameLoop gameLoop = new GameLoop(stage);
        gameLoop.start();

        menuView = new MenuView(root, gameLoop);
    }
}
