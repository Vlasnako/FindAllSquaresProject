package com.example.coursework2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import entity.FileWithPoints;
import entity.Point;
import entity.Rectangle;

public class SecondMethod {
public static long time;
public static int found;
static int id;

    public static List<Rectangle> findAllSquares(List<Point> points) {
        try(FileWriter fileWriter = new FileWriter(FileWithPoints.getFileFromReader());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {

            long now = System.currentTimeMillis();
        Map<String, List<Point[]>> map = new HashMap<>();
        List <Rectangle> squares = new ArrayList<>();
        Set <Point> traversed = new HashSet<>();
            points.sort(Comparator.comparingDouble(p -> Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2))));
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                double midX = (p1.x + p2.x) / 2.0;
                double midY = (p1.y + p2.y) / 2.0;
                double dist = Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);
                String key = midX + "," + midY + "," + dist;
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(new Point[]{p1, p2});
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                List<Point[]> pairs = map.get(key);
                for (int i = 0; i < pairs.size(); i++) {
                    for (int j = i + 1; j < pairs.size(); j++) {
                        if (traversed.contains(pairs.get(i)[0]) || traversed.contains(pairs.get(i)[1])
                                || traversed.contains(pairs.get(j)[0]) || traversed.contains(pairs.get(j)[1])) continue;
                            List <Point> vertices = Rectangle.getConvexPolygon(pairs.get(i)[0],pairs.get(i)[1], pairs.get(j)[0], pairs.get(j)[1]);
                            if (vertices.get(0).distance(vertices.get(2)) == vertices.get(1).distance(vertices.get(3))) {
                                if (Math.round(vertices.get(0).distance(vertices.get(2))) ==
                                        Math.round(Math.sqrt(2)*vertices.get(0).distance(vertices.get(1)))) {


                                    Rectangle r =  new Rectangle(pairs.get(i)[0], pairs.get(i)[1], pairs.get(j)[0], pairs.get(j)[1]);
                                    r.id = ++id;
                                    squares.add(r);
                                    bufferedWriter.write(r+"\n");
                                    traversed.add(pairs.get(i)[0]);
                                    traversed.add(pairs.get(i)[1]);
                                    traversed.add(pairs.get(j)[0]);
                                    traversed.add(pairs.get(j)[1]);
                                    found++;
                                }
                            }

                    }
                }
            }
        }
        long then = System.currentTimeMillis();
        time = then-now;
        return squares;
        } catch (IOException e) {

        }
        return null;
    }
}
