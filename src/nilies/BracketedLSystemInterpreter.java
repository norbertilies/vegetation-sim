package nilies;

import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static nilies.Constants.angleStep;
import static nilies.Constants.distance;


public class BracketedLSystemInterpreter {

    private Map<String, Object> rules = new HashMap<>();

    public void addRule(String something, Object turnsInto){
        rules.put(something,turnsInto);
    }

    public ArrayList<String> nextIteration(ArrayList<String> oldIt){
        ArrayList<String> afterIt = new ArrayList<>();

        for (String s : oldIt){
            if (rules.get(s) != null){
                if (rules.get(s) instanceof String)
                    afterIt.add((String)rules.get(s));
                else if (rules.get(s) instanceof List){
                    afterIt.add((String)((List)rules.get(s)).get(getRandomNumberMax(((List)rules.get(s)).size())));
                }
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

    public void drawSentence(TurtleLine turtleLine, ArrayList<String> steps, double angle, Group root){
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
                    drawSentence(turtleLine.getCopy(), subStep, angle, root);
                    break;
                case "+":
                    angle += angleStep;
                    break;
                case "-":
                    angle -= angleStep;
                    break;
                case "F":
                    turtleLine = turtleLine.move(distance, angle);
                    turtleLine.draw(root, Color.DARKGREEN);
                    break;
                default:
                    break;
            }
        }
    }

    public int getRandomNumberMax(int i){
        return ThreadLocalRandom.current().nextInt(0, i);
    }
}
