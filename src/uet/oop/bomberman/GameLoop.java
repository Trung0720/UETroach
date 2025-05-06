package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.camera.CameraTranslate;
import uet.oop.bomberman.controller.PlayerController;
import uet.oop.bomberman.entities.EntitySetManagement;
import uet.oop.bomberman.entities.map.Map;
import uet.oop.bomberman.screen.StatusBar;
import uet.oop.bomberman.screen.afterlevel.ResultScene;
import uet.oop.bomberman.screen.afterlevel.ResultType;

public class GameLoop extends AnimationTimer {
    public static final int STATUS_PLAYING = 1;
    public static final int STATUS_GAME_OVER = 2;
    public static final int STATUS_TRANSITION = 3;
    private final Stage stage;
    public static EntitySetManagement entitySetManagement = EntitySetManagement.getEntitySetManagement();
    public static int gameStatus = 0;
    public static int score = 0;
    public static int currentLevel = 0;
    public static int nextLevel = 1;
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;
    private long lastUpdate = 0;
    private final long frameDuration = 1_000_000_000 / 60;

    public GameLoop(Stage stage) {
        this.stage = stage;
    }

    public void handle(long now) {
        if (lastUpdate == 0) {
            lastUpdate = now;
            return;
        }

        if (now - lastUpdate >= frameDuration) {
            lastUpdate += frameDuration;
            stage.setTitle(calculateFPSandSCORE(now));

            if (gameStatus == STATUS_PLAYING) {
                if (currentLevel < nextLevel) {
                    gameStatus = STATUS_TRANSITION;
                    ResultScene.renderScene(ResultType.LEVEL_UP);

                    PauseTransition delay = new PauseTransition(Duration.seconds(2));
                    delay.setOnFinished(actionEvent -> {
                        levelUp(Main.scene);
                        gameStatus = STATUS_PLAYING;
                    });

                    delay.play();
                    return;

                    /*
                    ResultScene.renderScene(ResultType.LEVEL_UP);
                    levelUp(Main.scene);
                     */
                }
                update();
                render();
                StatusBar.updateStatusBar(now);
            } else if (gameStatus == STATUS_GAME_OVER) {
                ResultScene.renderScene(ResultType.LOSE);
                gameStatus = STATUS_TRANSITION;
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(actionEvent -> restart());
                delay.play();
            }
        }
    }

    /**
     * Calculate FPS.
     * ChatGPT.
     * @return game FPS
     */
    public String calculateFPSandSCORE(long now) {
        long oldFrameTime = frameTimes[frameTimeIndex];
        frameTimes[frameTimeIndex] = now;
        frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
        if (frameTimeIndex == 0) {
            arrayFilled = true;
        }
        if (arrayFilled) {
            long elapsedNanos = now - oldFrameTime;
            long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
            double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame;
            return "Bomberman     " + String.format(" FPS : %.0f", frameRate);
        }
        return "Bomberman";
    }

    public void update() {
        entitySetManagement.updateALl();
    }

    public void render() {
        entitySetManagement.renderAll(Main.graphicsContext);
    }

    public static void restart() {
        entitySetManagement = EntitySetManagement.getEntitySetManagement();
        currentLevel = 0;
        nextLevel = 1;
        Main.menuView.showMenu();
        gameStatus = 0;
    }

    public static void levelUp(Scene scene) {
        if (nextLevel < 4) {
            entitySetManagement.clearAll();
            Map.createMapByLevel(nextLevel);
            currentLevel = nextLevel;

            Main.cameraX = 0;
            Main.cameraY = 0;
            CameraTranslate.moveCamera(Main.cameraX, Main.cameraY);

            PlayerController.playerControl(scene, entitySetManagement.getBomberMan(), entitySetManagement);
        } else {
            ResultScene.renderScene(ResultType.WIN);
            restart();
        }
    }
}
