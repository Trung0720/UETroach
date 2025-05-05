package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntitySetManagement;
import uet.oop.bomberman.entities.Move;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Entity implements Move {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    protected int keepMoving = 0;
    protected int deathCount = 0;
    private int speed = 4;
    private int speedX = 0;
    private int speedY = 0;
    private boolean isAlive = true;
    private int lastDirection = -1;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void randomDirection() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case RIGHT:
                this.speedX = this.getSpeed();
                this.speedY = 0;
                break;
            case LEFT:
                this.speedX = this.getSpeed() * -1;
                this.speedY = 0;
                break;
            case UP:
                this.speedX = 0;
                this.speedY = this.getSpeed();
                break;
            case DOWN:
                this.speedX = 0;
                this.speedY = this.getSpeed() * -1;
                break;
        }
    }

    public void betterDirection() {
        Random random = new Random();
        int[] directions = {UP, DOWN, LEFT, RIGHT};

        List<Integer> valid = new ArrayList<>();
        for (int dir : directions) {
            if (dir == oppositeDirection(lastDirection)) {
                continue;
            }
            int tmpX = x, tmpY = y;
            int tmpSpeedX = 0, tmpSpeedY = 0;
            switch (dir) {
                case RIGHT -> tmpSpeedX = this.getSpeed();
                case LEFT -> tmpSpeedX = -this.getSpeed();
                case UP -> tmpSpeedY = this.getSpeed();
                case DOWN -> tmpSpeedY = -this.getSpeed();
            }
            x += tmpSpeedX;
            y += tmpSpeedY;

            boolean blocked = checkBoundBomb() || checkBoundBrick() || checkBoundWall();

            x = tmpX;
            y = tmpY;
            if (!blocked) {
                valid.add(dir);
            }
        }
        if (!valid.isEmpty()) {
            int choose = valid.get(random.nextInt(valid.size()));
            switch (choose) {
                case RIGHT -> {
                    this.speedX = getSpeed();
                    this.speedY = 0;
                }
                case LEFT -> {
                    this.speedX = -getSpeed();
                    this.speedY = 0;
                }
                case UP -> {
                    this.speedX = 0;
                    this.speedY = getSpeed();
                }
                case DOWN -> {
                    this.speedX = 0;
                    this.speedY = -getSpeed();
                }
            }
            lastDirection = choose;
        } else {
            randomDirection();
        }
    }

    private int oppositeDirection(int direction) {
        return switch (direction) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            default -> -1;
        };
    }

    public void randomSpeed() {
        Random random = new Random();
        int speed = random.nextInt(3) + 2;
    }

    public void checkBomber() {
        if (this.intersect(EntitySetManagement.getEntitySetManagement().getBomberMan())) {
            EntitySetManagement.getEntitySetManagement().getBomberMan().setAlive(false);
        }
    }

    public void randomMove() {
        if (this.speedX == 0) {
            this.y += this.getSpeedY();
            if (checkBoundWall() || checkBoundBrick() || checkBoundBomb() || getY() % Sprite.SCALED_SIZE == 0) {
                if (getY() % Sprite.SCALED_SIZE != 0) {
                    this.y -= this.getSpeedY();
                }
                betterDirection();
            }
        } else {
            this.x += this.getSpeedX();
            if (checkBoundBomb() || checkBoundBrick() || checkBoundWall() || getX() % Sprite.SCALED_SIZE == 0) {
                if (getX() % Sprite.SCALED_SIZE != 0) {
                    this.x -= this.getSpeedX();
                }
                betterDirection();
            }
        }
    }

    @Override
    public void update() {
        if (!this.isAlive) {
            if (deathCount == 0) {
//                Sound.playSound("enemyDeath", 1500);
                deathCount = 1;
            }
        }
        checkBomber();
    }

    @Override
    public void goUp() {

    }

    @Override
    public void goRight() {

    }

    @Override
    public void goLeft() {

    }

    @Override
    public void goDown() {

    }
}