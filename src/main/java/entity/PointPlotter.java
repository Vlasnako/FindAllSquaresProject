package entity;

import com.example.coursework2.CoordinatePlane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class PointPlotter {
    static final int X_SHIFT = 500;
    static final int Y_SHIFT = 1000;
    public PointPlotter() {

    }
    public static void plotPoint(Point point) {
        Label label = new Label("(" + point.getX() + ";" + point.getY() + ")");
        Circle circle = new Circle(2);
        circle.setCenterX(point.getX() + X_SHIFT);
        circle.setCenterY(Y_SHIFT - point.getY());
        label.setLayoutX(point.getX() + X_SHIFT);
        label.setLayoutY(Y_SHIFT - point.getY());
        circle.setFill(Color.GREEN);
        CoordinatePlane.group.getChildren().addAll(circle);
    }
    public static void plotRectangle(Rectangle rectangle) {
        rectangle.setStroke(Color.RED);
        CoordinatePlane.group.getChildren().add(rectangle);
    }

    public void run() {
        Line x = new Line();
        x.setStartX(-1000);
        x.setStartY(Y_SHIFT);
        x.setEndX(2000);
        x.setEndY(Y_SHIFT);
        x.setStroke(Color.BLACK);
        x.setStrokeWidth(2);

        Line y = new Line();
        y.setStartX(X_SHIFT);
        y.setStartY(-1000);
        y.setEndX(X_SHIFT);
        y.setEndY(2000);

        y.setStroke(Color.BLACK);
        y.setStrokeWidth(2);

        CoordinatePlane.group.getChildren().addAll(x, y);
    }
}
