package uet.oop.bomberman.camera;

import uet.oop.bomberman.Main;

public class CameraTranslate {
    public static void moveCamera(int x, int y) {
        Main.graphicsContext.translate(-x, -y);
        Main.cameraX -= x;
        Main.cameraY -= y;
    }
}
