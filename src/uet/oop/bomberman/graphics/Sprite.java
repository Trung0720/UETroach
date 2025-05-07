package uet.oop.bomberman.graphics;

import javafx.scene.image.*;

import java.util.Arrays;

/**
 * Source code base.
 */
public class Sprite {

    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 4;

    /*
    |--------------------------------------------------------------------------
    | Board sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite portal = new Sprite(DEFAULT_SIZE, 16, 16, 4, 0, SpriteSheet.tiles);
    public static Sprite wall = new Sprite(DEFAULT_SIZE, 16, 16, 5, 0, SpriteSheet.tiles);
    public static Sprite grass = new Sprite(DEFAULT_SIZE, 16, 16, 6, 0, SpriteSheet.tiles);
    public static Sprite brick = new Sprite(DEFAULT_SIZE, 16, 16, 7, 0, SpriteSheet.tiles);
    public static Sprite brick_exploded_1 = new Sprite(DEFAULT_SIZE, 16, 16, 7, 1, SpriteSheet.tiles);
    public static Sprite brick_exploded_2 = new Sprite(DEFAULT_SIZE, 16, 16, 7, 2, SpriteSheet.tiles);
    public static Sprite brick_exploded_3 = new Sprite(DEFAULT_SIZE, 16, 16, 7, 3, SpriteSheet.tiles);
    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite player_up = new Sprite(DEFAULT_SIZE, 16, 16, 0, 0, SpriteSheet.tiles);
    public static Sprite player_down = new Sprite(DEFAULT_SIZE, 16, 16, 2, 0, SpriteSheet.tiles);
    public static Sprite player_left = new Sprite(DEFAULT_SIZE, 16, 16, 3, 0, SpriteSheet.tiles);
    public static Sprite player_right = new Sprite(DEFAULT_SIZE, 16, 16, 1, 0, SpriteSheet.tiles);
    public static Sprite player_up_1 = new Sprite(DEFAULT_SIZE, 16, 16, 0, 1, SpriteSheet.tiles);
    public static Sprite player_up_2 = new Sprite(DEFAULT_SIZE, 16, 16, 0, 2, SpriteSheet.tiles);
    public static Sprite player_down_1 = new Sprite(DEFAULT_SIZE, 16, 16, 2, 1, SpriteSheet.tiles);
    public static Sprite player_down_2 = new Sprite(DEFAULT_SIZE, 16, 16, 2, 2, SpriteSheet.tiles);
    public static Sprite player_left_1 = new Sprite(DEFAULT_SIZE, 16, 16, 3, 1, SpriteSheet.tiles);
    public static Sprite player_left_2 = new Sprite(DEFAULT_SIZE, 16, 16, 3, 2, SpriteSheet.tiles);
    public static Sprite player_right_1 = new Sprite(DEFAULT_SIZE, 16, 16, 1, 1, SpriteSheet.tiles);
    public static Sprite player_right_2 = new Sprite(DEFAULT_SIZE, 16, 16, 1, 2, SpriteSheet.tiles);
    public static Sprite player_dead_1 = new Sprite(DEFAULT_SIZE, 16, 16, 4, 2, SpriteSheet.tiles);
    public static Sprite player_dead_2 = new Sprite(DEFAULT_SIZE, 16, 16, 5, 2, SpriteSheet.tiles);
    public static Sprite player_dead_3 = new Sprite(DEFAULT_SIZE, 16, 16, 6, 2, SpriteSheet.tiles);
    /*
    |--------------------------------------------------------------------------
    | Enemies Sprites
    |--------------------------------------------------------------------------
     */
    // PINEAPPLE
    public static Sprite pineapple_left_1 = new Sprite(DEFAULT_SIZE, 16, 16, 9, 0, SpriteSheet.tiles);
    public static Sprite pineapple_left_2 = new Sprite(DEFAULT_SIZE, 16, 16, 9, 1, SpriteSheet.tiles);
    public static Sprite pineapple_right_1 = new Sprite(DEFAULT_SIZE, 16, 16, 10, 0, SpriteSheet.tiles);
    public static Sprite pineapple_right_2 = new Sprite(DEFAULT_SIZE, 16, 16, 10, 1, SpriteSheet.tiles);
    public static Sprite pineapple_dead = new Sprite(DEFAULT_SIZE, 16, 16, 9, 2, SpriteSheet.tiles);
    // CHICKEN
    public static Sprite chicken_left_1 = new Sprite(DEFAULT_SIZE, 16, 16, 11, 0, SpriteSheet.tiles);
    public static Sprite chicken_left_2 = new Sprite(DEFAULT_SIZE, 16, 16, 11, 1, SpriteSheet.tiles);
    public static Sprite chicken_right_1 = new Sprite(DEFAULT_SIZE, 16, 16, 12, 0, SpriteSheet.tiles);
    public static Sprite chicken_right_2 = new Sprite(DEFAULT_SIZE, 16, 16, 12, 1, SpriteSheet.tiles);
    public static Sprite chicken_dead = new Sprite(DEFAULT_SIZE, 16, 16, 11, 2, SpriteSheet.tiles);
    // STRAWBERRY
    public static Sprite strawberry_left_1 = new Sprite(DEFAULT_SIZE, 16, 16, 13, 0, SpriteSheet.tiles);
    public static Sprite strawberry_left_2 = new Sprite(DEFAULT_SIZE, 16, 16, 13, 1, SpriteSheet.tiles);
    public static Sprite strawberry_left_3 = new Sprite(DEFAULT_SIZE, 16, 16, 13, 2, SpriteSheet.tiles);
    public static Sprite strawberry_left_4 = new Sprite(DEFAULT_SIZE, 16, 16, 13, 3, SpriteSheet.tiles);
    public static Sprite strawberry_right_1 = new Sprite(DEFAULT_SIZE, 16, 16, 14, 0, SpriteSheet.tiles);
    public static Sprite strawberry_right_2 = new Sprite(DEFAULT_SIZE, 16, 16, 14, 1, SpriteSheet.tiles);
    public static Sprite strawberry_right_3 = new Sprite(DEFAULT_SIZE, 16, 16, 14, 2, SpriteSheet.tiles);
    public static Sprite strawberry_right_4 = new Sprite(DEFAULT_SIZE, 16, 16, 14, 3, SpriteSheet.tiles);
    public static Sprite strawberry_dead = new Sprite(DEFAULT_SIZE, 16, 16, 14, 4 , SpriteSheet.tiles);
    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb = new Sprite(DEFAULT_SIZE, 16, 16, 0, 3, SpriteSheet.tiles);
    public static Sprite bomb_1 = new Sprite(DEFAULT_SIZE, 16, 16, 1, 3, SpriteSheet.tiles);
    public static Sprite bomb_2 = new Sprite(DEFAULT_SIZE, 16, 16, 2, 3, SpriteSheet.tiles);
    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_exploded_1 = new Sprite(DEFAULT_SIZE, 16, 16, 0, 4, SpriteSheet.tiles);
    public static Sprite bomb_exploded_2 = new Sprite(DEFAULT_SIZE, 16, 16, 0, 5, SpriteSheet.tiles);
    public static Sprite bomb_exploded_3 = new Sprite(DEFAULT_SIZE, 16, 16, 0, 6, SpriteSheet.tiles);
    public static Sprite explosion_vertical_1 = new Sprite(DEFAULT_SIZE, 16, 16, 1, 5, SpriteSheet.tiles);
    public static Sprite explosion_vertical_2 = new Sprite(DEFAULT_SIZE, 16, 16, 2, 5, SpriteSheet.tiles);
    public static Sprite explosion_vertical_3 = new Sprite(DEFAULT_SIZE, 16, 16, 3, 5, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_1 = new Sprite(DEFAULT_SIZE, 16, 16, 1, 7, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_2 = new Sprite(DEFAULT_SIZE, 16, 16, 1, 8, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_3 = new Sprite(DEFAULT_SIZE, 16, 16, 1, 9, SpriteSheet.tiles);
    public static Sprite explosion_vertical_top_last_1 = new Sprite(DEFAULT_SIZE, 16, 16, 1, 4, SpriteSheet.tiles);
    public static Sprite explosion_vertical_top_last_2 = new Sprite(DEFAULT_SIZE, 16, 16, 2, 4, SpriteSheet.tiles);
    public static Sprite explosion_vertical_top_last_3 = new Sprite(DEFAULT_SIZE, 16, 16, 3, 4, SpriteSheet.tiles);
    public static Sprite explosion_vertical_down_last_1 = new Sprite(DEFAULT_SIZE, 16, 16, 1, 6, SpriteSheet.tiles);
    public static Sprite explosion_vertical_down_last_2 = new Sprite(DEFAULT_SIZE, 16, 16, 2, 6, SpriteSheet.tiles);
    public static Sprite explosion_vertical_down_last_3 = new Sprite(DEFAULT_SIZE, 16, 16, 3, 6, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_left_last_1 = new Sprite(DEFAULT_SIZE, 16, 16, 0, 7, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_left_last_2 = new Sprite(DEFAULT_SIZE, 16, 16, 0, 8, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_left_last_3 = new Sprite(DEFAULT_SIZE, 16, 16, 0, 9, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_right_last_1 = new Sprite(DEFAULT_SIZE, 16, 16, 2, 7, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_right_last_2 = new Sprite(DEFAULT_SIZE, 16, 16, 2, 8, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_right_last_3 = new Sprite(DEFAULT_SIZE, 16, 16, 2, 9, SpriteSheet.tiles);
    /*
    |--------------------------------------------------------------------------
    | PowerUps
    |--------------------------------------------------------------------------
     */
    public static Sprite powerup_bombs = new Sprite(DEFAULT_SIZE, 16, 16, 0, 10, SpriteSheet.tiles);
    public static Sprite powerup_flames = new Sprite(DEFAULT_SIZE, 16, 16, 1, 10, SpriteSheet.tiles);
    public static Sprite powerup_speed = new Sprite(DEFAULT_SIZE, 16, 16, 2, 10, SpriteSheet.tiles);
    public static Sprite powerup_wallpass = new Sprite(DEFAULT_SIZE, 16, 16, 3, 10, SpriteSheet.tiles);
    public static Sprite powerup_detonator = new Sprite(DEFAULT_SIZE, 16, 16, 4, 10, SpriteSheet.tiles);
    public static Sprite powerup_bombpass = new Sprite(DEFAULT_SIZE, 16, 16, 5, 10, SpriteSheet.tiles);
    public static Sprite powerup_flamepass = new Sprite(DEFAULT_SIZE, 16, 16, 6, 10, SpriteSheet.tiles);

    public final int SIZE;
    public int[] _pixels;
    protected int _realWidth;
    protected int _realHeight;
    private int _x, _y;
    private SpriteSheet _sheet;

    public Sprite(int SIZE, int _realWidth, int _realHeight, int _x, int _y, SpriteSheet _sheet) {
        this.SIZE = SIZE;
        this._pixels = new int[SIZE * SIZE];
        this._realWidth = _realWidth;
        this._realHeight = _realHeight;
        this._x = _x * SIZE;
        this._y = _y * SIZE;
        this._sheet = _sheet;
        load();
    }

    public Sprite(int size, int color) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    public static Sprite movingSprite(int animate, int time, Sprite... frames) {
        /*
        if (frames.length == 0 || time <= 0) return null;

        int frameCount = frames.length;
        int frameDuration = time / frameCount;
        if (frameDuration == 0) {
            frameDuration = 1;
        }
        int index = (animate / frameDuration / (frames.length / 2)) % frameCount;
        System.out.println("bb" + index);
        return frames[index];
         */

        if (frames.length == 0) return null;
        int index = (animate / (time / frames.length) / (frames.length / 2)) % frames.length;
        return frames[index];
    }

    public int getSIZE() {
        return SIZE;
    }

    public int get_pixels(int i) {
        return _pixels[i];
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                _pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
            }
        }
    }

    private void setColor(int color) {
        Arrays.fill(_pixels, color);
    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                pw.setArgb(x, y, _pixels[x + y * SIZE]);
            }
        }
        Image input = new ImageView(wr).getImage();
        return resample(input, SCALED_SIZE / DEFAULT_SIZE);
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }

        return output;
    }
}
