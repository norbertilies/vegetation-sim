package nilies;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nilies.component.RuleSet;
import nilies.util.DrawingStage;

import java.util.ArrayList;
import java.util.List;


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

        ArrayList<RuleSet> ruleSets = new ArrayList<>();
        ruleSets.add(firstSetOfRules());
        ruleSets.add(secondSetOfRules());

        DrawingStage drawingStage = new DrawingStage();
        //X -> reset to axiom; W -> next iteration; A,D -> next, previous rule set
        drawingStage.configKeyBinds(scene, root, ruleSets);
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

    private RuleSet secondSetOfRules(){
        RuleSet ruleSet = new RuleSet();

        List<String> rulesForF = new ArrayList<>();
        rulesForF.add("F[+F]F[-F]F[-F]F[+F]F");

        ruleSet.addRule("F", rulesForF);

        return ruleSet;
    }
}
