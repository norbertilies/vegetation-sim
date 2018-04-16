package nilies.component;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import nilies.util.TranslateUtil;

public class TurtleLine {
    private Point p1, p2;
    private double alpha;

    public TurtleLine(double x1, double y1, double x2, double y2, double alpha) {
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
        this.alpha = alpha;
    }

    public void draw(Group root, Color color, Double stroke) {
        Line line = new Line(p1.x, p1.y, p2.x, p2.y);
        line.setStroke(color);
        line.setStrokeLineCap(StrokeLineCap.BUTT);
        line.setStrokeWidth(stroke);

        root.getChildren().add(line);

    }

    public TurtleLine move(Double distance, Double angle) {
        alpha = 90;
        double newAlpha = (alpha + angle) % 360;

        Point destination = TranslateUtil.moveTowardsAngle(p2, newAlpha, distance);

        return new TurtleLine(p2.x, p2.y, destination.x, destination.y, newAlpha);
    }

    @Override
    public String toString() {
        return p1 + " " + p2 + ", angle: " + alpha;
    }

    public TurtleLine getCopy() {
        return new TurtleLine(p1.x, p1.y, p2.x, p2.y, alpha);
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }
}