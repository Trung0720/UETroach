package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.EntitySetManagement;
import uet.oop.bomberman.entities.enemies.search.AStar;
import uet.oop.bomberman.entities.enemies.search.Search;
import uet.oop.bomberman.entities.map.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

//Oneal
public class Chicken extends Enemy {
    private static final String CHICKEN_DEATH_SOUND = "res/sound/dead_chicken.mp3";
    private int slow = 0;
    private int keepMoving = 0;

    public Chicken(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


    @Override
    public void goUp() {
        for (int i = 1; i <= this.getSpeed(); i++) {
            this.y--;
            if (checkBoundBomb() || checkBoundBrick() || checkBoundWall()) {
                this.y++;
                break;
            }
        }
        setImg(Sprite.movingSprite(
                keepMoving, 60,
                Sprite.chicken_right_1,
                Sprite.chicken_right_2).getFxImage());
    }

    @Override
    public void goRight() {
        for (int i = 1; i <= this.getSpeed(); i++) {
            this.x++;
            if (checkBoundBrick() || checkBoundWall() || checkBoundBomb()) {
                this.x--;
                break;
            }
        }
        setImg(Sprite.movingSprite(
                keepMoving, 60,
                Sprite.chicken_right_1,
                Sprite.chicken_right_2).getFxImage());
    }

    @Override
    public void goLeft() {
        for (int i = 1; i <= this.getSpeed(); i++) {
            this.x--;
            if (checkBoundBomb() || checkBoundBrick() || checkBoundWall()) {
                this.x++;
                break;
            }
        }
        setImg(Sprite.movingSprite(
                keepMoving, 60,
                Sprite.chicken_left_1,
                Sprite.chicken_left_2).getFxImage());
    }

    @Override
    public void goDown() {
        for (int i = 1; i <= this.getSpeed(); i++) {
            this.y++;
            if (checkBoundBomb() || checkBoundBrick() || checkBoundWall()) {
                this.y--;
                break;
            }
        }
        setImg(Sprite.movingSprite(
                keepMoving, 60,
                Sprite.chicken_left_1,
                Sprite.chicken_left_2).getFxImage());
    }

    public Search.Position nextPosition(int row, int col) {
        return AStar.aStarSearch(Map.map2D,
                new Search.Position(this.y / Sprite.SCALED_SIZE, this.x / Sprite.SCALED_SIZE),
                new Search.Position(row, col));
    }

    public void move(Search.Position pair) {
        int tmpX = pair.getCol() * Sprite.SCALED_SIZE;
        int tmpY = pair.getRow() * Sprite.SCALED_SIZE;

        if (this.y < tmpY) {
            if (slow % 2 == 0) {
                goDown();
            }
        }
        if (this.y > tmpY) {
            if (slow % 2 == 0) {
                goUp();
            }
        }
        if (this.x > tmpX) {
            if (slow % 2 == 0) {
                goLeft();
            }
        }
        if (this.x < tmpX) {
            if (slow % 2 == 0) {
                goRight();
            }
        }

        if (!this.isAlive()) {
            setImg(Sprite.chicken_dead.getFxImage());
        }
    }

    public void moveFree() {
        this.randomDirection();
        if (this.getSpeedX() > 0) {
            goRight();
        } else if (this.getSpeedX() < 0) {
            goLeft();
        } else if (this.getSpeedY() > 0) {
            goUp();
        } else if (this.getSpeedY() < 0) {
            goDown();
        }
    }


    @Override
    public void update() {
        if (!this.isAlive()) {
            if (deathCount == 0) {
                Sound.playSoundForRate(CHICKEN_DEATH_SOUND, 1.5);
                deathCount = 1;
            }
        }
        checkBomber();

        keepMoving = (keepMoving + 1) % 101;

        int destRow = EntitySetManagement.getEntitySetManagement().getBomberMan().getY() / Sprite.SCALED_SIZE;
        int destCol = EntitySetManagement.getEntitySetManagement().getBomberMan().getX() / Sprite.SCALED_SIZE;

        Search.Position bomber = nextPosition(destRow, destCol);
        slow = (slow + 1) % 101;

        if (this.x == bomber.getCol() * Sprite.SCALED_SIZE && this.y == bomber.getRow() * Sprite.SCALED_SIZE) {
            moveFree();
        } else {
            move(bomber);
        }
    }
}

