package nilies;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nilies.component.RuleSet;
import nilies.util.Constants;
import nilies.util.DrawingStage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VegetationSimApp extends javafx.application.Application {

    public static void main(String[] args) {
        javafx.application.Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 900, 900, Color.BLACK);
        stage.setScene(scene);
        stage.show();

        ArrayList<RuleSet> ruleSets = readPredefinedRuleSets();

        Constants.AXIOM = ruleSets.get(0).getAxiom();

        DrawingStage drawingStage = new DrawingStage();
        //X -> reset to axiom; W -> next iteration; A,D -> next, previous rule set
        drawingStage.configKeyBinds(scene, root, ruleSets);
    }

    private ArrayList<RuleSet> readPredefinedRuleSets(){
        ArrayList<RuleSet> ruleSets = new ArrayList<>();
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
        return ruleSets;
    }

    private RuleSet readNewRuleSet(BufferedReader reader){
        RuleSet ruleSet = new RuleSet();
        try {
            String line = reader.readLine();
            if (line != null) {
                ruleSet.setAxiom(line);
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
