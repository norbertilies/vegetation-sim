package nilies.util;

import javafx.scene.paint.Color;
import nilies.component.RuleSet;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static Double ANGLE_STEP = 22.5;
    public static Double DISTANCE = 6.0;
    public static Double LINE_STROKE_VARIATION = 0.21;
    public static String AXIOM;
    public static Long TIME_TO_LIVE = 5000L;
    public static Boolean RANDOM_COLOR = false;
    public static Color BACKGROUND_COLOR = Color.rgb(243, 255, 230);
    public static Color TEXT_COLOR = Color.DARKBLUE;
    public static Integer currentRuleSet = 0;
    public static Color STANDARD_COLOR = Color.rgb(117, 98, 67);
    public static Color LEAF_COLOR = Color.rgb(0, 204, 102);
    public static Color FLOWER_COLOR = Color.rgb(204,130,27);
    public static Boolean GARDEN_ONGOING = false;
    public static Double LEAF_LENGTH = 8.0;
    public static List<RuleSet> ruleSets = new ArrayList<>();
    public static Color FLOWER_STROKE = Color.rgb(240,90,20);
    public static Color FLOWER_CENTER_FILL = Color.YELLOW;
    public static Color LEAF_STROKE = Color.DARKGREEN;
}
