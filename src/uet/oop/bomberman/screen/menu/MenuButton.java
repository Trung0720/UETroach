package uet.oop.bomberman.screen.menu;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.InputStream;

public class MenuButton extends Button {
    private final String FONT_PATH = "/menu/wheaton capitals.otf";
    private final String BUTTON = "/menu/button.png";
    private final String BUTTON_PRESSED = "/menu/button_pressed.png";
    private final int BUTTON_FONT_SIZE = 45;
    private final int BUTTON_WIDTH = 380;
    private final int BUTTON_HEIGHT = 98;
    private final int BUTTON_HEIGHT_PRESSED = 90;

    private ImageView buttonImageView;

    public MenuButton(String text) {
        setPrefWidth(BUTTON_WIDTH);
        setPrefHeight(BUTTON_HEIGHT);

        buttonImageView = new ImageView(new Image(BUTTON));
        buttonImageView.setFitWidth(getPrefWidth());
        buttonImageView.setFitHeight(getPrefHeight());
        buttonImageView.setPreserveRatio(false);
        setGraphic(buttonImageView);

        setText(text);
        setButtonFont();
        setContentDisplay(ContentDisplay.CENTER);
        setTextFill(Color.SNOW);
        setStyle("-fx-background-color: transparent;");

        eventHandler();
    }


    private void setButtonFont(){
        InputStream fontStream = getClass().getResourceAsStream(FONT_PATH);

        if (fontStream != null) {
            setFont(Font.loadFont(fontStream, BUTTON_FONT_SIZE));
        } else {
            System.out.println("Menu buttons font not found!");
            setFont(Font.font("Arial", BUTTON_FONT_SIZE));
        }
    }

    private void setButtonPressed() {
        buttonImageView.setImage(new Image(BUTTON_PRESSED));
        setPrefHeight(BUTTON_HEIGHT_PRESSED);
        buttonImageView.setFitHeight(getPrefHeight());
        setLayoutY(getLayoutY() + (BUTTON_HEIGHT - BUTTON_HEIGHT_PRESSED));
    }

    private void setButtonReleased() {
        buttonImageView.setImage(new Image(BUTTON));
        setPrefHeight(BUTTON_HEIGHT);
        buttonImageView.setFitHeight(getPrefHeight());
        setLayoutY(getLayoutY() - (BUTTON_HEIGHT - BUTTON_HEIGHT_PRESSED));
    }

    private void eventHandler() {
        setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressed();
            }
        });

        setOnMouseReleased(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleased();
            }
        });

        setOnMouseEntered(mouseEvent -> {
            setEffect(new DropShadow());
        });

        setOnMouseExited(mouseEvent -> {
            setEffect(null);
        });
    }
}
