package uet.oop.bomberman.entities.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.EntitySetManagement;
import uet.oop.bomberman.entities.map.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;


import java.util.Timer;
import java.util.TimerTask;

public class SpeedItem extends Item {

    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void render(GraphicsContext gc) {
        if (this.isVisible) {
            gc.drawImage(img, x, y);
        }
    }

    @Override
    public void update() {
        if (checkBoundBomber() && !isUsed) {
            handleItemCollection();
            scheduleBombTimeout();
        }
    }

    private void handleItemCollection() {
        Sound.playSoundTillEnd(ITEM_COLLECTION);
        Map.map2D[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] = ' ';
        int speed = EntitySetManagement.getEntitySetManagement().getBomberMan().getSpeed();
        EntitySetManagement.getEntitySetManagement().getBomberMan().setSpeed(speed + 4);
        this.isVisible = false;
        this.isUsed = true;
    }

    private void scheduleBombTimeout() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int speed = EntitySetManagement.getEntitySetManagement().getBomberMan().getSpeed();
                EntitySetManagement.getEntitySetManagement().getBomberMan().setSpeed(speed - 4);
            }
        };
        Timer timer = new Timer();
        try {
            timer.schedule(task, 15000);
        } catch (Exception e) {
            System.out.println("Bomb timeout error:" + e.getMessage());
        }
    }
}