package nilies.util;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import nilies.component.RuleSet;
import nilies.component.TurtleLine;
import nilies.drawtool.BracketedLSystemDrawingTool;
import nilies.interpreter.BracketedLSystemInterpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static nilies.interpreter.BracketedLSystemInterpreter.startTime;
import static nilies.interpreter.BracketedLSystemInterpreter.timeLimitExceeded;
import static nilies.util.Constants.*;

public class DrawingStage {

    private ArrayList<String> currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
    private int timesPressedN = 0;


    public void doNextIteration(Group root) {
        startTime = System.currentTimeMillis();
        timeLimitExceeded = false;

        TurtleLine line = new TurtleLine(450, 900, 450, 888, 90);

        ArrayList<RuleSet> ruleSets = new ArrayList<>();
        ruleSets.add(firstSetOfRules());

        BracketedLSystemInterpreter interpreter = new BracketedLSystemInterpreter(ruleSets.get(0).getRules());
        BracketedLSystemDrawingTool drawingTool = new BracketedLSystemDrawingTool();

        currentIteration = interpreter.nextIteration(currentIteration);
        drawingTool.drawFromSentence(line, currentIteration, 0, root);
        if (timeLimitExceeded) {
            timesPressedN--;
            System.out.println("Time limit exceeded. Press 'X' to reset to axiom..\n");
        } else {
            System.out.println("Done in " + (System.currentTimeMillis() - startTime) + "ms..\n");
        }
    }

    public void configKeyBinds(Scene scene, Group root) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.X) {
                System.out.println("X pressed -> Resetting pattern to initial AXIOM");
                timesPressedN = 1;
                root.getChildren().clear();
                currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
                doNextIteration(root);
            } else if (e.getCode() == KeyCode.N) {
                timesPressedN++;
                System.out.println("N pressed " + timesPressedN + " times..");
                root.getChildren().clear();
                doNextIteration(root);
            }
        });
    }

    private RuleSet firstSetOfRules(){
        RuleSet ruleSet = new RuleSet();

        List<String> rulesForF = new ArrayList<>();
        rulesForF.add("F[+F[F]]F[-F][-F[F]]F[+F]F");
        rulesForF.add("F[-F]F[F[+F]][-F]F[F[-F]]");
        rulesForF.add("F[+F]F[-F]F[-F]F[+F]F");

        ruleSet.addRule("F", rulesForF);

        return ruleSet;
    }

    public static Color randomColor() {
        Random rand = new Random();
        Float r = rand.nextFloat();
        Float g = rand.nextFloat();
        Float b = rand.nextFloat();
        return Color.color(r, g, b);
    }
}

