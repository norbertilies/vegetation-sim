package nilies.util;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nilies.component.RuleSet;
import nilies.component.TurtleLine;
import nilies.drawtool.BracketedLSystemDrawingTool;
import nilies.exception.TLEException;
import nilies.interpreter.BracketedLSystemInterpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static nilies.interpreter.BracketedLSystemInterpreter.startTime;
import static nilies.interpreter.BracketedLSystemInterpreter.timeLimitExceeded;
import static nilies.util.Constants.*;

public class DrawingStage {

    private ArrayList<String> currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
    private int timesPressedW = 0;


    public void doNextIteration(Group root, RuleSet ruleSet) throws TLEException {
        startTime = System.currentTimeMillis();
        timeLimitExceeded = false;

        TurtleLine line = new TurtleLine(450, 900, 450, 888, 90);

        BracketedLSystemInterpreter interpreter = new BracketedLSystemInterpreter(ruleSet.getRules());
        BracketedLSystemDrawingTool drawingTool = new BracketedLSystemDrawingTool();

        currentIteration = interpreter.nextIteration(currentIteration);
        drawingTool.drawFromSentence(line, currentIteration, 0, root);
        if (timeLimitExceeded) {
            timesPressedW--;
        }
    }

    public void configKeyBinds(Scene scene, Group root, ArrayList<RuleSet> ruleSets) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.X) {
                timesPressedW = 1;
                root.getChildren().clear();
                root.getChildren().add(getIterationText());
                root.getChildren().add(getRuleSetText());
                currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
                try {
                    doNextIteration(root, ruleSets.get(currentRuleSet));
                } catch (TLEException e1) {
                    root.getChildren().add(getTLEText());
                }
            } else if (e.getCode() == KeyCode.W) {
                if (timeLimitExceeded == null || !timeLimitExceeded) {
                    timesPressedW++;
                    root.getChildren().clear();
                    root.getChildren().add(getIterationText());
                    root.getChildren().add(getRuleSetText());
                    try {
                        doNextIteration(root, ruleSets.get(currentRuleSet));
                    } catch (TLEException e1) {
                        root.getChildren().add(getTLEText());
                    }
                }
            } else if (e.getCode() == KeyCode.A) {
                timesPressedW = 1;
                root.getChildren().clear();
                currentRuleSet = currentRuleSet - 1 < 0 ? ruleSets.size() - 1 : currentRuleSet - 1;
                AXIOM = ruleSets.get(currentRuleSet).getAxiom();
                currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
                root.getChildren().add(getIterationText());
                root.getChildren().add(getRuleSetText());
                try {
                    doNextIteration(root, ruleSets.get(currentRuleSet));
                } catch (TLEException e1) {
                    root.getChildren().add(getTLEText());
                }
            } else if (e.getCode() == KeyCode.D) {
                timesPressedW = 1;
                root.getChildren().clear();
                currentRuleSet = currentRuleSet >= ruleSets.size() - 1 ? 0 : currentRuleSet + 1;
                AXIOM = ruleSets.get(currentRuleSet).getAxiom();
                currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
                root.getChildren().add(getIterationText());
                root.getChildren().add(getRuleSetText());
                try {
                    doNextIteration(root, ruleSets.get(currentRuleSet));
                } catch (TLEException e1) {
                    root.getChildren().add(getTLEText());
                }
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

    private Text getRuleSetText(){
        Text text =  new Text(10,24, "Rule set  #" + currentRuleSet);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(java.awt.Font.MONOSPACED, 20));
        return text;
    }

    private Text getIterationText(){
        Text text =  new Text(10,48, "Iteration #" + timesPressedW);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(java.awt.Font.MONOSPACED, 20));
        return text;
    }

    private Text getTLEText(){
        Text text =  new Text(10,72, "Time limit exceeded.\nPlease press X to reset.");
        text.setFill(Color.WHITE);
        text.setFont(Font.font(java.awt.Font.MONOSPACED, 20));
        return text;
    }
}
