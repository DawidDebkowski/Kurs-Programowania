import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * Klasa statyczna służąca do zapisywania i wczytywania stanu CanvasPane
 * 
 * @see CanvasPane
 */
public class FileHandler {
    /**
     * Metoda obsługująca wybranie pliku zapisu przez użytkownika.
     * @param root okno do którego okno wybierania pliku będzie przyczepione
     * @return ścieżka do pliku
     */
    public static String getSaveFile(Window root) {
        FileChooser fc = new FileChooser();
        fc.setInitialFileName("figury.txt");
        ExtensionFilter filter = new ExtensionFilter("TXT File .txt", "*.txt");
        fc.getExtensionFilters().add(filter);
        fc.setSelectedExtensionFilter(filter);
        File f = fc.showSaveDialog(root);
        if (f == null) {
            MainMenu.ShowError("Błędnie wybrany plik. Nie zapisano!");
            return null;
        }
        return f.getAbsolutePath();
    }

    /**
     * Metoda obsługująca wybranie pliku wczytania przez użytkownika.
     * @param root okno do którego okno wybierania pliku będzie przyczepione
     * @return ścieżka do pliku
     */
    public static String getOpenFile(Window root) {
        FileChooser fc = new FileChooser();
        ExtensionFilter filter = new ExtensionFilter("TXT File .txt", "*.txt");
        fc.getExtensionFilters().add(filter);
        fc.setSelectedExtensionFilter(filter);
        File f = fc.showOpenDialog(root);
        if (f == null) {
            MainMenu.ShowError("Błędnie wybrany plik. Nie wczytano!");
            return null;
        }
        return f.getAbsolutePath();
    }

    /**
     * Tworzy wszystkie figury z danego pliku na danym canvas.
     * 
     * @param path       ścieżka do pliku zapisu
     * @param canvasPane canvas na którym figury mają być tworzone
     */
    public static void loadAll(String path, CanvasPane canvasPane) {
        List<String> shapeList = readFile(path);
        for (String save : shapeList) {
            loadShape(save, canvasPane);
        }
    }

    private static List<String> readFile(String path) {
        List<String> shapeList = new ArrayList<String>();
        try (FileReader file = new FileReader(path)) {
            // czyta kolejne linie z pliku
            BufferedReader bfr = new BufferedReader(file);
            String linia = "";
            while ((linia = bfr.readLine()) != null) {
                shapeList.add(linia);
            }
            return shapeList;
        } catch (IOException e) {
            MainMenu.ShowError("Nie można odczytać pliku");
            e.printStackTrace();
            return null;
        }
    }

    private static void saveToFile(String save, String path) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(save);
        } catch (IOException e) {
            MainMenu.ShowError("Nie można zapisać do pliku");
            e.printStackTrace();
        }
    }

    // LADOWANIE
    private static void loadShape(String save, CanvasPane canvasPane) {
        // typ figury
        String shape;
        shape = save.substring(0, 1);
        MShapeTypes possibleShape = null;
        if (shape.equals(MShapeTypes.Triangle.saveString)) {
            possibleShape = MShapeTypes.Triangle;
        } else if (shape.equals(MShapeTypes.Rectangle.saveString)) {
            possibleShape = MShapeTypes.Rectangle;
        } else if (shape.equals(MShapeTypes.Circle.saveString)) {
            possibleShape = MShapeTypes.Circle;
        }

        // parametry figury
        double[] parameters = new double[10];
        String[] subStrings = save.split(",");
        for (int i = 1; i < subStrings.length - 1; i++) {
            try {
                parameters[i - 1] = Double.parseDouble(subStrings[i]);
            } catch (NumberFormatException e) {
                MainMenu.ShowError("Błędny plik zapisu");
            }
        }

        canvasPane.createShape(possibleShape, parameters[0], parameters[1],
                parameters[2], parameters[3],
                parameters[4], parameters[5],
                parameters[6], parameters[7],
                parameters[8],
                Color.web(subStrings[subStrings.length - 1]));
    }

    /**
     * Zapisuje wszystkie podane figury.
     * 
     * @param shapes
     * @param path
     */
    public static void saveAll(List<SaveableShape> shapes, String path) {
        String save = "";
        for (SaveableShape shape : shapes) {
            save += FileHandler.shapeToString(shape) + "\n";
        }

        saveToFile(save, path);
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

}
