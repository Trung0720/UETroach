package uet.oop.bomberman.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {
    public static SpriteSheet tiles = new SpriteSheet(256, "/textures/classic.png");

    public final int SIZE;
    private final String _path;
    public int[] _pixels;
    public BufferedImage image;

    public SpriteSheet(int SIZE, String _path) {
        this.SIZE = SIZE;
        this._path = _path;
        _pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            URL a = SpriteSheet.class.getResource(_path);
            image = ImageIO.read(a);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, _pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
