package nilies.util;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nilies.component.Point;
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

    private static Point defaultPoint = new Point(450,900);

    private ArrayList<String> currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
    public static int timesPressedW = 0;

    //returns the time it took to execute
    private Long doNextIteration(Group root, RuleSet ruleSet, Point point) throws TLEException {
        startTime = System.currentTimeMillis();
        timeLimitExceeded = false;

        TurtleLine line = new TurtleLine(point.x, point.y,point.x, point.y, 90);

        BracketedLSystemInterpreter interpreter = new BracketedLSystemInterpreter(ruleSet.getRules());
        BracketedLSystemDrawingTool drawingTool = new BracketedLSystemDrawingTool();

        currentIteration = interpreter.nextIteration(currentIteration);
        drawingTool.drawFromSentence(line, currentIteration, 0, root, 3.3);
        if (timeLimitExceeded) {
            timesPressedW--;
        }
        return System.currentTimeMillis()-startTime;
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
                    doNextIteration(root, ruleSets.get(currentRuleSet), defaultPoint);
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
                        //add time it took to execute
                        root.getChildren().add(
                                getTimeText(doNextIteration(root, ruleSets.get(currentRuleSet), defaultPoint
                                )));
                    } catch (TLEException e1) {
                        root.getChildren().add(getTLEText());
                    }
                }
            } else if (e.getCode() == KeyCode.A) {
                timesPressedW = 1;
                root.getChildren().clear();
                currentRuleSet = currentRuleSet - 1 < 0 ? ruleSets.size() - 1 : currentRuleSet - 1;
                AXIOM = ruleSets.get(currentRuleSet).getAxiom();
                ANGLE_STEP = ruleSets.get(currentRuleSet).getAlpha();
                currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
                root.getChildren().add(getIterationText());
                root.getChildren().add(getRuleSetText());
                try {
                    doNextIteration(root, ruleSets.get(currentRuleSet), defaultPoint);
                } catch (TLEException e1) {
                    root.getChildren().add(getTLEText());
                }
            } else if (e.getCode() == KeyCode.D) {
                timesPressedW = 1;
                root.getChildren().clear();
                currentRuleSet = currentRuleSet >= ruleSets.size() - 1 ? 0 : currentRuleSet + 1;
                AXIOM = ruleSets.get(currentRuleSet).getAxiom();
                ANGLE_STEP = ruleSets.get(currentRuleSet).getAlpha();
                currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
                root.getChildren().add(getIterationText());
                root.getChildren().add(getRuleSetText());
                try {
                    doNextIteration(root, ruleSets.get(currentRuleSet), defaultPoint);
                } catch (TLEException e1) {
                    root.getChildren().add(getTLEText());
                }
            } else if (e.getCode() == KeyCode.G){
                GARDEN_ONGOING = true;
                root.getChildren().clear();
                int startX, startY;
                startX = 50;
                startY = 70;
                do{
                    currentRuleSet = getRandomInteger(ruleSets.size());
                    AXIOM = ruleSets.get(currentRuleSet).getAxiom();
                    ANGLE_STEP = ruleSets.get(currentRuleSet).getAlpha();
                    currentIteration = new ArrayList<>(Collections.singletonList(AXIOM));
                    for (int i = 0;i < 4; i++){
                        try {
                            doNextIteration(root, ruleSets.get(currentRuleSet),
                                    new Point(startX + randomConvertToNegative(getRandomInteger(15)),
                                            startY + randomConvertToNegative(getRandomInteger(15))));
                        } catch (TLEException e1) {
                            //should never be reached
                            e1.printStackTrace();
                        }
                    }
                    startX += getRandomInteger(70)+30;
                    if (startX > 850){
                        startX = getRandomInteger(70)+30;
                        startY += getRandomInteger(80)+100;
                    }
                } while (startY < 1000);
                GARDEN_ONGOING = false;
            }
        });
    }

    public Integer getRandomInteger(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public Integer randomConvertToNegative(int number){
        Random rand = new Random();
        if (rand.nextBoolean())
            return -number;
        return number;
    }

    public static Color randomColor() {
        Random rand = new Random();
        Float r = rand.nextFloat();
        Float g = rand.nextFloat();
        Float b = rand.nextFloat();
        return Color.color(r, g, b);
    }

    private Text getRuleSetText(){
        Text text =  new Text(10,796, "Rule set  #" + currentRuleSet);
        text.setFill(TEXT_COLOR);
        text.setFont(Font.font(java.awt.Font.MONOSPACED, 20));
        return text;
    }

    private Text getIterationText(){
        Text text =  new Text(10,820, "Iteration #" + timesPressedW);
        text.setFill(TEXT_COLOR);
        text.setFont(Font.font(java.awt.Font.MONOSPACED, 20));
        return text;
    }

    private Text getTLEText(){
        Text text =  new Text(10,844, "Time limit exceeded.\nMaximum admited is "+TIME_TO_LIVE+"ms.\nPlease press X to reset.");
        text.setFill(TEXT_COLOR);
        text.setFont(Font.font(java.awt.Font.MONOSPACED, 20));
        return text;
    }

    private Text getTimeText(Long time){
        Text text =  new Text(10,844, "Time   "+fixedLengthString( time+"ms\n", 6));
        text.setFill(TEXT_COLOR);
        text.setFont(Font.font(java.awt.Font.MONOSPACED, 20));
        return text;
    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
}

