package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntitySetManagement;
import uet.oop.bomberman.entities.map.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity {
    public static int radius = 1;
    public static int animation = 0;
    private boolean passOver = true;
    public int timeToExplode = 0;
    private List<Flame> allFlame = new ArrayList<>();
    public static int bombNum = 1;
    private int keepTransforming = 0;
    private boolean exploded = false;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


    public boolean exploded() {
        return this.exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public int getKeepTransforming() {
        return keepTransforming;
    }

    public void setKeepTransforming(int keepTransforming) {
        this.keepTransforming = keepTransforming;
    }

    public void setAllFlame(List<Flame> allFlame) {
        this.allFlame = allFlame;
    }

    public int getBombNum() {
        return bombNum;
    }

    public void setBombNum(int bombNum) {
        this.bombNum = bombNum;
    }

    public boolean isPassOver() {
        return passOver;
    }

    public void setPassOver(boolean passOver) {
        this.passOver = passOver;
    }

    public void addFlame() {
        for (int i = 1; i <= radius; i++) {
            Flame flame = new Flame(this.x / Sprite.SCALED_SIZE, this.y / Sprite.SCALED_SIZE + i,
                    (i == radius) ?
                            Sprite.movingSprite(
                                    Bomb.animation, Sprite.SCALED_SIZE * 3 / 2,
                                    Sprite.explosion_vertical_down_last_1,
                                    Sprite.explosion_vertical_down_last_2,
                                    Sprite.explosion_vertical_down_last_3
                            ).getFxImage() :
                            Sprite.movingSprite(
                                    Bomb.animation, Sprite.SCALED_SIZE * 3 / 2,
                                    Sprite.explosion_vertical_1,
                                    Sprite.explosion_vertical_2,
                                    Sprite.explosion_vertical_3
                            ).getFxImage(), (i == radius) ? Flame.F_downLast : Flame.F_down
            );
            if (flame.checkBoundWall()) {
                break;
            }
            if (flame.checkFlameBrick()) {
                break;
            }
            this.allFlame.add(flame);
        }

        // up
        for (int i = 1; i <= radius; i++) {
            Flame flame = new Flame(this.x / Sprite.SCALED_SIZE, this.y / Sprite.SCALED_SIZE - i,
                    (i == radius) ?
                            Sprite.movingSprite(
                                    Bomb.animation, Sprite.SCALED_SIZE * 3 / 2,
                                    Sprite.explosion_vertical_top_last_1,
                                    Sprite.explosion_vertical_top_last_2,
                                    Sprite.explosion_vertical_top_last_3
                            ).getFxImage() :
                            Sprite.movingSprite(
                                    Bomb.animation, Sprite.SCALED_SIZE * 3 / 2,
                                    Sprite.explosion_vertical_1,
                                    Sprite.explosion_vertical_2,
                                    Sprite.explosion_vertical_3
                            ).getFxImage(), (i == radius) ? Flame.F_upLast : Flame.F_up
            );
            if (i == radius) {

            }
            if (flame.checkBoundWall()) {
                break;
            }
            if (flame.checkFlameBrick()) {
                break;
            }
            this.allFlame.add(flame);
        }

        for (int i = 1; i <= radius; i++) {
            Flame flame = new Flame(this.x / Sprite.SCALED_SIZE + i, this.y / Sprite.SCALED_SIZE,
                    (i == radius) ?
                            Sprite.movingSprite(
                                    Bomb.animation, Sprite.SCALED_SIZE * 3 / 2,
                                    Sprite.explosion_horizontal_right_last_1,
                                    Sprite.explosion_horizontal_right_last_2,
                                    Sprite.explosion_horizontal_right_last_3
                            ).getFxImage() :
                            Sprite.movingSprite(
                                    Bomb.animation, Sprite.SCALED_SIZE * 3 / 2,
                                    Sprite.explosion_horizontal_1,
                                    Sprite.explosion_horizontal_2,
                                    Sprite.explosion_horizontal_3
                            ).getFxImage(), (i == radius) ? Flame.F_rightLast : Flame.F_right
            );
            if (flame.checkBoundWall()) {
                break;
            }
            if (flame.checkFlameBrick()) {
                break;
            }
            this.allFlame.add(flame);
        }

        for (int i = 1; i <= radius; i++) {
            Flame flame = new Flame(this.x / Sprite.SCALED_SIZE - i, this.y / Sprite.SCALED_SIZE,
                    (i == radius) ?
                            Sprite.movingSprite(
                                    Bomb.animation, Sprite.SCALED_SIZE * 3 / 2,
                                    Sprite.explosion_horizontal_left_last_1,
                                    Sprite.explosion_horizontal_left_last_2,
                                    Sprite.explosion_horizontal_left_last_3
                            ).getFxImage() :
                            Sprite.movingSprite(
                                    Bomb.animation, Sprite.SCALED_SIZE * 3 / 2,
                                    Sprite.explosion_horizontal_1,
                                    Sprite.explosion_horizontal_2,
                                    Sprite.explosion_horizontal_3
                            ).getFxImage(), (i == radius) ? Flame.F_leftLast : Flame.F_left
            );
            if (flame.checkBoundWall()) {
                break;
            }
            if (flame.checkFlameBrick()) {
                break;
            }
            this.allFlame.add(flame);
        }
    }

    public List<Flame> getAllFlame() {
        return allFlame;
    }

    public void setTimeToExplode() {
        Timer timer = new Timer();
        try {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
//                    Sound.playSound("explosion", 1500);
                }
            }, 3010);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    EntitySetManagement.getEntitySetManagement().removeBomb();
                }
            }, 3500);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    EntitySetManagement.getEntitySetManagement().removeBrick();
                    EntitySetManagement.getEntitySetManagement().removeEnemies();
                }
            }, 3530);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update() {
        keepTransforming++;
        if (keepTransforming > 100) {
            keepTransforming = 0;
        }
        if (this.exploded) {
            // remove obstacle in map 0 -> grass
            Map.map2D[this.getY() / Sprite.SCALED_SIZE][this.getX() / Sprite.SCALED_SIZE] = ' ';
            this.setImg(
                    Sprite.movingSprite(
                            90, Sprite.SCALED_SIZE,
                            Sprite.bomb_exploded_1,
                            Sprite.bomb_exploded_2,
                            Sprite.bomb_exploded_3
                    ).getFxImage());
            if (this.timeToExplode == 1) {
                this.timeToExplode++;
                this.addFlame();
            }
        } else {
            if (this.timeToExplode == 0) {
                this.timeToExplode++;
                setTimeToExplode();
            }
            this.setImg(
                    Sprite.movingSprite(
                            keepTransforming, 90,
                            Sprite.bomb,
                            Sprite.bomb_1,
                            Sprite.bomb_2
                    ).getFxImage());
        }
        if (this.exploded) {
            if (animation >= Sprite.SCALED_SIZE * 3 / 2) {
                animation = 0;
            }
            animation++;
            setImg(Sprite.movingSprite(
                    animation, Sprite.SCALED_SIZE * 3 / 2,
                    Sprite.bomb_exploded_1,
                    Sprite.bomb_exploded_2,
                    Sprite.bomb_exploded_3
            ).getFxImage());
        }
    }
}