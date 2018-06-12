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
    private final ColorPicker leafStroke;
    private final ColorPicker flowerStroke;
    private final ColorPicker flowerCenter;

    public ColorsPopup(Stage mainStage){
        popup = new Stage();

        Group root = new Group();
        Scene popupScene = new Scene(root, 270, 300, Color.LIGHTGRAY);
        popup.initOwner(mainStage);
        popup.setScene(popupScene);
        popup.show();

        flower = addFlowerColorPicker(root);
        bark = addBarkColorPicker(root);
        leaf = addLeafColorPicker(root);
        flowerCenter = addFlowerCenterColorPicker(root);
        flowerStroke = addFlowerStrokeColorPicker(root);
        leafStroke = addLeafStrokeColorPicker(root);
        addSaveButton(root);

    }

    public ColorPicker addBarkColorPicker(Group root){
        ColorPicker colorPicker = new ColorPicker(STANDARD_COLOR);
        colorPicker.setLayoutX(105);
        colorPicker.setLayoutY(10);

        Label label = new Label("Bark: ");
        label.setLayoutX(10);
        label.setLayoutY(15);

        root.getChildren().add(label);
        root.getChildren().add(colorPicker);

        return colorPicker;
    }

    public ColorPicker addFlowerColorPicker(Group root){
        ColorPicker colorPicker = new ColorPicker(FLOWER_COLOR);
        colorPicker.setLayoutX(105);
        colorPicker.setLayoutY(50);

        Label label = new Label("Flower: ");
        label.setLayoutX(10);
        label.setLayoutY(55);

        root.getChildren().add(label);
        root.getChildren().add(colorPicker);

        return colorPicker;
    }

    public ColorPicker addFlowerStrokeColorPicker(Group root){
        ColorPicker colorPicker = new ColorPicker(FLOWER_STROKE);
        colorPicker.setLayoutX(105);
        colorPicker.setLayoutY(90);

        Label label = new Label("Flower stroke: ");
        label.setLayoutX(10);
        label.setLayoutY(95);

        root.getChildren().add(label);
        root.getChildren().add(colorPicker);

        return colorPicker;
    }

    public ColorPicker addFlowerCenterColorPicker(Group root){
        ColorPicker colorPicker = new ColorPicker(FLOWER_CENTER_FILL);
        colorPicker.setLayoutX(105);
        colorPicker.setLayoutY(130);

        Label label = new Label("Flower center: ");
        label.setLayoutX(10);
        label.setLayoutY(135);

        root.getChildren().add(label);
        root.getChildren().add(colorPicker);

        return colorPicker;
    }


    public ColorPicker addLeafColorPicker(Group root){
        ColorPicker colorPicker = new ColorPicker(LEAF_COLOR);
        colorPicker.setLayoutX(105);
        colorPicker.setLayoutY(170);

        Label label = new Label("Leaf: ");
        label.setLayoutX(10);
        label.setLayoutY(175);

        root.getChildren().add(label);
        root.getChildren().add(colorPicker);

        return colorPicker;
    }

    public ColorPicker addLeafStrokeColorPicker(Group root){
        ColorPicker colorPicker = new ColorPicker(LEAF_STROKE);
        colorPicker.setLayoutX(105);
        colorPicker.setLayoutY(210);

        Label label = new Label("Leaf stroke: ");
        label.setLayoutX(10);
        label.setLayoutY(215);

        root.getChildren().add(label);
        root.getChildren().add(colorPicker);

        return colorPicker;
    }

    public void addSaveButton(Group root){
        Button btn = new Button("Save");
        btn.setLayoutX(80);
        btn.setLayoutY(250);
        btn.setPrefHeight(30);
        btn.setPrefWidth(95);

        btn.setOnAction(event -> {
            FLOWER_COLOR = flower.getValue();
            STANDARD_COLOR = bark.getValue();
            LEAF_COLOR = leaf.getValue();
            LEAF_STROKE = leafStroke.getValue();
            FLOWER_STROKE = flowerStroke.getValue();
            FLOWER_CENTER_FILL = flowerCenter.getValue();

            popup.close();
        });

        root.getChildren().add(btn);

    }
}
