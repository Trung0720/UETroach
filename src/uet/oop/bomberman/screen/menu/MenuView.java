package uet.oop.bomberman.screen.menu;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuView {
    private static final int WIDTH = Main.CAMERA_WIDTH * Sprite.SCALED_SIZE;
    private static final int HEIGHT = Main.CAMERA_HEIGHT * Sprite.SCALED_SIZE;
    private static final String BACKGROUND = "menu/background.png";
    private static int buttonCount = 0;
    private final Group root;
    private final GameLoop gameLoop;
    private static Pane pane;
    private final MenuSubScene scoresSubScene = new MenuSubScene(SubsceneType.SCORES);
    private final MenuSubScene helpSubScene = new MenuSubScene(SubsceneType.HELP);
    private final MenuSubScene creditsSubScene = new MenuSubScene(SubsceneType.CREDITS);
    private MenuSubScene sceneToHide;
    private final MenuButton startButton = new MenuButton("START");
    private final MenuButton scoresButton = new MenuButton("SCORES");
    private final MenuButton helpButton = new MenuButton("HELP");
    private final MenuButton creditsButton = new MenuButton("CREDITS");
    private final MenuButton exitButton = new MenuButton("EXIT");
    private final ArrayList<MenuButton> buttonList = new ArrayList<>(
            Arrays.asList(startButton, scoresButton, helpButton, creditsButton, exitButton)
    );

    public MenuView(Group root, GameLoop gameLoop) {
        this.root = root;
        this.gameLoop = gameLoop;
        pane = new Pane();

        pane.getChildren().addAll(
                scoresSubScene,
                helpSubScene,
                creditsSubScene
        );

        createBackground();
        createLogo();
        createButtons();
        root.getChildren().add(pane);
    }

    public void showMenu() {
        pane.setVisible(true);
        if (!root.getChildren().contains(pane)) {
            root.getChildren().add(pane);
        }
    }
    private void createBackground() {
        Image backgroundImage = new Image(BACKGROUND);
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

    private void showSubScene(MenuSubScene menuSubScene) {
        if (sceneToHide != null) {
            sceneToHide.fadeOut();
        }

        if (menuSubScene == scoresSubScene) {
            scoresSubScene.updateScoresContent();
        }

        if (sceneToHide != menuSubScene) {
            menuSubScene.fadeIn();
            sceneToHide = menuSubScene;
        } else {
            sceneToHide = null;
        }

    }

    private void createButtons() {
        buttonList.forEach(menuButton -> {
            menuButton.setLayoutX(0);
            menuButton.setLayoutY(260 + buttonCount * 100);
            buttonCount++;
            pane.getChildren().add(menuButton);
        });

        startButton.setOnAction(actionEvent -> {
            pane.setVisible(false);
            GameLoop.gameStatus = 1;
        });

        scoresButton.setOnAction(actionEvent -> {
            showSubScene(scoresSubScene);
        });

        helpButton.setOnAction(actionEvent -> {
            showSubScene(helpSubScene);
        });

        creditsButton.setOnAction(actionEvent -> {
            showSubScene(creditsSubScene);
        });

        exitButton.setOnAction(actionEvent -> {
            Sound.stopAll();
            gameLoop.stop();
            Platform.exit();
            System.exit(0);
        });
    }
}
