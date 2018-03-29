package nilies;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class JavaFX extends Application {
    @Override
    public void start(Stage stage) {

        Group root = new Group();

        Scene scene = new Scene(root, 900, 900, Color.BLACK);
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.X){
                root.getChildren().clear();
                doMagic(root);
            }
        });
    }

    public void doMagic(Group root){

        TurtleLine line = new TurtleLine(450, 690, 450, 688, 90);

        BracketedLSystemInterpreter interpreter = new BracketedLSystemInterpreter();



        List<String> rulesForF = new ArrayList<>();
        rulesForF.add("F[+F]F[-F]F[+F[F]]");
        rulesForF.add("F[+F]F[-F]F");
        rulesForF.add("F[-F]F[+F]");
        rulesForF.add("F[[-F]F[+F]]");

        interpreter.addRule("F", rulesForF);
        interpreter.addRule("X","-[-F][+F[[+F]+F]]");
        String axiom = "X";

        ArrayList<String> currentIteration = new ArrayList<>(Collections.singletonList(axiom));

        for (int i = 1; i <= 6; i++) {
            System.out.print("iteration #" + i + ": ");
            currentIteration = interpreter.nextIteration(currentIteration);
        }

        interpreter.doStuff(line, currentIteration, 0, root);


    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static Color randomColor() {
        Random rand = new Random();
        Float r = rand.nextFloat();
        Float g = rand.nextFloat();
        Float b = rand.nextFloat();
        return Color.color(r, g, b);
    }
}
