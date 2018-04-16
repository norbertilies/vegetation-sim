package nilies.component;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import nilies.util.TranslateUtil;

import static nilies.util.Constants.DISTANCE;

public class Leaf {
    private Point start;
    private Double angle;
    private static Double leafLength = DISTANCE*2/3;
    private Color color;

    public Leaf(Point start, Double angle, Color color){
        this.start = start;
        this.angle = angle;
        this.color = color;
    }

    public void draw(Group root) {
        Ellipse leaf = generateLeaf();

        leaf.setStroke(color);
        leaf.setFill(color);
        leaf.setRotate(angle+90);
        root.getChildren().add(leaf);

    }

    public Ellipse generateLeaf() {

        Point destination = TranslateUtil.moveTowardsAngle(start, angle, leafLength);

        Double centerX, centerY;
        centerX = (start.x + destination.x)/2;
        centerY = (start.y+ destination.y)/2;

        Ellipse leaf = new Ellipse(centerX, centerY, leafLength, leafLength/2);

        return leaf;
    }
}
