import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.paint.Color;

public class FileHandler {
    public FileHandler()
    {

    }

    public static void loadShape(String save, CanvasPane canvasPane)
    {
        String shape;
        shape = save.substring(0,1);
        System.err.println(shape);
        PossibleShapes possibleShape = null;
        if(shape.equals(PossibleShapes.Triangle.saveString))
        {
            possibleShape = PossibleShapes.Triangle;
        }
        else if(shape == PossibleShapes.Rectangle.saveString)
        {
            possibleShape = PossibleShapes.Rectangle;
        }
        else if(shape.equals(PossibleShapes.Circle.saveString))
        {
            System.err.println("cicle");
            possibleShape = PossibleShapes.Circle;
        }
        
        System.err.println(possibleShape);
        double[] parameters = new double[10];
        String[] subStrings = save.split(",");
        for(int i=1;i<subStrings.length-1;i++)
        {
            parameters[i-1] = Double.parseDouble(subStrings[i]);
        }


        canvasPane.createShape(possibleShape, parameters[0], parameters[1], 
        parameters[2], parameters[3], 
        parameters[4], parameters[5], 
        Color.ALICEBLUE);
    }

    public static String readFile(String path)
    {
        try (FileReader file = new FileReader(path)) {
            BufferedReader bfr = new BufferedReader(file);
            String linia = "";
            // ODCZYT KOLEJNYCH LINII Z PLIKU:
            try {
                while((linia = bfr.readLine()) != null){
                return linia;
            }
            } catch (IOException e) {
                System.out.println("BŁĄD ODCZYTU Z PLIKU!");
                System.exit(2);
            }

            return linia;
        } catch (IOException e) {
            System.err.println("zle sie dzieje");
            e.printStackTrace();
            return "false";
        }
    }

    public static void saveToFile(String save, String path)
    {
        try (FileWriter file = new FileWriter(path)) {
            file.write(save);
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
        save += shape.getX() + "," + shape.getY()+ ",";
        save += shape.getWidth() + "," + shape.getHeight()+ ",";
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

