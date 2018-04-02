package nilies.component;

import java.util.*;

public class RuleSet {
    private String axiom;
    private Map<String, Object> rules;

    public RuleSet(){
        rules = new HashMap<>();
    }

    public void addRule(String letter, String turnsInto){
        if (rules.get(letter) == null) {
            rules.put(letter, turnsInto);
        } else if (rules.get(letter) instanceof String){
            rules.put(letter, new ArrayList<>(Collections.singletonList(turnsInto)));
        } else {
            ((List)rules.get(letter)).add(turnsInto);
        }
    }

    public Map<String, Object> getRules() {
        return rules;
    }

    public String getAxiom() {
        return axiom;
    }

    public void setAxiom(String axiom) {
        this.axiom = axiom;
    }
}
