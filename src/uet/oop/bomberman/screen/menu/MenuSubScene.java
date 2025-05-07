package uet.oop.bomberman.screen.menu;

import javafx.animation.FadeTransition;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MenuSubScene extends SubScene {
    private static final String BACKGROUND_IMAGE = "/menu/panel.png";
    private static final String FONT_PATH = "/font/Baloo-Regular.ttf";
    private static final int WIDTH = 398;
    private static final int HEIGHT = 500;

    public MenuSubScene(SubsceneType subsceneType) {

        super(new Pane(), WIDTH, HEIGHT);
        setLayoutX(932);
        setLayoutY(270);
        setVisible(false);
        setOpacity(0);

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(BACKGROUND_IMAGE, WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                null
        );
        Pane root = (Pane) this.getRoot();
        root.setBackground(new Background(backgroundImage));

        setOnMouseEntered(mouseEvent -> {
            setEffect(new DropShadow());
        });
        setOnMouseExited(mouseEvent -> {
            setEffect(null);
        });

        switch (subsceneType) {

            case HELP:
                root.getChildren().add(createHelpContent());
                break;
            case SCORES:
                root.getChildren().add(createScoresContent());
                break;
            case CREDITS:
                root.getChildren().add(createCreditsContent());
                break;
        }
    }

    public void fadeIn() {
        this.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(300), this);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    public void fadeOut() {
        FadeTransition ft = new FadeTransition(Duration.millis(300), this);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(event -> this.setVisible(false));
        ft.play();
    }

    private Label createLabel(String text, int size) {
        Label label = new Label(text);
        InputStream fontStream = getClass().getResourceAsStream(FONT_PATH);
        if (fontStream != null) {
            label.setFont(Font.loadFont(fontStream, size));
        } else {
            System.out.println("Label font not found!");
            label.setFont(Font.font("Arial", size));
        }

        label.setWrapText(true);
        label.setGraphicTextGap(30);
        return label;
    }

    private Label createLabelWithIcon(String text, int size, String imagePath) {
        Label label = createLabel(text, size);
        ImageView image = new ImageView(new Image(imagePath, 30, 30, false, true));
        label.setGraphic(image);

        return label;
    }

    private VBox createHelpContent() {
        Label label = createLabel("Hướng dẫn cách chơi", 30);
        Label label1 = createLabelWithIcon("Di chuyển sang trái", 25, "/menu/key_left.png");
        Label label2 = createLabelWithIcon("Di chuyển sang phải", 25, "/menu/key_right.png");
        Label label3 = createLabelWithIcon("Di chuyển lên trên", 25, "/menu/key_up.png");
        Label label4 = createLabelWithIcon("Di chuyển xuống dưới", 25, "/menu/key_down.png");
        Label label5 = createLabelWithIcon("Tiêu diệt toàn bộ quái", 25, "/menu/key_c.png");
        Label label6 = createLabelWithIcon("Đặt bomb", 25, "/menu/key_space.png");

        VBox box = new VBox(7);
        box.getChildren().addAll(label, label1, label2, label3, label4, label5, label6);
        box.setLayoutX(45);
        box.setLayoutY(60);
        return box;
    }

    private VBox createScoresContent() {
        Label title = createLabel("HIGH SCORE", 50);
        VBox box = new VBox(6);
        box.setLayoutX(40);
        box.setLayoutY(30);
        box.getChildren().add(title);

        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("res/score/scores.txt"));
        } catch (IOException e) {
            System.out.println("Score input error: " + e.getMessage());
        }

        List<Integer> scores = new ArrayList<>();
        try {
            for (String s : lines)  {
                scores.add(Integer.parseInt(s));
            }
        } catch (NumberFormatException e){
            System.out.println("Format scores error: " + e.getMessage());
        }

        scores.sort(Comparator.reverseOrder());

        Label label;
        for (int i = 1; i <= Math.min(5, scores.size()); i++) {
            label = createLabel(
                    "Anonymous #" + i + " - " + scores.get(i - 1).toString(),
                    25
            );
            box.getChildren().add(label);
        }

        for (int i = Math.min(5, scores.size()) + 1; i <= 7; i++) {
            label = createLabel("Anonymous #" + i + " - --", 25);
            box.getChildren().add(label);
        }

        return box;
    }

    private VBox createCreditsContent() {
        Label creator1 = createLabel("24022474_NGUYỄN QUỐC TRUNG", 20);
        Label creator2 = createLabel("24022450_TỐNG QUANG THÁI", 20);
        Label creator3 = createLabel("24022480_NGUYỄN THIÊN TRƯỜNG", 20);
        VBox box = new VBox(3);
        box.getChildren().addAll(creator1, creator2, creator3);
        box.setLayoutX(38);
        box.setLayoutY(100);
        return box;
    }

    public void updateScoresContent() {
        Pane root = (Pane) getRoot();
        root.getChildren().clear();


        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(BACKGROUND_IMAGE, WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                null
        );
        root.setBackground(new Background(backgroundImage));

        VBox box = createScoresContent();
        root.getChildren().add(box);
    }
}
