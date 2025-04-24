package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class GameLoop extends AnimationTimer {
    private final Stage stage;
    public static int gameStart;
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

            if (gameStart == 1) {
                update();
                render();
            } else if (gameStart == 2 || gameStart == 3) {
                gameStart = 0;
            }
        }
    }

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
