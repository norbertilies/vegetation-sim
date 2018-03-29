package nilies;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class JavaFX extends Application  {

    @Override
    public void start(Stage stage) {
        Group root = new Group();

        TurtleLine line = new TurtleLine(450,690,450,688,90);

        List<TurtleLine> firstList = new ArrayList<>();
        List<TurtleLine> secondList;
        firstList.add(line);

        Scene scene = new Scene(root, 900, 900, Color.BLACK);
        stage.setScene(scene);
        stage.show();

        BracketedLSystemInterpreter interpreter = new BracketedLSystemInterpreter();

        interpreter.addRule("F","FF-[-F+F+F]+[+F-F-F]");
        interpreter.addRule("X", "F-[[X]+X]+F[+FX]-X");
        String axiom = "F";

        ArrayList<String> currentIteration = new ArrayList<>(Collections.singletonList(axiom));

        for (int i = 1; i <= 4; i++){
            System.out.print("iteration #"+i+": ");
            System.out.println(currentIteration);
            currentIteration = interpreter.nextIteration(currentIteration);
        }

        interpreter.doStuff(line, currentIteration, 0, root);

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static Color randomColor(){
        Random rand = new Random();
        Float r = rand.nextFloat();
        Float g = rand.nextFloat();
        Float b = rand.nextFloat();
        return Color.color(r,g,b);
    }


}
