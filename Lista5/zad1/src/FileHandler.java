import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public FileHandler()
    {

    }

    public static void saveToFile(String string, String path)
    {
        try (FileWriter file = new FileWriter(path)) {
            file.write(string);
        } catch (IOException e) {
            System.err.println("zle sie dzieje");
            e.printStackTrace();
        }
    }

    public static void shapeToFile(SaveableShape shape)
    {
        saveToFile(shapeToString(shape), "./shape");
    }

    private static String shapeToString(SaveableShape shape)
    {
        String save = "";

        save += shape.getShapeType().saveString + ",";
        save += shape.getStartX() + "," + shape.getStartY()+ ",";
        save += shape.getWidth() + "," + shape.getHeight()+ ",";
        save += shape.getTranslateX() + "," + shape.getTranslateY()+ ",";
        save += shape.getScaleX() + "," + shape.getScaleY()+ ",";
        save += shape.getRotate() + ",";
        save += shape.getFill();
        save += ";";

        return save;
    }

    public static void main(String[] args) {
        // FileHandler.saveShape(shapeToString(null), "./hi");
    }
}

