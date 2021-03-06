package nilies;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nilies.component.Menu;
import nilies.component.RuleSet;
import nilies.util.Constants;
import nilies.util.DrawingStage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static nilies.util.Constants.BACKGROUND_COLOR;
import static nilies.util.Constants.ruleSets;

public class VegetationSimApp extends javafx.application.Application {

    public static void main(String[] args) {
        javafx.application.Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 900, 1000, BACKGROUND_COLOR);
        stage.setScene(scene);
        stage.show();

        readPredefinedRuleSets();

        Constants.AXIOM = ruleSets.get(0).getAxiom();
        Constants.ANGLE_STEP = ruleSets.get(0).getAlpha();

        DrawingStage drawingStage = new DrawingStage();
        //X -> reset to axiom; W -> next iteration; A,D -> next, previous rule set
        drawingStage.configKeyBinds(stage, scene, root);
        new Menu(root, stage, ruleSets);

    }

    private void readPredefinedRuleSets(){
        try {
            FileReader fileReader = new FileReader("predefined-rule-sets.txt");
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            RuleSet ruleSet = readNewRuleSet(bufferedReader);
            do{
                ruleSets.add(ruleSet);
                ruleSet = readNewRuleSet(bufferedReader);
            } while (!(ruleSets.get(ruleSets.size()-1).getAxiom() == null));
            ruleSets.remove(ruleSets.size()-1);
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RuleSet readNewRuleSet(BufferedReader reader){
        RuleSet ruleSet = new RuleSet();
        try {
            String line = reader.readLine();
            if (line != null) {
                ruleSet.setName(line);
                line = reader.readLine();
                ruleSet.setAxiom(line);
                line = reader.readLine();
                ruleSet.setAlpha(Double.valueOf(line));
                line = reader.readLine();
                ruleSet.setOptimalIterations(Integer.valueOf(line));
                line = reader.readLine();
                do {
                    List<String> splitLine = Arrays.stream(line.split("->")).map(String::trim).collect(Collectors.toList());
                    ruleSet.addRule(splitLine.get(0), splitLine.get(1));
                    line = reader.readLine();
                } while (!(line == null) && !line.equals(""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ruleSet;
    }
}
