package uet.oop.bomberman.entities.map;

import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.EntitySetManagement;
import uet.oop.bomberman.entities.enemies.Chicken;
import uet.oop.bomberman.entities.enemies.Pineapple;
import uet.oop.bomberman.entities.enemies.Strawberry;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.entities.map.mapblock.Grass;
import uet.oop.bomberman.entities.map.mapblock.Wall;
import uet.oop.bomberman.entities.map.mapblock.Brick;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.entities.portal.Portal;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Map {
    public static char[][] map2D = new char[Main.HEIGHT][Main.WIDTH];

    public static void createMapByLevel(int level) {
        String path = "res/levels/Level" + level + ".txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))) {
            String line = bufferedReader.readLine().trim();
            String[] str = line.split("\\s+");
            map2D = new char[Main.HEIGHT][Main.WIDTH];
            for (int i = 0; i < Main.HEIGHT; i++) {
                line = bufferedReader.readLine();
                for (int j = 0; j < Main.WIDTH; j++) {
                    map2D[i][j] = line.charAt(j);
                }
            }
            fillMapImage(map2D);
        } catch (Exception e) {
            System.out.println("map invalid: " + e.getMessage());
        }
    }

    public static void fillMapImage(char[][] map2D) {
        EntitySetManagement esm = GameLoop.entitySetManagement;

        for (int i = 0; i < Main.HEIGHT; i++) {
            for (int j = 0; j < Main.WIDTH; j++) {
                char tile = map2D[i][j];

                if (i == 0 || j == 0 || i == Main.HEIGHT - 1 || j == Main.WIDTH - 1 || tile == '#') {
                    esm.getWallList().add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else {
                    esm.getGrassList().add(new Grass(j, i, Sprite.grass.getFxImage()));
                }

                switch (tile) {
                    case 'p':
                        esm.setBomberMan(new Bomber(j, i, Sprite.player_right.getFxImage()));
                        map2D[i][j] = ' ';
                        break;
                    case '1':
                        esm.getEnemyList().add(new Pineapple(j, i, Sprite.pineapple_left_1.getFxImage()));
                        break;
                    case '2':
                        esm.getEnemyList().add(new Chicken(j, i, Sprite.chicken_left_1.getFxImage()));
                        break;
                    case '3':
                        esm.getEnemyList().add(new Strawberry(j, i, Sprite.strawberry_right_1.getFxImage()));
                        break;
//                    case '4':
//                        esm.getEnemyList().add(new Kondorian(j, i, Sprite.kondoria_right1.getFxImage()));
//                        break;
                    //nếu cần con thứ 4 thì sửa, không thì xóa đi cũng được
                    case '#':
                        esm.getWallList().add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    case '*':
                        esm.getBrickList().add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'b':
                        esm.getItemList().add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        esm.getBrickList().add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map2D[i][j] = '*';
                        break;
                    case 'f':
                        esm.getItemList().add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        esm.getBrickList().add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map2D[i][j] = '*';
                        break;
                    case 's':
                        esm.getItemList().add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        esm.getBrickList().add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map2D[i][j] = '*';
                        break;
                    case 'x':
                        esm.setPortal(new Portal(j, i, Sprite.portal.getFxImage()));
                        esm.getBrickList().add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map2D[i][j] = '*';
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        createMapByLevel(1);
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 31; j++) {
                System.out.print(map2D[i][j]);
            }
            System.out.println();
        }
        System.out.println(map2D[5][16]);
    }
}

