package uet.oop.bomberman.entities;

import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.map.mapblock.Brick;
import uet.oop.bomberman.entities.map.mapblock.Grass;
import uet.oop.bomberman.entities.map.mapblock.Wall;
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

    private final List<Bomb> bombList = new ArrayList<>();
    private final List<Wall> wallList = new ArrayList<>();
    private final List<Grass> grassList = new ArrayList<>();
    private final List<Enemy> enemyList = new ArrayList<>();
    private final List<Brick> brickList = new ArrayList<>();
    private final List<Item> itemList = new ArrayList<>();

    private Entity portal = null;
    private Bomber bomberMan = new Bomber(1, 1, Sprite.player_right.getFxImage());

    public void removeEnemies() {
        int sizeBefore = enemyList.size();
        enemyList.removeIf(enemy -> !enemy.isAlive());
        int removed = sizeBefore - enemyList.size();
        Main.score += removed * 10;
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
        itemList.clear();
        bomberMan = null;
        portal = null;
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
