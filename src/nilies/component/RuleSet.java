package nilies.component;

import java.util.HashMap;
import java.util.Map;

public class RuleSet {
    private Map<String, Object> rules;

    public RuleSet(){
        rules = new HashMap<>();
    }

    public void addRule(String letter, Object turnsInto){
        rules.put(letter,turnsInto);
    }

    public Map<String, Object> getRules() {
        return rules;
    }
}
