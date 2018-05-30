package nilies.component;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu {

    private Robot r;

    public Menu(Group root, Stage mainStage, List<RuleSet> ruleSets) {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        Rectangle menu = new Rectangle(0,900, 900, 100);
        menu.setFill(Color.LIGHTGRAY);
        root.getChildren().add(menu);
        addButtons(root, mainStage);
    }

    public void addButtons(Group root, Stage mainStage){
        root.getChildren().add(getLeftButton());
        root.getChildren().add(getRightButton());
        root.getChildren().add(getResetButton());
        root.getChildren().add(getUpButton());
        root.getChildren().add(getGenerateButton());
        root.getChildren().add(getRulesButton(mainStage));
        root.getChildren().add(getColorsButton(mainStage));
    }

    public Button getLeftButton(){
        Button button = new Button("\uD83E\uDC70");
        button.setTooltip(new Tooltip("Hot-key: A"));
        button.setMinWidth(80);
        button.setMaxWidth(80);
        button.setMinHeight(30);
        button.setMaxHeight(30);
        button.setLayoutX(700);
        button.setLayoutY(950);

        button.setOnAction(event -> {
            r.keyPress(KeyEvent.VK_A);
            r.keyRelease(KeyEvent.VK_A);
            System.out.println("A");
        });

        return button;
    }

    public Button getRightButton(){
        Button button = new Button("\uD83E\uDC72");
        button.setTooltip(new Tooltip("Hot-key: D"));
        button.setMinWidth(80);
        button.setMaxWidth(80);
        button.setMinHeight(30);
        button.setMaxHeight(30);
        button.setLayoutX(782);
        button.setLayoutY(950);

        button.setOnAction(event -> {
            r.keyPress(KeyEvent.VK_D);
            r.keyRelease(KeyEvent.VK_D);
            System.out.println("D");
        });

        return button;
    }

    public Button getResetButton(){
        Button button = new Button("Reset");
        button.setTooltip(new Tooltip("Hot-key: X"));
        button.setMinWidth(110);
        button.setMaxWidth(110);
        button.setMinHeight(45);
        button.setMaxHeight(45);
        button.setLayoutX(560);
        button.setLayoutY(925);

        button.setOnAction(event -> {
            r.keyPress(KeyEvent.VK_X);
            r.keyRelease(KeyEvent.VK_X);
            System.out.println("X");
        });

        return button;
    }

    public Button getUpButton(){
        Button button = new Button("\uD83E\uDC71");
        button.setTooltip(new Tooltip("Hot-key: W"));
        button.setMinWidth(80);
        button.setMaxWidth(80);
        button.setMinHeight(30);
        button.setMaxHeight(30);
        button.setLayoutX(741);
        button.setLayoutY(915);

        button.setOnAction(event -> {
            r.keyPress(KeyEvent.VK_W);
            r.keyRelease(KeyEvent.VK_W);
            System.out.println("W");
        });

        return button;
    }

    public Button getGenerateButton(){
        Button button = new Button("Generate");
        button.setMinWidth(110);
        button.setMaxWidth(110);
        button.setMinHeight(45);
        button.setMaxHeight(45);
        button.setLayoutX(420);
        button.setLayoutY(925);

        button.setOnAction(event -> {
            r.keyPress(KeyEvent.VK_G);
            r.keyRelease(KeyEvent.VK_G);
            System.out.println("G");
        });

        return button;
    }

    public Button getColorsButton(Stage mainStage) {
        Button button = new Button("Choose colors");
        button.setMinWidth(160);
        button.setMaxWidth(160);
        button.setMinHeight(45);
        button.setMaxHeight(45);
        button.setLayoutX(210);
        button.setLayoutY(925);

        button.setOnAction(event -> new ColorsPopup(mainStage));

        return button;
    }

    public Button getRulesButton(Stage mainStage) {
        Button button = new Button("Rules");
        button.setTooltip(new Tooltip("Hot-key: G"));
        button.setMinWidth(110);
        button.setMaxWidth(110);
        button.setMinHeight(45);
        button.setMaxHeight(45);
        button.setLayoutX(50);
        button.setLayoutY(925);



        button.setOnAction(event -> new RulesPopup(mainStage));

        return button;
    }
}
