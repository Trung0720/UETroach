package uet.oop.bomberman.camera;

import uet.oop.bomberman.Main;

public class CameraTranslate {
    public static void moveCamera(int x, int y) {
        Main.graphicsContext.clearRect(0, 0,
                Main.canvas.getWidth(),
                Main.canvas.getHeight()
        );
        Main.graphicsContext.translate(-x, -y);
        Main.cameraX -= x;
        Main.cameraY -= y;
    }
}
