import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.shape.Shape;

public class FileHandler {
    public FileHandler()
    {

    }

    public static void saveShape(Shape shape, String path)
    {
        try (FileWriter file = new FileWriter(path)) {
            file.write("hi");
        } catch (IOException e) {
            System.err.println("zle sie dzieje");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileHandler.saveShape(null, "./hi");
    }
}

