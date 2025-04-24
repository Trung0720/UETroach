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

    private ImageView buttonImageView;

    public MenuButton(String text) {
        setPrefWidth(380);
        setPrefHeight(98);

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
            setFont(Font.loadFont(fontStream, 45));
        } else {
            System.out.println("Menu buttons font not found!");
            setFont(Font.font("Arial", 45));
        }
    }

    private void setButtonPressed() {
        buttonImageView.setImage(new Image(BUTTON_PRESSED));
        setPrefHeight(90);
        buttonImageView.setFitHeight(getPrefHeight());
        setLayoutY(getLayoutY() + 8);
    }

    private void setButtonReleased() {
        buttonImageView.setImage(new Image(BUTTON));
        setPrefHeight(98);
        buttonImageView.setFitHeight(getPrefHeight());
        setLayoutY(getLayoutY() - 8);
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
