package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import uet.oop.bomberman.screen.AfterLevelScene;
import uet.oop.bomberman.screen.StatusBar;

public class GameLoop extends AnimationTimer {
    private final Stage stage;
    public static int gameStatus;
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

            if (gameStatus == 1) {
                update();
                render();
                StatusBar.updateStatusBar(now);
            } else if (gameStatus == 2 || gameStatus == 3) {
                gameStatus = 0;
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
        return;
    }

    public void render() {
        return;
    }
}
