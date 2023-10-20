package entity;

import java.util.Objects;

public class Point implements Comparable<Point>{
    public int x;
    public int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public static void main(String[] args) {
        Point a = new Point(1,2);
        Point b = new Point(1,2);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
    public double distance( Point p2) {
        double dx = getX() - p2.getX();
        double dy = getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public int compareTo(Point o) {
        return x - o.x;
    }
}
