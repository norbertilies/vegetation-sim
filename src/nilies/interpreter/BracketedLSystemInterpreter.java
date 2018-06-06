package nilies.interpreter;

import nilies.exception.TLEException;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static nilies.util.TLEChecker.isTLE;

public class BracketedLSystemInterpreter {
    public static Boolean timeLimitExceeded;
    public static Long startTime;

    private Map<String, Object> rules;

    public BracketedLSystemInterpreter(Map<String, Object> rules){
        this.rules = rules;
    }

    public ArrayList<String> nextIteration(ArrayList<String> oldIt) throws TLEException {
        ArrayList<String> afterIt = new ArrayList<>();
        for (String s : oldIt){
            if (isTLE()) {
                return oldIt;
            }
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
            if (isTLE()) {
                return oldIt;
            }
            char c = appendedResult.charAt(i);
            afterIt.add(Character.toString(c));
        }
        return afterIt;
    }

    private int getRandomNumberMax(int i){
        return ThreadLocalRandom.current().nextInt(0, i);
    }
}
