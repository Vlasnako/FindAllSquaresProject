package com.example.coursework2;

import entity.FileWithPoints;
import entity.Point;
import entity.PointRetriever;
import javafx.fxml.FXML;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StartController {
    private static Set<Point> set;
    private static List<Point> list;

    @FXML
    public void loadPoints() {
        File file = FileWithPoints.getFileFromReader();
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<List<Point>> future = service.submit(new PointRetriever(file));
        try {
            list = future.get();
            set = new HashSet<>(list);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            service.shutdown();
        }
    }

    @FXML
    public void openProgramWindow() {
           CoordinatePlane.show();
    }

    public static Set<Point> points() {
        return set;
    }
    public static List<Point> pointsAsList() {
        return list;
    }
}
