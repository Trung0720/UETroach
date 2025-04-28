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

import java.io.InputStream;

public class MenuSubScene extends SubScene {
    private static final String BACKGROUND_IMAGE = "/menu/panel.png";
    private static final String FONT_PATH = "/menu/Baloo-Regular.ttf";


    public MenuSubScene(SubsceneType subsceneType) {
        super(new AnchorPane(), 398, 500);
        setLayoutX(932);
        setLayoutY(270);
        setVisible(false);
        setOpacity(0);

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(BACKGROUND_IMAGE, 398, 500, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                null
        );
        AnchorPane root = (AnchorPane) this.getRoot();
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
        Label label = createLabel("Hướng dẫn cách chơi:", 30);
        Label label1 = createLabelWithIcon("Di chuyển sang trái", 25, "/menu/key_left.png");
        Label label2 = createLabelWithIcon("Di chuyển sang phải", 25, "/menu/key_right.png");
        Label label3 = createLabelWithIcon("Di chuyển lên trên", 25, "/menu/key_up.png");
        Label label4 = createLabelWithIcon("Di chuyển xuống dưới", 25, "/menu/key_down.png");
        Label label5 = createLabelWithIcon("Đặt bomb", 25, "/menu/key_space.png");

        VBox box = new VBox(6);
        box.getChildren().addAll(label, label1, label2, label3, label4, label5);
        box.setLayoutX(45);
        box.setLayoutY(90);
        return box;
    }

    private VBox createScoresContent() {
        VBox box = new VBox(10);
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
}
