package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.screen.menu.MenuView;

public class Main extends Application {
    public static int WIDTH = 31;
    public static int HEIGHT = 13;
    public static int CAMERA_WIDTH = 21;
    public static int CAMERA_HEIGHT = 13;
    public static int STATUS_BAR_HEIGHT = 32;
    public static final String ICON = "sprites/Bomber/koala_down.png";
    public static Group root = new Group();
    public static Canvas canvas;
    public static GraphicsContext graphicsContext;


    public static void main(String[] args) {
        Application.launch(Main.class);
    }

    public void start(Stage stage) {
        stage.setResizable(false);
        stage.getIcons().add(new Image("sprites/Bomber/koala_down.png"));

        Scene scene = new Scene(
                root,
                Sprite.SCALED_SIZE * WIDTH,
                Sprite.SCALED_SIZE * HEIGHT
        );
        stage.setScene(scene);
        stage.show();

        canvas = new Canvas(
                Sprite.SCALED_SIZE * WIDTH,
                Sprite.SCALED_SIZE * HEIGHT
        );
        graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        GameLoop gameLoop = new GameLoop(stage);
        gameLoop.start();

        MenuView menuView = new MenuView(stage, scene, root, gameLoop);
    }
}
