package nilies;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

import static java.lang.Math.*;
import static nilies.Constants.lineStroke;

public class TurtleLine {
    Point p1, p2;
    double alpha;

    TurtleLine(double x1, double y1, double x2, double y2, double alpha) {
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
        this.alpha = alpha;
    }

    public void draw(Group root, Color color) {
        Line line = new Line(p1.x, p1.y, p2.x, p2.y);
        line.setStroke(color);
        line.setStrokeLineCap(StrokeLineCap.BUTT);
        line.setStrokeWidth(lineStroke);

        root.getChildren().add(line);

    }

    public TurtleLine move(Double distance, Double angle) {
        alpha = 90;
        double newAlpha = (alpha + angle) % 360;

        double newX, newY;

        if (angleInBetween(0, 90)) {
            newX = p2.x + distance * cos(Math.toRadians(newAlpha));
            newY = p2.y - distance * sin(Math.toRadians(newAlpha));
        } else if (angleInBetween(91, 179)) {
            newX = p2.x - distance * cos(Math.toRadians(180 - newAlpha));
            newY = p2.y - distance * sin(Math.toRadians(180 - newAlpha));
        } else if (angleInBetween(180, 270)) {
            newX = p2.x - distance * cos(Math.toRadians(newAlpha - 180));
            newY = p2.y + distance * sin(Math.toRadians(newAlpha - 180));
        } else {
            newX = p2.x + distance * sin(Math.toRadians(newAlpha - 270));
            newY = p2.y + distance * cos(Math.toRadians(newAlpha - 270));
        }
        return new TurtleLine(p2.x, p2.y, newX, newY, newAlpha);
    }

    private boolean angleInBetween(double x, double y) {
        return (alpha >= x && alpha <= y);
    }

    @Override
    public String toString() {
        return p1 + " " + p2 + ", angle: " + alpha;
    }

    public TurtleLine getCopy() {
        return new TurtleLine(p1.x, p1.y, p2.x, p2.y, alpha);
    }
}