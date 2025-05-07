package uet.oop.bomberman.entities.player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.camera.CameraTranslate;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntitySetManagement;
import uet.oop.bomberman.entities.Move;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.map.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.Timer;
import java.util.TimerTask;


public class Bomber extends Entity implements Move {
    private static final String BOMB_PLANTED_SOUND = "res/sound/bomb_planted.wav";
    private static final String DEATH_SOUND = "res/sound/player_death.mp3";
    private int speed = 2;
    private boolean isAlive = true;
    private boolean deathHandled = false;
    private int keepMoving = 0;
    private int numeberOfBomb = 1;
    private int countDeathUpdate = 1;

    public Bomber(int xUnit, int yUnit, Image image) {
        super(xUnit, yUnit, image);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getNumberOfBomb() {
        return numeberOfBomb;
    }

    public void setNumberOfBomb(int numeberOfBomb) {
        this.numeberOfBomb = numeberOfBomb;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    @Override
    public void goUp() {
        boolean moved = false;
        for (int i = 1; i <= this.speed; i++) {
            this.y -= 1;
            if (checkBoundBomb() || checkBoundBrick() || checkBoundWall()) {
                this.y += 1;
                super.roundHorizontal();
                break;
            }
            moved = true;
        }
        if (moved) {
            keepMoving = (keepMoving + this.speed) % 108;
            setImg(Sprite.movingSprite(
                    keepMoving, 36,
                    Sprite.player_up,
                    Sprite.player_up_1,
                    Sprite.player_up_2).getFxImage());
        }
    }

    @Override
    public void goRight() {
        for (int i = 1; i <= this.speed; i++) {
            if (canMoveCamera()) {
                CameraTranslate.moveCamera(1, 0);
            }
            if (this.x == (Main.CAMERA_WIDTH - 1) / 2 * Sprite.SCALED_SIZE) {
                CameraTranslate.moveCamera(1, 0);
            }
            this.x += 1;
            if (checkBoundBrick() || checkBoundBomb() || checkBoundWall()) {
                if (canMoveCamera()) {
                    CameraTranslate.moveCamera(-1, 0);
                }
                this.x -= 1;
                super.roundVertical();
                break;
            }
        }
        keepMoving = (keepMoving + this.speed) % 108;
        setImg(Sprite.movingSprite(
                keepMoving, 36,
                Sprite.player_right,
                Sprite.player_right_1,
                Sprite.player_right_2).getFxImage());
    }

    @Override
    public void goLeft() {
        for (int i = 1; i <= this.speed; i++) {
            if (canMoveCamera()) {
                CameraTranslate.moveCamera(-1, 0);
            }
            if (this.x == Main.WIDTH - (Main.CAMERA_WIDTH + 1) / 2 * Sprite.SCALED_SIZE) {
                CameraTranslate.moveCamera(-1, 0);
            }
            this.x -= 1;
            if (checkBoundBomb() || checkBoundBrick() || checkBoundWall()) {
                if (canMoveCamera()) {
                    CameraTranslate.moveCamera(1, 0);
                }
                this.x += 1;
                super.roundVertical();
                break;
            }
        }
        keepMoving = (keepMoving + this.speed) % 108;
        setImg(Sprite.movingSprite(
                keepMoving, 36,
                Sprite.player_left,
                Sprite.player_left_1,
                Sprite.player_left_2).getFxImage());
    }

    @Override
    public void goDown() {
        boolean moved = false;
        for (int i = 1; i <= this.speed; i++) {
            this.y += 1;
            if (checkBoundBomb() || checkBoundBrick() || checkBoundWall()) {
                this.y -= 1;
                super.roundHorizontal();
                break;
            }
            moved = true;
        }
        if (moved) {
            keepMoving = (keepMoving + this.speed) % 108;
            setImg(Sprite.movingSprite(
                    keepMoving, 36,
                    Sprite.player_down,
                    Sprite.player_down_1,
                    Sprite.player_down_2).getFxImage());
        }
    }

    public boolean canMoveCamera() {
        return 2 * this.x > (Main.CAMERA_WIDTH - 1) * Sprite.SCALED_SIZE
                && this.x < (Main.WIDTH - (Main.CAMERA_WIDTH + 1) / 2) * Sprite.SCALED_SIZE;
    }

    @Override
    public boolean checkBoundBomb() {
        for (Bomb bomb : EntitySetManagement.getEntitySetManagement().getBombList()) {
            double disX = Math.abs(this.getX() - bomb.getX());
            double disY = Math.abs(this.getY() - bomb.getY());
            if (disX >= Sprite.SCALED_SIZE || disY >= Sprite.SCALED_SIZE) {
                bomb.setPassOver(false);
            }
            if (bomb.isPassOver()) {
                continue;
            }
            if (this.intersect(bomb)) {
                return true;
            }
        }
        return false;
    }

    public void setUpBomberDeath() {
        keepMoving++;
        if (keepMoving > 100) {
            keepMoving = 0;
        }
        setImg(Sprite.movingSprite(
                keepMoving, 180,
                Sprite.player_dead_1,
                Sprite.player_dead_2,
                Sprite.player_dead_3).getFxImage());
    }

    public void addBomb(Bomb bomb) {
        EntitySetManagement.getEntitySetManagement().getBombList().add(bomb);
    }

    public void plantTheBomb() {
        if (EntitySetManagement.getEntitySetManagement().getBombList().size() < EntitySetManagement.getEntitySetManagement().getBomberMan().numeberOfBomb) {
            int xGrid = Math.round(this.getX() / (float) Sprite.SCALED_SIZE);
            int yGrid = Math.round(this.getY() / (float) Sprite.SCALED_SIZE);

            float xFraction = (this.getX() / (float) Sprite.SCALED_SIZE) - xGrid;
            float yFraction = (this.getY() / (float) Sprite.SCALED_SIZE) - yGrid;

            if (Math.abs(xFraction) > 0.5) {
                xGrid = xFraction > 0 ? xGrid + 1 : xGrid - 1;
            }
            if (Math.abs(yFraction) > 0.5) {
                yGrid = yFraction > 0 ? yGrid + 1 : yGrid - 1;
            }

            Bomb bomb = new Bomb(xGrid, yGrid, Sprite.bomb_2.getFxImage());
            Map.map2D[bomb.getY() / Sprite.SCALED_SIZE][bomb.getX() / Sprite.SCALED_SIZE] = '*';
            boolean duplicate = false;
            for (Bomb exist : EntitySetManagement.getEntitySetManagement().getBombList()) {
                if (exist.intersect(bomb)) {
                    duplicate = true;
                    break;
                }
            }
            try {
                Sound.playSoundTillEnd(BOMB_PLANTED_SOUND);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        bomb.setImg(Sprite.bomb_exploded_3.getFxImage());
                        bomb.addFlame();
                        bomb.setExploded(true);
                    }
                };
                EntitySetManagement.getEntitySetManagement().getBombList().add(bomb);
                Timer timer = new Timer();
                try {
                    timer.schedule(task, 3500);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {
        if (!this.isAlive && !deathHandled) {
            deathHandled = true;
            Sound.playSoundTillEnd(DEATH_SOUND);
            TimerTask timeDeath = new TimerTask() {
                @Override
                public void run() {
                    setUpBomberDeath();
                }
            };
            Timer timer = new Timer();
            timer.schedule(timeDeath, 100L);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    GameLoop.gameStatus = GameLoop.STATUS_GAME_OVER;
                }
            }, 2000L);
        }
    }
}