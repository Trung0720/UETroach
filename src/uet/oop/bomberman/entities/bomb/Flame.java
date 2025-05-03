package uet.oop.bomberman.entities.bomb;

import  javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntitySetManagement;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.map.mapblock.Brick;
import uet.oop.bomberman.entities.map.mapblock.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    /*
    1 : up
    2 : down
    3 : right
    4 : left
    5 : up last
    6 : down last
    7 : right last
    8 : left last
     */
    public static final int F_up = 1;
    public static final int F_down = 2;
    public static final int F_right = 3;
    public static final int F_left = 4;
    public static final int F_upLast = 5;
    public static final int F_downLast = 6;
    public static final int F_rightLast = 7;
    public static final int F_leftLast = 8;

    private int direction = -1;

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Flame(int xUnit, int yUnit, Image img, int direction) {
        super(xUnit, yUnit, img);
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void update() {
        checkFlameBrick();
        destroyFlameEnemy();
        checkFlameBomber();
        checkFlameOtherBomb();
        updateFlameImage();
    }

    private void updateFlameImage() {
        Sprite[] frames;
        switch (direction) {
            case F_up:
            case F_down:
                frames = new Sprite[]{
                        Sprite.explosion_vertical_1,
                        Sprite.explosion_vertical_2,
                        Sprite.explosion_vertical_3
                };
                break;
            case F_right:
            case F_left:
                frames = new Sprite[]{
                        Sprite.explosion_horizontal_1,
                        Sprite.explosion_horizontal_2,
                        Sprite.explosion_horizontal_3
                };
                break;
            case F_upLast:
                frames = new Sprite[]{
                        Sprite.explosion_vertical_top_last_1,
                        Sprite.explosion_vertical_top_last_2,
                        Sprite.explosion_vertical_top_last_3
                };
                break;
            case F_downLast:
                frames = new Sprite[]{
                        Sprite.explosion_vertical_down_last_1,
                        Sprite.explosion_vertical_down_last_2,
                        Sprite.explosion_vertical_down_last_3
                };
                break;
            case F_rightLast:
                frames = new Sprite[]{
                        Sprite.explosion_horizontal_right_last_1,
                        Sprite.explosion_horizontal_right_last_2,
                        Sprite.explosion_horizontal_right_last_3
                };
                break;
            case F_leftLast:
                frames = new Sprite[]{
                        Sprite.explosion_horizontal_left_last_1,
                        Sprite.explosion_horizontal_left_last_2,
                        Sprite.explosion_horizontal_left_last_3
                };
                break;
            default:
                return;
        }
        setImg(Sprite.movingSprite(Bomb.animation, Sprite.SCALED_SIZE * 3 / 2, frames).getFxImage());
    }

    public boolean checkFlameBrick() {
        for (Brick brick : EntitySetManagement.getEntitySetManagement().getBrickList()) {
            if (this.intersect(brick)) {
                brick.setBroken(true);
                return true;
            }
        }
        return false;
    }

    public boolean checkFlameWall() {
        for (Wall wall : EntitySetManagement.getEntitySetManagement().getWallList()) {
            if (this.intersect(wall)) {
                return true;
            }
        }
        return false;
    }

    public void destroyFlameEnemy() {
        for (Enemy enemy : EntitySetManagement.getEntitySetManagement().getEnemyList()) {
            if (this.intersect(enemy) || enemy.checkBoundBombExplosion()) {
                enemy.setAlive(false);
            }
        }
    }

    public void checkFlameBomber() {
        if (this.intersect(EntitySetManagement.getEntitySetManagement().getBomberMan())) {
            EntitySetManagement.getEntitySetManagement().getBomberMan().setAlive(false);
        }
        for (Bomb bomb : EntitySetManagement.getEntitySetManagement().getBombList()) {
            if (bomb.intersect(EntitySetManagement.getEntitySetManagement().getBomberMan()) && bomb.exploded()) {
                EntitySetManagement.getEntitySetManagement().getBomberMan().setAlive(false);
            }
        }
    }

    public boolean checkFlameOtherBomb() {
        for (Bomb bomb : EntitySetManagement.getEntitySetManagement().getBombList()) {
            if (bomb.intersect(this)) {
                bomb.setExploded(true);
                // max number of bomb is 2 so i can return now;
                return true;
            }
        }
        return false;
    }
}
