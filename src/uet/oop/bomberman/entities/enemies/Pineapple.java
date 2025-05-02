package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
//Balloom
public class Pineapple extends Enemy {
    public Pineapple(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void getImage() {
        if (this.getSpeedX() > 0) {
            this.img = Sprite.movingSprite(
                    this.x, Sprite.DEFAULT_SIZE,
                    Sprite.pineapple_right_1,
                    Sprite.pineapple_right_2).getFxImage();
        } else if (this.getSpeedX() < 0) {
            this.img = Sprite.movingSprite(
                    this.x, Sprite.DEFAULT_SIZE,
                    Sprite.pineapple_left_1,
                    Sprite.pineapple_left_2).getFxImage();
        } else if (this.getSpeedY() > 0) {
            this.img = Sprite.movingSprite(
                    this.x, Sprite.DEFAULT_SIZE,
                    Sprite.pineapple_right_1,
                    Sprite.pineapple_right_2).getFxImage();
        } else {
            this.img = Sprite.movingSprite(
                    this.x, Sprite.DEFAULT_SIZE,
                    Sprite.pineapple_left_1,
                    Sprite.pineapple_left_2).getFxImage();
        }
    }

    @Override
    public void update() {
        super.update();
        super.setSpeed(2);
        keepMoving++;
        if (keepMoving > 500) {
            keepMoving = 0;
        }
        if (this.isAlive()) {
            randomMove();
            getImage();
        } else {
            this.setImg(Sprite.pineapple_dead.getFxImage());
        }
    }
}