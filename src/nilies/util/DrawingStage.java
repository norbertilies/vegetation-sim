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
    private int timesPressedW = 0;


    public void doNextIteration(Group root, RuleSet ruleSet) {
        startTime = System.currentTimeMillis();
        timeLimitExceeded = false;

        TurtleLine line = new TurtleLine(450, 900, 450, 888, 90);

        BracketedLSystemInterpreter interpreter = new BracketedLSystemInterpreter(ruleSet.getRules());
        BracketedLSystemDrawingTool drawingTool = new BracketedLSystemDrawingTool();

        currentIteration = interpreter.nextIteration(currentIteration);
        drawingTool.drawFromSentence(line, currentIteration, 0, root);
        if (timeLimitExceeded) {
            timesPressedW--;
            System.out.println("Time limit exceeded. Press 'X' to reset to axiom..\n");
        } else {
            System.out.println("Done in " + (System.currentTimeMillis() - startTime) + "ms..\n");
        }
    }

    public void configKeyBinds(Scene scene, Group root, ArrayList<RuleSet> ruleSets) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.X) {
                System.out.println("X pressed -> Resetting pattern to initial AXIOM");
                timesPressedW = 1;
                root.getChildren().clear();
                currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
                doNextIteration(root, ruleSets.get(currentRuleSet));
            } else if (e.getCode() == KeyCode.W) {
                timesPressedW++;
                System.out.println("W pressed " + timesPressedW + " times..");
                root.getChildren().clear();
                doNextIteration(root, ruleSets.get(currentRuleSet));
            } else if (e.getCode() == KeyCode.A) {
                System.out.println("Previous rule set..\n");
                timesPressedW = 1;
                root.getChildren().clear();
                currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
                currentRuleSet = currentRuleSet - 1 < 0 ? ruleSets.size() - 1 : currentRuleSet - 1;
                doNextIteration(root, ruleSets.get(currentRuleSet));
            } else if (e.getCode() == KeyCode.D) {
                System.out.println("Next rule set..\n");
                timesPressedW = 1;
                root.getChildren().clear();
                currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
                currentRuleSet = currentRuleSet >= ruleSets.size() - 1 ? 0 : currentRuleSet + 1;
                doNextIteration(root, ruleSets.get(currentRuleSet));
            }
        });
    }

    public static Color randomColor() {
        Random rand = new Random();
        Float r = rand.nextFloat();
        Float g = rand.nextFloat();
        Float b = rand.nextFloat();
        return Color.color(r, g, b);
    }
}

