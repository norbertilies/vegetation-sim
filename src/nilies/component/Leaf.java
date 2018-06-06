package nilies.component;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import nilies.util.TranslateUtil;

import java.util.ArrayList;
import java.util.List;

import static nilies.util.Constants.LEAF_LENGTH;
import static nilies.util.Constants.LEAF_STROKE;


public class Leaf {
    private Point start;
    private Double angle;
    private Color color;

    public Leaf(Point start, Double angle, Color color){
        this.start = start;
        this.angle = angle;
        this.color = color;
    }

    public void draw(Group root) {
        List<QuadCurve> leaf = generateLeaf();

        leaf.forEach( l -> {
            l.setStroke(LEAF_STROKE);
            l.setFill(color);
            root.getChildren().add(l);
        });
    }

    public List<QuadCurve> generateLeaf() {
        List<QuadCurve> quadCurves = new ArrayList<>();

        Point destination = TranslateUtil.moveTowardsAngle(start, angle, LEAF_LENGTH);

        QuadCurve a,b;
        a = new QuadCurve(start.x, start.y, start.x+LEAF_LENGTH/2, start.y+LEAF_LENGTH/2, destination.x, destination.y);
        b = new QuadCurve(start.x, start.y, start.x+LEAF_LENGTH/2, start.y-LEAF_LENGTH/2, destination.x, destination.y);
        quadCurves.add(a);
        quadCurves.add(b);

        return quadCurves;
    }
}
