package entity;

import com.example.coursework2.HelloApplication;
import javafx.stage.FileChooser;

import java.io.File;

public class FileWithPoints {
    public static File getFileFromReader() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(HelloApplication.stage);
    }
}
