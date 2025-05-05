package uet.oop.bomberman.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.EntitySetManagement;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.HashSet;
import java.util.Set;

public class PlayerController {
    private static final Set<KeyCode> keyInputs = new HashSet<>();

    public static void playerControl(Scene scene, Bomber bomber, EntitySetManagement entitySetManagement) {
        if (bomber.isAlive()) {
            scene.setOnKeyPressed(keyEvent -> {
                KeyCode keyCode = keyEvent.getCode();
                keyInputs.add(keyCode);

                if (keyCode == KeyCode.C) {
                    entitySetManagement.getEnemyList().forEach(enemy -> enemy.setAlive(false));
                    entitySetManagement.getEnemyList().removeIf(enemy -> !enemy.isAlive());
                }
                if (keyCode == KeyCode.SPACE) {
                    bomber.plantTheBomb();
                }
            });

            scene.setOnKeyReleased(keyEvent -> {
                keyInputs.remove(keyEvent.getCode());

                switch (keyEvent.getCode()) {
                    case UP -> {
                        bomber.setImg(Sprite.player_up.getFxImage());
                    }
                    case DOWN -> {
                        bomber.setImg(Sprite.player_down.getFxImage());
                    }
                    case LEFT -> {
                        bomber.setImg(Sprite.player_left.getFxImage());
                    }
                    case RIGHT -> {
                        bomber.setImg(Sprite.player_right.getFxImage());
                    }
                }
            });

            AnimationTimer playerControlTimer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    if (!bomber.isAlive()) {
                        stop();
                        return;
                    }

                    if (keyInputs.contains(KeyCode.UP)) {
                        bomber.goUp();
                    } else if (keyInputs.contains(KeyCode.DOWN)) {
                        bomber.goDown();
                    } else if (keyInputs.contains(KeyCode.LEFT)) {
                        bomber.goLeft();
                    } else if (keyInputs.contains(KeyCode.RIGHT)) {
                        bomber.goRight();
                    }
                }
            };
            playerControlTimer.start();
        }
    }
}
