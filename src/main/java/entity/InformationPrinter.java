package entity;

import com.example.coursework2.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.FileReader;

public class InformationPrinter {
    public static Label label;
    public static void giveInfo(int i) {
        if (label == null) {
            label = new Label();
            label.setLayoutX(210);
            label.setLayoutY(100);
            label.setTextFill(Color.BLUE);
            label.setScaleX(3);
            label.setText("Знайдено квадратів: " + FirstMethod.found + "\nЧас (мс): " + FirstMethod.time);
            CoordinatePlane.anchorPane.getChildren().add(label);
        }
        switch (i) {
            case 1:
                label.setText("Перший метод: Знайдено квадратів:\n" + FirstMethod.found + "\nЧас (мс): " + FirstMethod.time);
                FirstMethod.found = 0;
                FirstMethod.time = 0;
            break;
            case 2:
                label.setText("Другий метод: Знайдено квадратів:\n" + SecondMethod.found + "\nЧас (мс): " + SecondMethod.time);
                SecondMethod.found = 0;
                SecondMethod.time = 0;
                break;
        }
    }

}
