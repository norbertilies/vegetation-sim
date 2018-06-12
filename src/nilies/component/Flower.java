package nilies.component;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import nilies.util.TranslateUtil;

import java.util.ArrayList;
import java.util.List;

import static nilies.util.Constants.FLOWER_CENTER_FILL;
import static nilies.util.Constants.FLOWER_STROKE;

public class Flower {
    private Point point;
    private Integer numberOfPetalsPerHalf;
    private static Double petalLength = 8.0;

    public Flower(Point point, Integer numberOfPetalsPerHalf){
        this.point = point;
        this.numberOfPetalsPerHalf = numberOfPetalsPerHalf;
    }

    public void draw(Group root, Color color) {
        List<Ellipse> petalPair = generateFlower();

        petalPair.forEach(p -> {
            p.setStroke(FLOWER_STROKE);
            p.setFill(color);
            root.getChildren().add(p);
        });

        Circle circle = new Circle();
        circle.setCenterX(point.x);
        circle.setCenterY(point.y - petalLength/2);
        circle.setRadius(2.5);
        circle.setFill(FLOWER_CENTER_FILL);

        root.getChildren().add(circle);

    }

    private List<Ellipse> generateFlower() {
        List<Ellipse> petals = new ArrayList<>();
        Double angleOfPetal = (180.0/numberOfPetalsPerHalf);

        for (int i = 0; i < numberOfPetalsPerHalf; i++) {

            Point destination = TranslateUtil.moveTowardsAngle(point, 90.0, petalLength);

            Double centerX, centerY;
            centerX = (point.x + destination.x) / 2;
            centerY = (point.y + destination.y) / 2;

            Ellipse petal = new Ellipse(centerX, centerY, petalLength, petalLength / 4);
            petal.setRotate(90.0+angleOfPetal*(i+1));

            petals.add(petal);
        }
        return petals;
    }
}