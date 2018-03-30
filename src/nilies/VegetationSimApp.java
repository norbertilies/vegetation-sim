package nilies;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nilies.util.DrawingStage;


public class VegetationSimApp extends javafx.application.Application {

    public static void main(String[] args) {
        javafx.application.Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 900, 900, Color.BLACK);
        stage.setScene(scene);
        stage.show();

        DrawingStage drawingStage = new DrawingStage();
        drawingStage.configKeyBinds(scene, root);
    }

}
