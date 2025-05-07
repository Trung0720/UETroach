package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Strawberry extends Enemy {
    public Strawberry(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void getImage() {
        if (this.getSpeedX() > 0) {
            this.img = Sprite.movingSprite(
                    this.x, Sprite.DEFAULT_SIZE,
                    Sprite.strawberry_right_1,
                    Sprite.strawberry_right_2,
                    Sprite.strawberry_right_3,
                    Sprite.strawberry_right_4).getFxImage();
        } else if (this.getSpeedX() < 0) {
            this.img = Sprite.movingSprite(
                    this.x, Sprite.DEFAULT_SIZE,
                    Sprite.strawberry_left_1,
                    Sprite.strawberry_left_2,
                    Sprite.strawberry_left_3,
                    Sprite.strawberry_left_4).getFxImage();
        } else if (this.getSpeedY() > 0) {
            this.img = Sprite.movingSprite(
                    this.y, Sprite.DEFAULT_SIZE,
                    Sprite.strawberry_right_1,
                    Sprite.strawberry_right_2,
                    Sprite.strawberry_right_3,
                    Sprite.strawberry_right_4).getFxImage();
        } else {
            this.img = Sprite.movingSprite(
                    this.y, Sprite.DEFAULT_SIZE,
                    Sprite.strawberry_left_1,
                    Sprite.strawberry_left_2,
                    Sprite.strawberry_left_3,
                    Sprite.strawberry_left_4).getFxImage();
        }
    }

    @Override
    public void update() {
        super.update();
        super.setSpeed(4);
        keepMoving++;
        if (keepMoving > 500) {
            keepMoving = 0;
        }
        if (this.isAlive()) {
            randomMove();
            getImage();
        } else {
            this.setImg(Sprite.strawberry_dead.getFxImage());
        }
    }
}
