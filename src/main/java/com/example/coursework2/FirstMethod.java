package com.example.coursework2;
import entity.Point;
import entity.Rectangle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FirstMethod {
    static int id;
    public static int found;
    public static long time;

    // Slope denominator
    static int xDifference(Point a, Point b) {
        return b.getX() - a.getX();
    }

    // Slope numerator
    static int yDifference(Point a, Point b) {
        return b.getY() - a.getY();
    }

    static List<Rectangle> findAllSquares(List <Point> list) {
        try(FileWriter fileWriter = new FileWriter(new File("C:\\Users\\VlasN\\Downloads\\squares.txt"));
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            long now = System.currentTimeMillis();
            List<Rectangle> squareSet = new ArrayList<>();
            Set<Point> usedVertices = new HashSet<>();

            list.sort(Comparator.comparingDouble(p -> Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2))));

            for (Point A: list) {
                for (Point B: list) {
                    if (!A.equals(B) && !usedVertices.contains(A) && !usedVertices.contains(B)) {
                        Rectangle sq = new Rectangle();

                        sq.setA(A);
                        sq.setB(B);

                        int xdif = xDifference(A, B);
                        int ydif = yDifference(A, B);
                        if (xdif < 0 || ydif < 0) {
                            xdif = Math.abs(xdif);
                            ydif = Math.abs(ydif);
                        } else {
                            xdif *= -1;
                            ydif *= -1;
                        }

                        Point C = new Point(A.getX() + ydif, A.getY() + xdif);
                        Point D = new Point(B.getX() + ydif, B.getY() + xdif);
                        if(usedVertices.contains(C) || usedVertices.contains(D)) continue;
                        sq.setC(C);
                        sq.setD(D);

                        if (list.contains(C) && list.contains(D) && (!C.equals(D))) {
                            List<Point> points = Rectangle.getConvexPolygon(A,B, C, D);
                            if (points.get(0).distance(points.get(2)) == points.get(1).distance(points.get(3))) {
                                sq = new Rectangle(points.get(0), points.get(1), points.get(2), points.get(3));
                                sq.id = ++id;
                                bufferedWriter.write(sq+"\n");
                                squareSet.add(sq);

                                usedVertices.add(A);
                                usedVertices.add(B);
                                usedVertices.add(C);
                                usedVertices.add(D);

                                found++;
                            }
                        }
                    }
                }
            }

            long then= System.currentTimeMillis();
            time = then-now;
            return squareSet;

        } catch (IOException e) {

        }
        return null;
    }


}
