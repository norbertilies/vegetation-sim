package nilies.component;

import java.util.*;

public class RuleSet {
    private String name;
    private String axiom;
    private Map<String, Object> rules;
    private Double alpha;
    private Integer optimalIterations;

    public RuleSet() {
        rules = new HashMap<>();
    }

    public void addRule(String letter, String turnsInto) {
        if (rules.get(letter) == null) {
            rules.put(letter, turnsInto);
        } else if (rules.get(letter) instanceof String) {
            ArrayList<String> ruleList = new ArrayList<>();
            ruleList.add(turnsInto);
            ruleList.add((String) this.rules.get(letter));
            this.rules.put(letter, ruleList);
        } else {
            ((List) rules.get(letter)).add(turnsInto);
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

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public Integer getOptimalIterations() {
        return optimalIterations;
    }

    public void setOptimalIterations(Integer optimalIterations) {
        this.optimalIterations = optimalIterations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRules(Map<String, Object> rules) {
        this.rules = rules;
    }

    public void setRuleSet(RuleSet ruleSet) {
        this.name = ruleSet.getName();
        this.alpha = ruleSet.getAlpha();
        this.axiom = ruleSet.getAxiom();
        this.optimalIterations = ruleSet.getOptimalIterations();
        this.rules = ruleSet.getRules();
    }
}
