package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntitySetManagement;

public abstract class Item extends Entity {
    public static final String ITEM_COLLECTION = "res/sound/item_collect.mp3";
    protected boolean isVisible = true;
    protected boolean isUsed = false;

    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public abstract void update();

    protected boolean checkBoundBomber() {
        return this.intersect(EntitySetManagement.getEntitySetManagement().getBomberMan());
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

//    public void removeItem() {
//        try{
//            EntitySetManagement.getEntitySetManagement().getItemList().removeIf(Item::checkBoundBomber);
//        } catch (Exception e) {
//            e.printStackTrace(); //ConcurrentModificationException
//        }
//    }
}