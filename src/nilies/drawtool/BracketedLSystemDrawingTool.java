package nilies.drawtool;

import javafx.scene.Group;
import nilies.component.Flower;
import nilies.component.Leaf;
import nilies.component.TurtleLine;
import nilies.exception.TLEException;
import nilies.util.DrawingStage;

import java.util.ArrayList;
import java.util.Random;

import static nilies.util.Checker.isTLE;
import static nilies.util.Constants.*;
import static nilies.util.DrawingStage.randomColor;

public class BracketedLSystemDrawingTool {

    public void drawFromSentence(TurtleLine turtleLine, ArrayList<String> steps, double angle, Group root, Double stroke) throws TLEException {
        for (int i = 0; i < steps.size(); i++){
            if (isTLE()) {
                break;
            }
            switch (steps.get(i)) {
                case "[":
                    i++;
                    int brackets = 0;
                    ArrayList<String> subStep = new ArrayList<>();

                    while ((i < steps.size() && !steps.get(i).equals("]")) || brackets != 0) {
                        if (steps.get(i).equals("[")) {
                            brackets++;
                        } else if (steps.get(i).equals("]")) {
                            brackets--;
                        }
                        subStep.add(steps.get(i));
                        i++;
                    }
                    drawFromSentence(turtleLine.getCopy(), subStep, angle, root, stroke - LINE_STROKE_VARIATION);
                    break;
                case "+":
                    angle += ANGLE_STEP;
                    break;
                case "-":
                    angle -= ANGLE_STEP;
                    break;
                case "F":
                    turtleLine = turtleLine.move(DISTANCE, angle);
                    turtleLine.draw(root, STANDARD_COLOR, stroke);
                    break;
                case "X":
                    if (generateRandomSubunitarNumber() < 0.92) {
                        if ((DrawingStage.timesPressedW > 2 || GARDEN_ONGOING) && angle != 90) {
                            Leaf leaf = new Leaf(turtleLine.getP1(), angle, RANDOM_COLOR ? randomColor() : LEAF_COLOR);
                            leaf.draw(root);
                        }
                    } else {
                        new Flower(turtleLine.getP1(), 5).draw(root, FLOWER_COLOR);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private Double generateRandomSubunitarNumber(){
        Random rand = new Random();
        return rand.nextDouble();
    }
}
