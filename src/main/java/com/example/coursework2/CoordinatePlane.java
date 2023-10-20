package com.example.coursework2;

import entity.InformationPrinter;
import entity.PointPlotter;
import entity.Rectangle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CoordinatePlane {
    public static ScrollPane scrollPane = new ScrollPane();
    static List<Rectangle> rectangles;
    public static Group group = new Group();
    public static Pane groupPane = new Pane(group); // Wrap the Group in a Pane
    public static AnchorPane anchorPane = new AnchorPane();
    public static Button firstMethodButton = new Button("First method O(n^2)");
    public static Button secondMethodButton = new Button("Second method O(n)");

    public static void show() {
        HelloApplication.stage.setResizable(true);
        scrollPane.setContent(groupPane);
        anchorPane.getChildren().add(scrollPane);
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);

        Scene scene = new Scene(anchorPane);
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();

        PointPlotter plotter = new PointPlotter();
        plotter.run();
        StartController.points().forEach(PointPlotter::plotPoint);

        setButton();
        setButton2();

        rectangles = new ArrayList<>();
    }

    public static void setButton() {
        firstMethodButton.setLayoutX(10);
        firstMethodButton.setLayoutY(10);
        anchorPane.getChildren().add(firstMethodButton);

        firstMethodButton.setOnAction(e -> {
            rectangles = FirstMethod.findAllSquares(StartController.pointsAsList());
            if (Rectangle.builtPolys != null) Rectangle.removeConvexPolygon();
            Objects.requireNonNull(rectangles).forEach(Rectangle::buildConvexPolygon);
            InformationPrinter.giveInfo(1);
        });
    }
    public static void setButton2() {
        secondMethodButton.setLayoutX(160);
        secondMethodButton.setLayoutY(10);
        anchorPane.getChildren().add(secondMethodButton);

        secondMethodButton.setOnAction(e -> {
            rectangles = SecondMethod.findAllSquares(StartController.pointsAsList());
            if (Rectangle.builtPolys != null) Rectangle.removeConvexPolygon();
            Objects.requireNonNull(rectangles).forEach(Rectangle::buildConvexPolygon);
            InformationPrinter.giveInfo(2);
        });
    }
}


