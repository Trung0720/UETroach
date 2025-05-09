package uet.oop.bomberman.entities.portal;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.entities.EntitySetManagement;
import uet.oop.bomberman.entities.items.Item;


public class Portal extends Item {
    public Portal(int xUnit, int yUnit, Image img) {
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
        if (this.intersect(EntitySetManagement.getEntitySetManagement().getBomberMan())
                && (EntitySetManagement.getEntitySetManagement().getEnemyList().isEmpty())) {
            GameLoop.nextLevel++;
        }
    }
}