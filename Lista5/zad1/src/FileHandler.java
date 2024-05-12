import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public FileHandler()
    {

    }

    public static void saveShape(ActivableShape shape, String path)
    {
        String shapeSave;

        if()

        try (FileWriter file = new FileWriter(path)) {
            file.write("hi");
        } catch (IOException e) {
            System.err.println("zle sie dzieje");
            e.printStackTrace();
        }
    }

    private static String shapeToString(ActivableShape shape)
    {
        String shapeSave = "";

        shapeSave += shape.getShapeType().saveString + ",";


        return shapeSave;
    }

    public static void main(String[] args) {
        FileHandler.saveShape(null, "./hi");
    }
}

