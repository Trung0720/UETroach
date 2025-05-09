package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.camera.CameraTranslate;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.map.mapblock.Brick;
import uet.oop.bomberman.entities.map.mapblock.Wall;
import uet.oop.bomberman.graphics.Sprite;



public abstract class Entity {
    public int animate;
    protected int x;
    protected int y;
    protected Image img;

    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.animate = 60;
    }

    public static boolean checkIntersectDeep(Rectangle2D a, Rectangle2D b) {
        return a != null && b != null &&
                a.getMaxX() > b.getMinX() &&
                a.getMaxY() > b.getMinY() &&
                a.getMinX() < b.getMaxX() &&
                a.getMinY() < b.getMaxY();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setAnimate(int animate) {
        this.animate = animate;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public boolean intersect(Entity other) {
        return checkIntersectDeep(other.getBoundary(), this.getBoundary());
    }

    public boolean checkBoundBrick() {
        for (Brick brick : EntitySetManagement.getEntitySetManagement().getBrickList()) {
            if (this.intersect(brick)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoundWall() {
        for (Wall wall : EntitySetManagement.getEntitySetManagement().getWallList()) {
            if (this.intersect(wall)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoundBomb() {
        for (Bomb bomb : EntitySetManagement.getEntitySetManagement().getBombList()) {
            if (this.intersect(bomb)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoundBombExplosion() {
        for (Bomb bomb : EntitySetManagement.getEntitySetManagement().getBombList()) {
            if (this.intersect(bomb) && bomb.exploded()) {
                return true;
            }
        }
        return false;
    }

    public void roundPosition(int directionUp, int directionRight) {
        int remainX = this.x % Sprite.SCALED_SIZE;
        int remainY = this.y % Sprite.SCALED_SIZE;

        if (directionUp == 1) {
            this.y -= remainY;
        } else if (directionUp == 0) {
            this.y += (Sprite.SCALED_SIZE - remainY);
        }

        if (directionRight == 1) {
            this.x += (Sprite.SCALED_SIZE - remainX);
        } else if (directionRight == 0) {
            this.x -= remainX;
        }
    }

    public boolean roundVertical() {
        int oldY = this.y;
        int mod = this.y % Sprite.SCALED_SIZE;

        if (mod > 2 * Sprite.SCALED_SIZE / 3) {
            this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
        } else if (mod < Sprite.SCALED_SIZE / 3) {
            this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE);
        }

        return oldY != this.y;
    }

    public boolean roundHorizontal() {
        int oldX = this.x;
        int mod = this.x % Sprite.SCALED_SIZE;

        if (mod > 2 * Sprite.SCALED_SIZE / 3) {
            this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
        } else if (mod < Sprite.SCALED_SIZE / 3) {
            this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE);
        }

        int newX = this.x;

        if (2 * newX > (Main.CAMERA_WIDTH - 1) * Sprite.SCALED_SIZE
                && newX < (Main.WIDTH - (Main.CAMERA_WIDTH + 1) / 2) * Sprite.SCALED_SIZE) {
            CameraTranslate.moveCamera(newX - oldX, 0);
        }

        return oldX != newX;
    }


    public Rectangle getRect() {
        return new Rectangle(
                this.x * Sprite.SCALED_SIZE / 2,
                this.y * Sprite.SCALED_SIZE / 2,
                Sprite.SCALED_SIZE,
                Sprite.SCALED_SIZE);
    }
}
