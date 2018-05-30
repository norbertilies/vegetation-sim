package nilies.component;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static nilies.util.Constants.*;

public class ColorsPopup {
    private final Stage popup;
    private final ColorPicker flower;
    private final ColorPicker bark;
    private final ColorPicker leaf;

    public ColorsPopup(Stage mainStage){
        popup = new Stage();

        Group root = new Group();
        Scene popupScene = new Scene(root, 230, 215, Color.LIGHTGRAY);
        popup.initOwner(mainStage);
        popup.setScene(popupScene);
        popup.show();

        flower = addFlowerColorPicker(root);
        bark = addBarkColorPicker(root);
        leaf = addLeafColorPicker(root);
        addSaveButton(root);

    }

    public ColorPicker addFlowerColorPicker(Group root){
        ColorPicker colorPicker = new ColorPicker(FLOWER_COLOR);
        colorPicker.setLayoutX(70);
        colorPicker.setLayoutY(10);

        Label label = new Label("Flower: ");
        label.setLayoutX(10);
        label.setLayoutY(15);

        root.getChildren().add(label);
        root.getChildren().add(colorPicker);

        return colorPicker;
    }

    public ColorPicker addBarkColorPicker(Group root){
        ColorPicker colorPicker = new ColorPicker(STANDARD_COLOR);
        colorPicker.setLayoutX(70);
        colorPicker.setLayoutY(50);

        Label label = new Label("Bark: ");
        label.setLayoutX(10);
        label.setLayoutY(55);

        root.getChildren().add(label);
        root.getChildren().add(colorPicker);

        return colorPicker;
    }

    public ColorPicker addLeafColorPicker(Group root){
        ColorPicker colorPicker = new ColorPicker(LEAF_COLOR);
        colorPicker.setLayoutX(70);
        colorPicker.setLayoutY(90);

        Label label = new Label("Leaf: ");
        label.setLayoutX(10);
        label.setLayoutY(95);

        root.getChildren().add(label);
        root.getChildren().add(colorPicker);

        return colorPicker;
    }

    public void addSaveButton(Group root){
        Button btn = new Button("Save");
        btn.setLayoutX(65);
        btn.setLayoutY(150);
        btn.setPrefHeight(30);
        btn.setPrefWidth(95);

        btn.setOnAction(event -> {
            FLOWER_COLOR = flower.getValue();
            STANDARD_COLOR = bark.getValue();
            LEAF_COLOR = leaf.getValue();

            popup.close();
        });

        root.getChildren().add(btn);

    }
}
