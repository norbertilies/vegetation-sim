package nilies;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;
import java.util.List;

import static nilies.BracketedLSystemInterpreter.timeLimitExceeded;
import static nilies.BracketedLSystemInterpreter.startTime;
import static nilies.Constants.*;

public class JavaFX extends Application {

    private ArrayList<String> currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
    private int timesPressedN = 0;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();

        Scene scene = new Scene(root, 900, 900, Color.BLACK);
        stage.setScene(scene);
        stage.show();

        configKeyBinds(scene, root);

    }

    public void doNextIteration(Group root) {
        startTime = System.currentTimeMillis();
        timeLimitExceeded = false;

//        TurtleLine line = new TurtleLine(450, 900, 450, 888, 90);
        TurtleLine line = new TurtleLine(450, 450, 450, 450, 90);

        BracketedLSystemInterpreter interpreter = new BracketedLSystemInterpreter();

        List<String> rulesForF = new ArrayList<>();
        rulesForF.add("FF");

        interpreter.addRule("X", "X+YF+");
        interpreter.addRule("Y", "-FX-Y");

        currentIteration = interpreter.nextIteration(currentIteration);
        interpreter.drawSentence(line, currentIteration, 0, root);
        if (timeLimitExceeded) {
            timesPressedN--;
            System.out.println("Time limit exceeded. Press 'X' to reset to axiom..\n");
        } else {
            System.out.println("Done in " + (System.currentTimeMillis() - startTime) + "ms..\n");
        }
    }

    public static Color randomColor() {
        Random rand = new Random();
        Float r = rand.nextFloat();
        Float g = rand.nextFloat();
        Float b = rand.nextFloat();
        return Color.color(r, g, b);
    }

    private void configKeyBinds(Scene scene, Group root) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.X) {
                System.out.println("X pressed -> Resetting pattern to initial AXIOM");
                timesPressedN = 1;
                root.getChildren().clear();
                currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
                doNextIteration(root);
            } else if (e.getCode() == KeyCode.N) {
                if (timesPressedN < MAX_ITERATIONS) {
                    timesPressedN++;
                    System.out.println("N pressed " + timesPressedN + " times..");
                    root.getChildren().clear();
                    doNextIteration(root);
                } else {
                    System.out.println("Can't go for more than " + MAX_ITERATIONS + " iterations");
                }
            }
        });
    }

}
