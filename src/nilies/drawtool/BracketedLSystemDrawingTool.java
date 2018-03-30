package nilies.drawtool;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import nilies.component.TurtleLine;

import java.util.ArrayList;

import static nilies.util.Checker.isTLE;
import static nilies.util.Constants.ANGLE_STEP;
import static nilies.util.Constants.DISTANCE;
import static nilies.util.Constants.RANDOM_COLOR;
import static nilies.util.DrawingStage.randomColor;

public class BracketedLSystemDrawingTool {

    public void drawFromSentence(TurtleLine turtleLine, ArrayList<String> steps, double angle, Group root){
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
                        if (steps.get(i).equals("[")){
                            brackets++;
                        } else if (steps.get(i).equals("]")){
                            brackets--;
                        }
                        subStep.add(steps.get(i));
                        i++;
                    }
                    drawFromSentence(turtleLine.getCopy(), subStep, angle, root);
                    break;
                case "+":
                    angle += ANGLE_STEP;
                    break;
                case "-":
                    angle -= ANGLE_STEP;
                    break;
                case "F":
                    turtleLine = turtleLine.move(DISTANCE, angle);
                    turtleLine.draw(root, RANDOM_COLOR?randomColor(): Color.DARKGREEN);
                    break;
                default:
                    break;
            }
        }
    }
}
