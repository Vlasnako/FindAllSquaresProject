package entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PointRetriever implements Callable<List<Point>> {
    private  List<Point> list = new ArrayList<>();
    private  File file;
    public PointRetriever(File file) {
        this.file = file;

    }
    public void readPoints(File file) {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String points;

            while (bufferedReader.ready()) {
                points = bufferedReader.readLine();
                if (points.isEmpty()) continue;
                int firstIndex = points.indexOf('=');
                int secondIndex = points.indexOf(';');
                String x = points.substring(firstIndex + 1, secondIndex);
                x = x.trim();
                int pointX = Integer.parseInt(x);
                int thirdIndex = points.indexOf('=', secondIndex);
                int fourthIndex = points.indexOf(';', thirdIndex);
                String y = points.substring(thirdIndex + 1, fourthIndex);
                y = y.trim();
                int pointY = Integer.parseInt(y);
                Point point = new Point(pointX, pointY);
                PointPlotter.plotPoint(point);
                list.add(point);

            }
        } catch (IOException | NullPointerException e) {
            System.out.println("File not chosen");
        }
    }


    @Override
    public List<Point> call() {
        readPoints(file);
        return list;
    }
}
