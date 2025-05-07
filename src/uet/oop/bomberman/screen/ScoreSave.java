package uet.oop.bomberman.screen;

import uet.oop.bomberman.GameLoop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ScoreSave {
    public static void saveScore() {
        String line = GameLoop.score + "\n";
        try {
            Files.writeString(Paths.get("res/score/scores.txt"),
                    line,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error saving score:" + e.getMessage());
        }
    }
}
