package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.entities.map.mapblock.Brick;
import uet.oop.bomberman.entities.map.mapblock.Grass;
import uet.oop.bomberman.entities.map.mapblock.Wall;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntitySetManagement {
    private static EntitySetManagement entitySetManagement = null;

    private EntitySetManagement() {}

    public static EntitySetManagement getEntitySetManagement() {
        if(Objects.isNull(entitySetManagement)) {
            entitySetManagement = new EntitySetManagement();
        }
        return entitySetManagement;
    }

    public static void reset() {
        entitySetManagement = new EntitySetManagement();
    }

    private final List<Bomb> bombList = new ArrayList<>();
    private final List<Wall> wallList = new ArrayList<>();
    private final List<Grass> grassList = new ArrayList<>();
    private final List<Enemy> enemyList = new ArrayList<>();
    private final List<Brick> brickList = new ArrayList<>();
    private final List<Item> itemList = new ArrayList<>();

    private Entity portal = null;
    private Bomber bomberMan = null;

    public void removeEnemies() {
        int sizeBefore = enemyList.size();
        enemyList.removeIf(enemy -> !enemy.isAlive());
        int removed = sizeBefore - enemyList.size();
        GameLoop.score += removed * 10;
    }

    public void removeBrick() {
        brickList.removeIf(Brick::isBroken);
    }

    public void removeBomb() {
        bombList.removeIf(Bomb::exploded);
    }

    public void removeUsedItems() {
        itemList.removeIf(Item::isUsed);
    }

    public void clearAll() {
        enemyList.clear();
        brickList.clear();
        grassList.clear();
        wallList.clear();
        bombList.clear();
        itemList.clear();
        bomberMan = null;
        portal = null;
    }

    public void updateALl() {
        try {
            grassList.forEach(Grass::update);
            wallList.forEach(Wall::update);
            if (portal != null) {
                portal.update();
            }
            itemList.forEach(Item::update);
            brickList.forEach(Brick::update);
            bombList.forEach(bomb -> {
                bomb.getAllFlame().forEach(Flame::update);
            });
            bombList.forEach(Bomb::update);
            enemyList.forEach(Enemy::update);
            if (bomberMan != null) {
                bomberMan.update();
            }
        } catch (Exception e) {
            System.out.println("Update all error:" + e.getMessage());
        }
    }

    public void renderAll(GraphicsContext graphicsContext) {
        try {
            graphicsContext.clearRect(0, 0,
                    Main.WIDTH * Sprite.SCALED_SIZE,
                    Main.HEIGHT * Sprite.SCALED_SIZE
            );

            grassList.forEach(grass -> grass.render(graphicsContext));
            wallList.forEach(wall -> wall.render(graphicsContext));
            portal.render(graphicsContext);
            itemList.forEach(item -> item.render(graphicsContext));
            brickList.forEach(brick -> brick.render(graphicsContext));
            bombList.forEach(bomb -> {
                bomb.getAllFlame().forEach(flame -> flame.render(graphicsContext));
            });
            bombList.forEach(bomb -> bomb.render(graphicsContext));
            enemyList.forEach(enemy -> enemy.render(graphicsContext));
            bomberMan.render(graphicsContext);
        } catch (Exception e) {
            System.out.println("Render all error:" + e.getMessage());
        }
    }

    public List<Wall> getWallList() {
        return wallList;
    }

    public List<Grass> getGrassList() {
        return grassList;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public List<Brick> getBrickList() {
        return brickList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public Entity getPortal() {
        return portal;
    }

    public void setPortal(Entity portal) {
        this.portal = portal;
    }

    public Bomber getBomberMan() {
        return bomberMan;
    }

    public void setBomberMan(Bomber bomberMan) {
        this.bomberMan = bomberMan;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }
}
