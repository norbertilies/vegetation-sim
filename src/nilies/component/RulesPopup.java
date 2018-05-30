package nilies.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

import static nilies.util.Constants.ruleSets;

public class RulesPopup {
    private ListView<String> list;
    private final Stage popup;

    public RulesPopup(Stage mainStage) {
        popup = new Stage();

        Group root = new Group();
        Scene popupScene = new Scene(root, 300, 400, Color.LIGHTGRAY);
        popup.initOwner(mainStage);
        popup.setScene(popupScene);
        popup.show();

        list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(ruleSets.stream().map(RuleSet::getName).collect(Collectors.toList()));
        list.setItems(items);
        list.setPrefHeight(360);
        list.setPrefWidth(185);
        list.setLayoutX(10);
        list.setLayoutY(10);

        list.setCellFactory(ComboBoxListCell.forListView(items));

        root.getChildren().add(getAddButton());
        root.getChildren().add(getDeleteButton());
        root.getChildren().add(getEditButton());

        root.getChildren().add(list);

    }

    private Button getAddButton() {
        Button btn = new Button("Add");
        btn.setLayoutX(200);
        btn.setLayoutY(10);
        btn.setPrefHeight(30);
        btn.setPrefWidth(95);

        btn.setOnAction(event -> {
            createPopupWithRuleSetDetails(null);
        });
        return btn;
    }

    private Button getEditButton() {
        Button btn = new Button("Edit");
        btn.setLayoutX(200);
        btn.setLayoutY(45);
        btn.setPrefHeight(30);
        btn.setPrefWidth(95);

        btn.setOnAction(event -> {
            String toEdit = "";
            if (list.getSelectionModel().getSelectedItems() != null)
                toEdit = list.getSelectionModel().getSelectedItems().get(0);
            if (!toEdit.equals("")) {
                for (int i = 0; i < ruleSets.size(); i++)
                    if (ruleSets.get(i).getName().equals(toEdit)) {
                        createPopupWithRuleSetDetails(ruleSets.get(i));
                        break;
                    }
            }
        });

        return btn;
    }

    private Button getDeleteButton() {
        Button btn = new Button("Delete");
        btn.setLayoutX(200);
        btn.setLayoutY(80);
        btn.setPrefHeight(30);
        btn.setPrefWidth(95);

        btn.setOnAction(event -> {
            String toDelete = "";
            if (list.getSelectionModel().getSelectedItems() != null)
                toDelete = list.getSelectionModel().getSelectedItems().get(0);
            if (!toDelete.equals("")) {
                list.getItems().remove(toDelete);

                RuleSet ruleSetToDelete = null;

                for (RuleSet ruleSet : ruleSets)
                    if (ruleSet.getName().equals(toDelete)) {
                        ruleSetToDelete = ruleSet;
                        break;
                    }

                ruleSets.remove(ruleSetToDelete);
            }
        });
        return btn;
    }

    private void createPopupWithRuleSetDetails(RuleSet ruleSet) {
        final Stage popup = new Stage();

        Group root = new Group();
        Scene popupScene = new Scene(root, 300, 520, Color.LIGHTGRAY);
        popup.initOwner(this.popup);
        popup.setScene(popupScene);
        popup.show();

        Label nameLabel = new Label("Name: ");
        TextField name = new TextField();
        name.setLayoutX(90);
        name.setLayoutY(10);
        nameLabel.setLayoutX(10);
        nameLabel.setLayoutY(14);
        nameLabel.setLabelFor(name);

        Label axiomLabel = new Label("Axiom: ");
        TextField axiom = new TextField();
        axiom.setLayoutX(90);
        axiom.setLayoutY(45);
        axiomLabel.setLayoutX(10);
        axiomLabel.setLayoutY(49);
        axiomLabel.setLabelFor(name);

        Label iterationsLabel = new Label("Iterations: ");
        TextField iterations = new TextField();
        iterations.setLayoutX(90);
        iterations.setLayoutY(80);
        iterationsLabel.setLayoutX(10);
        iterationsLabel.setLayoutY(84);
        iterationsLabel.setLabelFor(name);

        Label alphaLabel = new Label("Alpha: ");
        TextField alpha = new TextField();
        alpha.setLayoutX(90);
        alpha.setLayoutY(115);
        alphaLabel.setLayoutX(10);
        alphaLabel.setLayoutY(119);
        alphaLabel.setLabelFor(name);

        Label rulesLabel = new Label("Rules: ");
        TextArea rules = new TextArea();
        rules.setLayoutX(10);
        rules.setLayoutY(150);
        rules.setPrefWidth(270);
        rulesLabel.setLayoutX(10);
        rulesLabel.setLayoutY(154);
        rulesLabel.setLabelFor(name);



        if (ruleSet == null) {
            Button add = new Button("Add");
            add.setPrefWidth(100);
            add.setLayoutX(100);
            add.setLayoutY(470);
            add.setOnAction(event -> {
                RuleSet addedRuleSet = new RuleSet();
                addedRuleSet.setName(name.getText());
                addedRuleSet.setAxiom(axiom.getText());
                addedRuleSet.setOptimalIterations(Integer.valueOf(iterations.getText()));
                addedRuleSet.setAlpha(Double.valueOf(alpha.getText()));

                ruleSets.forEach(r -> {
                    if (r.getName().equals(addedRuleSet.getName())){
                        System.out.println("names need to be unique");
                    }
                });

                Arrays.stream(rules.getText().split("\\r?\\n"))
                        .forEach(line -> {
                            String[] rule = line.replaceAll("\\s+","").split("->");
                            if (rule.length == 2)
                                addedRuleSet.addRule(rule[0], rule[1]);
                        });
                ruleSets.add(addedRuleSet);
                popup.close();
                list.getItems().add(addedRuleSet.getName());
            });

            root.getChildren().add(add);
        } else {
            Button save = new Button("Save");
            save.setPrefWidth(100);
            save.setLayoutX(100);
            save.setLayoutY(470);

            name.setText(ruleSet.getName());
            alpha.setText(String.valueOf(ruleSet.getAlpha()));
            iterations.setText(String.valueOf(ruleSet.getOptimalIterations()));
            axiom.setText(ruleSet.getAxiom());
            ruleSet.getRules().forEach((key, value) -> {
                if (value instanceof String)
                    rules.appendText(key + " -> " + value + "\n");
                else {
                    List<String> list = (ArrayList)value;
                    for (String s : list)
                        rules.appendText(key + " -> " + s + "\n");
                }
            });

            root.getChildren().add(save);

            save.setOnAction(event -> {
                list.getItems().remove(ruleSet.getName());

                RuleSet newRuleSet = new RuleSet();
                newRuleSet.setName(ruleSet.getName());
                newRuleSet.setAlpha(ruleSet.getAlpha());
                newRuleSet.setOptimalIterations(ruleSet.getOptimalIterations());
                newRuleSet.setAxiom(ruleSet.getAxiom());

                Arrays.stream(rules.getText().split("\\r?\\n"))
                        .forEach(line -> {
                            String[] rule = line.replaceAll("\\s+","").split("->");
                            if (rule.length == 2)
                                newRuleSet.addRule(rule[0], rule[1]);
                        });
                ruleSet.setRuleSet(newRuleSet);

                int indexToRemove = -1;
                for (int i =0 ;i < ruleSets.size(); i++){
                    if (ruleSets.get(i).getName().equals(ruleSet.getName())){
                        indexToRemove = i;
                        break;
                    }
                }

                if (indexToRemove >= 0 && indexToRemove < ruleSets.size())
                    ruleSets.remove(indexToRemove);
                ruleSets.add(ruleSet);

                popup.close();
                list.getItems().add(ruleSet.getName());
            });
        }

        root.getChildren().add(nameLabel);
        root.getChildren().add(name);
        root.getChildren().add(axiomLabel);
        root.getChildren().add(axiom);
        root.getChildren().add(iterationsLabel);
        root.getChildren().add(iterations);
        root.getChildren().add(rulesLabel);
        root.getChildren().add(rules);
        root.getChildren().add(alphaLabel);
        root.getChildren().add(alpha);
    }
}