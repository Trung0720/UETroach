package uet.oop.bomberman.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

public class PlayerController {
    private static final Set<KeyCode> keyInputs = new HashSet<>();

    public static void playerControl(Scene scene) {
        scene.setOnKeyPressed(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            keyInputs.add(keyCode);

            if (keyCode == KeyCode.C) {

            }
            if (keyCode == KeyCode.SPACE) {

            }
        });

        scene.setOnKeyReleased(keyEvent -> {
            keyInputs.remove(keyEvent.getCode());

            switch (keyEvent.getCode()) {
                case UP -> {

                }
                case DOWN -> {

                }
                case LEFT -> {

                }
                case RIGHT -> {

                }
            }
        });

        AnimationTimer playerControlTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (keyInputs.contains(KeyCode.UP)) {

                } else if (keyInputs.contains(KeyCode.DOWN)) {

                } else if (keyInputs.contains(KeyCode.LEFT)) {

                } else if (keyInputs.contains(KeyCode.RIGHT)) {

                }
            }
        };
        playerControlTimer.start();
    }
}
