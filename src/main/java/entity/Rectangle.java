package entity;
import com.example.coursework2.CoordinatePlane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import java.util.*;
public class Rectangle {
    public static List <Polygon> builtPolys = new ArrayList<>();
    public int id;
    private Point A;
    private Point B;
    private Point C;
    private Point D;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        List<Point> points = List.of(A, B, C, D);
        List<Point> other = List.of(rectangle.A, rectangle.B, rectangle.C, rectangle.D);
        for (Point p : points) {
            if (other.contains(p)) return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(A)
                + Objects.hash(B)
                + Objects.hash(C)
                + Objects.hash(D);
    }
    public static void main(String[] args) {
        Point A = new Point(0, 100);
        Point B = new Point(100, 0);
        Point C = new Point(-100, 0);
        Point D = new Point(0, -100);
        Rectangle rectangle = new Rectangle(A, B, C, D);
        Rectangle rectangle1 = new Rectangle(A, D, C, B);
        System.out.println(rectangle1.hashCode());
        System.out.println(rectangle.hashCode());
    }
    public void setA(Point a) {
        A = a;
    }
    public void setB(Point b) {
        B = b;
    }
    public void setC(Point c) {
        C = c;
    }
    public void setD(Point d) {
        D = d;
    }
    public Rectangle(Point a, Point b, Point c, Point d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }
    public Rectangle() {}
    @Override
    public String toString() {

        return "Figure "+id+": " +
                "x1=" + A.getX() +
                "; y1=" + A.getY()+
                "; x2=" + B.getX() +
                "; y2=" + B.getY() +
                "; x3=" + C.getX() +
                "; y3=" + C.getY() +
                "; x4=" + D.getX() +
                "; y4=" + D.getY() +";";
    }
     public static void buildConvexPolygon(Rectangle r) {

        List<Point> points = new ArrayList<>();
        points.add(r.A);
        points.add(r.B);
        points.add(r.C);
        points.add(r.D);
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.getX() != p2.getX()) {
                    return -Double.compare(p1.getX(), p2.getX());
                } else {
                    return -Double.compare(p1.getY(), p2.getY());
                }
            }
        });
        List<Point> convexHull = grahamScan(points);
        if (convexHull.size() >= 3) {
            Polygon polygon = new Polygon();
            for (Point point : convexHull) {
                polygon.getPoints().addAll(
                        (double) point.getX() + PointPlotter.X_SHIFT,
                        (double) PointPlotter.Y_SHIFT - point.getY()
                );
            }
            polygon.setStroke(Color.RED);
            polygon.setFill(Color.TRANSPARENT);
            builtPolys.add(polygon);
            CoordinatePlane.group.getChildren().add(polygon);
        } else {
            System.out.println("These points do not form a convex polygon.");
        }
    }
    public static void removeConvexPolygon() {
        builtPolys.stream().forEach(p -> CoordinatePlane.group.getChildren().remove(p));
        builtPolys.clear();
    }
    public static List<Point> getConvexPolygon(Point A, Point B, Point C, Point D) {
        List<Point> corners = new ArrayList<>();
        corners.add(A);
        corners.add(B);
        corners.add(C);
        corners.add(D);
        if(corners.size() == 4) {
            List<Point>ordCorners = orderPointsByRows(corners);

            if(ordCorners.get(0).x > ordCorners.get(1).x) { // swap points
                Point tmp = ordCorners.get(0);
                ordCorners.set(0, ordCorners.get(1));
                ordCorners.set(1, tmp);
            }
            if(ordCorners.get(2).x < ordCorners.get(3).x) { // swap points
                Point tmp = ordCorners.get(2);
                ordCorners.set(2, ordCorners.get(3));
                ordCorners.set(3, tmp);
            }
            return ordCorners;
        }
        return null;
    }
    static List<Point> orderPointsByRows(List<Point> points) {
        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                if (p1.y < p2.y) return -1;
                if (p1.y > p2.y) return 1;
                return 0;
            }
        });
        return points;
    }
    private static List<Point> grahamScan(List<Point> points) {
        Point anchor = findAnchorPoint(points);
        points.sort((p1, p2) -> {
            double angle1 = Math.atan2(p1.getY() - anchor.getY(), p1.getX() - anchor.getX());
            double angle2 = Math.atan2(p2.getY() - anchor.getY(), p2.getX() - anchor.getX());
            return Double.compare(angle1, angle2);
        });
        return new ArrayList<>(points);
    }
    private static Point findAnchorPoint(List<Point> points) {
        Point anchor = points.get(0);
        for (Point point : points) {
            if (point.getY() < anchor.getY() || (point.getY() == anchor.getY() && point.getX() < anchor.getX())) {
                anchor = point;
            }
        }
        return anchor;
    }
}
