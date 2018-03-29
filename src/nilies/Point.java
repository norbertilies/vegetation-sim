package nilies;

public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point copyOf(Point p) {
        return new Point(p.x, p.y);
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}