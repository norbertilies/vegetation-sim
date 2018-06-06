package nilies.util;

import nilies.exception.TLEException;

import static nilies.interpreter.BracketedLSystemInterpreter.timeLimitExceeded;
import static nilies.interpreter.BracketedLSystemInterpreter.startTime;
import static nilies.util.Constants.TIME_TO_LIVE;

public class TLEChecker {
    public static boolean isTLE() throws TLEException {
        if (!timeLimitExceeded && System.currentTimeMillis() - startTime > TIME_TO_LIVE){
            timeLimitExceeded = true;
            throw new TLEException();
        }
        return false;
    }
}
