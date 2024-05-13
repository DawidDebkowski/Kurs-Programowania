import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class FileHandler {
    private static void loadShape(String save, CanvasPane canvasPane) {
        // typ figury
        String shape;
        shape = save.substring(0, 1);
        PossibleShapes possibleShape = null;
        if (shape.equals(PossibleShapes.Triangle.saveString)) {
            possibleShape = PossibleShapes.Triangle;
        } else if (shape.equals(PossibleShapes.Rectangle.saveString)) {
            possibleShape = PossibleShapes.Rectangle;
        } else if (shape.equals(PossibleShapes.Circle.saveString)) {
            possibleShape = PossibleShapes.Circle;
        }

        // parametry figury
        double[] parameters = new double[10];
        String[] subStrings = save.split(",");
        for (int i = 1; i < subStrings.length - 1; i++) {
            parameters[i - 1] = Double.parseDouble(subStrings[i]);
        }

        canvasPane.createShape(possibleShape, parameters[0], parameters[1],
                parameters[2], parameters[3],
                parameters[4], parameters[5],
                parameters[6], parameters[7],
                parameters[8],
                Color.web(subStrings[subStrings.length - 1]));
    }

    public static void loadAll(String path, CanvasPane canvasPane) {
        List<String> shapeList = readFile(path);
        for (String save : shapeList) {
            loadShape(save, canvasPane);
        }
    }

    private static List<String> readFile(String path) {
        List<String> shapeList = new ArrayList<String>();
        try (FileReader file = new FileReader(path)) {
            BufferedReader bfr = new BufferedReader(file);
            String linia = "";
            // ODCZYT KOLEJNYCH LINII Z PLIKU:
            try {
                while ((linia = bfr.readLine()) != null) {
                    shapeList.add(linia);
                }
            } catch (IOException e) {
                System.out.println("BŁĄD ODCZYTU Z PLIKU!");
                System.exit(2);
            }

            return shapeList;
        } catch (IOException e) {
            System.err.println("zle sie dzieje");
            e.printStackTrace();
            return null;
        }
    }

    public static void saveToFile(String save, String path) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(save);
        } catch (IOException e) {
            System.err.println("zle sie dzieje");
            e.printStackTrace();
        }
    }

    private static String shapeToString(SaveableShape shape) {
        String save = "";

        save += shape.getShapeType().saveString + ",";
        save += shape.getStartX() + "," + shape.getStartY() + ",";
        save += shape.getX() + "," + shape.getY() + ",";
        save += shape.getWidth() + "," + shape.getHeight() + ",";
        save += shape.getScaleX() + "," + shape.getScaleY() + ",";
        save += shape.getRotate() + ",";
        save += shape.getFill();
        save += "";

        return save;
    }

    public static void saveAll(List<SaveableShape> shapes, String path) {
        String save = "";
        for (SaveableShape shape : shapes) {
            save += FileHandler.shapeToString(shape) + "\n";
        }

        saveToFile(save, path);
    }
}
