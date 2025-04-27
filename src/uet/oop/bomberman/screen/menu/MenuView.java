package uet.oop.bomberman.screen.menu;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class MenuView {
    private static final int WIDTH = Main.CAMERA_WIDTH * Sprite.SCALED_SIZE;
    private static final int HEIGHT = Main.CAMERA_HEIGHT * Sprite.SCALED_SIZE;
    private static int buttonCount = 0;
    private final Stage stage;
    private final Scene scene;
    private final Group root;
    private final GameLoop gameLoop;
    private static AnchorPane pane;
    private MenuSubScene scoresSubScene;
    private MenuSubScene helpSubScene;
    private MenuSubScene creditsSubScene;
    private MenuSubScene sceneToHide;


    public enum menu {
        HELP,
        SCORES,
        CREDITS
    }

    public MenuView(Stage stage, Scene scene, Group root, GameLoop gameLoop) {
        this.stage = stage;
        this.scene = scene;
        this.root = root;
        this.gameLoop = gameLoop;
        menuButtons = new ArrayList<>();
        pane = new AnchorPane();
        createBackground();
        createLogo();
        createButtons();
        createSubScene();
        root.getChildren().add(pane);
    }

    public void showMenu() {
        pane.setVisible(true);
        if (!root.getChildren().contains(pane)) {
            root.getChildren().add(pane);
        }
    }

    private void createBackground() {
        Image backgroundImage = new Image("menu/background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        backgroundImageView.setFitWidth(WIDTH);
        backgroundImageView.setFitHeight(HEIGHT);
        backgroundImageView.setPreserveRatio(false);

        pane.getChildren().addFirst(backgroundImageView);
    }

    private void createLogo() {
        ImageView logo = new ImageView("menu/logo.png");
        logo.setLayoutX(205);
        logo.setLayoutY(50);
        logo.setFitWidth(941);
        logo.setFitHeight(171);

        logo.setOnMouseEntered(mouseEvent -> {
            logo.setEffect(new DropShadow());
        });
        logo.setOnMouseExited(mouseEvent -> {
            logo.setEffect(null);
        });

        pane.getChildren().add(logo);
    }

    private void createSubScene() {
        scoresSubScene = new MenuSubScene(menu.SCORES);
        helpSubScene = new MenuSubScene(menu.HELP);
        creditsSubScene = new MenuSubScene(menu.CREDITS);
        pane.getChildren().addAll(
                scoresSubScene,
                helpSubScene,
                creditsSubScene
        );
    }

    private void showSubScene(MenuSubScene menuSubScene) {
        if (sceneToHide != null && sceneToHide != menuSubScene) {
            sceneToHide.fadeOut();
        }
        if (sceneToHide != menuSubScene) {
            menuSubScene.fadeIn();
        }
        sceneToHide = menuSubScene;
    }

    private void addMenuButton(MenuButton menuButton) {
        menuButton.setLayoutX(0);
        menuButton.setLayoutY(260 + menuButtons.size() * 100);
        menuButtons.add(menuButton);
        pane.getChildren().add(menuButton);
    }

    private void createButtons() {
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton() {
        MenuButton startButton = new MenuButton("PLAY");
        addMenuButton(startButton);
    }

    private void createScoresButton() {
        MenuButton scoresButton = new MenuButton("SCORES");
        addMenuButton(scoresButton);

        scoresButton.setOnAction(actionEvent -> {
            showSubScene(scoresSubScene);
        });
    }

    private void createHelpButton() {
        MenuButton helpButton = new MenuButton("HELP");
        addMenuButton(helpButton);

        helpButton.setOnAction(actionEvent -> {
            showSubScene(helpSubScene);
        });
    }

    private void createCreditsButton() {
        MenuButton creditsButton = new MenuButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(actionEvent -> {
            showSubScene(creditsSubScene);
        });
    }

    private void createExitButton() {
        MenuButton exitButton = new MenuButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(actionEvent -> {
            gameLoop.stop();
            Platform.exit();
            System.exit(0);
        });
    }
}
