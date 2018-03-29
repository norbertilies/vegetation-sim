package nilies;

import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.*;

import static nilies.JavaFX.randomColor;

public class BracketedLSystemInterpreter {


    double angleStep = 22.5;
    double distance = 12;




    private Map<String, String> rules = new HashMap<>();

    public void addRule(String something, String turnsInto){
        rules.put(something,turnsInto);
    }

    public ArrayList<String> nextIteration(ArrayList<String> oldIt){
        ArrayList<String> afterIt = new ArrayList<>();

        for (String s : oldIt){
            if (rules.get(s) != null){
                afterIt.add(rules.get(s));
            } else {
                afterIt.add(s);
            }
        }

        StringBuilder appendedResult = new StringBuilder();

        for (String s : afterIt){
            appendedResult.append(s);
        }

        afterIt = new ArrayList<>();

        for (int i = 0; i < appendedResult.toString().length(); i++){
            char c = appendedResult.charAt(i);
            afterIt.add(Character.toString(c));
        }
        System.out.println(appendedResult);

        return afterIt;
    }


    public void doStuff(TurtleLine turtleLine, ArrayList<String> steps, double angle, Group root){
//      F→F[+F]F[-F][F]
        for (int i = 0; i < steps.size(); i++){
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
                    doStuff(turtleLine.getCopy(), subStep, angle, root);
                    break;
                case "+":
                    angle += angleStep;
                    break;
                case "-":
                    angle -= angleStep;
                    break;
                case "F":
                    turtleLine = turtleLine.move(distance, angle);
                    turtleLine.draw(root, randomColor());
                    break;
                default:
                    break;
            }
        }
    }
}
