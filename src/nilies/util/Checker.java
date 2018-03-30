package nilies.util;

import static nilies.interpreter.BracketedLSystemInterpreter.timeLimitExceeded;
import static nilies.interpreter.BracketedLSystemInterpreter.startTime;
import static nilies.util.Constants.TIME_TO_LIVE;

public class Checker {
    public static boolean isTLE(){
        if (!timeLimitExceeded && System.currentTimeMillis() - startTime > TIME_TO_LIVE){
            timeLimitExceeded = true;
            return true;
        }
        return false;
    }
}
