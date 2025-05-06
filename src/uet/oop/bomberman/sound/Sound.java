package uet.oop.bomberman.sound;

import javafx.animation.PauseTransition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class Sound {
    private static final ArrayList<MediaPlayer> activeSounds = new ArrayList<>();
    private static MediaPlayer foreverSound = null;
    private static double soundVolume = 0.4;

    public static double getSoundVolume() {
        return soundVolume;
    }

    public static void setSoundVolume(double soundVolume) {
        for (MediaPlayer mediaPlayer : activeSounds) {
            mediaPlayer.setVolume(soundVolume);
        }
        if (foreverSound != null) {
            foreverSound.setVolume(soundVolume);
        }
    }

    public static void playSoundForRate(String soundPath, double seconds) {
        try {
            String path = new File(soundPath).toURI().toString();
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(soundVolume);
            activeSounds.add(mediaPlayer);

            mediaPlayer.setOnReady(() -> {
                double soundDuration = media.getDuration().toSeconds();
                double rate = soundDuration / seconds;

                mediaPlayer.setRate(rate);
                mediaPlayer.play();

                PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
                delay.setOnFinished(actionEvent -> {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                    activeSounds.remove(mediaPlayer);
                });
                delay.play();
            });

            mediaPlayer.setOnError(() -> {
                System.out.println(soundPath + "MediaPlayer error:" + mediaPlayer.getError());
            });
        } catch (Exception e) {
            System.out.println(soundPath + e.getMessage());
        }
    }

    public static void playSoundTillEnd(String soundPath) {
        try {
            String path = new File(soundPath).toURI().toString();
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(soundVolume);
            activeSounds.add(mediaPlayer);

            mediaPlayer.setOnReady(mediaPlayer::play);
            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.dispose();
                activeSounds.remove(mediaPlayer);
            });

            mediaPlayer.setOnError(() -> {
                System.out.println(soundPath + "MediaPlayer error:" + mediaPlayer.getError());
            });
        } catch (Exception e) {
            System.out.println(soundPath + e.getMessage());
        }
    }

    public static void playSoundForever(String soundPath) {
        stopForeverSound();
        try {
            String path = new File(soundPath).toURI().toString();
            Media media = new Media(path);
            foreverSound = new MediaPlayer(media);
            foreverSound.setVolume(soundVolume);

            foreverSound.setCycleCount(MediaPlayer.INDEFINITE);
            foreverSound.setOnReady(foreverSound::play);

            foreverSound.setOnError(() -> {
                System.out.println(soundPath + "MediaPlayer error:" + foreverSound.getError());
            });
        } catch (Exception e) {
            System.out.println(soundPath + e.getMessage());
        }
    }

    public static void stopForeverSound() {
        if (foreverSound != null) {
            foreverSound.stop();
            foreverSound.dispose();
            foreverSound = null;
        }
    }

    public static void stopAll() {
        stopForeverSound();
        for (MediaPlayer mediaPlayer : activeSounds) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        activeSounds.clear();
    }
}
