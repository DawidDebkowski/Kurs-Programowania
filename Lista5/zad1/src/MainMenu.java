import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


import javafx.event.*;
/**
 * MainMenu
 * Klasa odpowiadająca za menu
 */

public class MainMenu extends MenuBar{
    private CanvasPane canvasPane;

    public MainMenu(CanvasPane canvasPane)
    {
        this.canvasPane = canvasPane;

        InitShapeMenu();
        InitSaveMenu();
        InitInfoMenu();
    }

    private void InitSaveMenu() {
        Menu saveMenu = new Menu("Plik");
        MenuItem saveItem = new MenuItem("Zapisz");

        saveItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("zapisuje");
                FileHandler.shapeToFile((SaveableShape)canvasPane.getActiveShape());
            }
            
        });
        saveMenu.getItems().addAll(saveItem);

        MenuItem loadItem = new MenuItem("Wczytaj");

        saveItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("zapisuje");
                FileHandler.shapeToFile((SaveableShape)canvasPane.getActiveShape());
            }
            
        });
        saveMenu.getItems().addAll(saveItem);

        this.getMenus().addAll(saveMenu);
    }

    private void InitInfoMenu() {
        Menu infoMenu = new Menu("Info");
        MenuItem info = new MenuItem("Wyświetl");

        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Informacje");
        ButtonType type = new ButtonType("Ok", ButtonData.CANCEL_CLOSE);
        dialog.setContentText("""
            Nazwa: Paint 2.0
            Przeznaczenie: Program służy do prostego generowania, obracania i przesuwania prostych figur geometrycznych.
            Autor: Dawid Dębkowski"""
                );
        dialog.getDialogPane().getButtonTypes().add(type);

        info.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
                dialog.showAndWait();
            }

        } );

        infoMenu.getItems().add(info);
        this.getMenus().add(infoMenu);
    }

    //Inicjuje menu do wybierania kształtu
    private void InitShapeMenu()
    {
        Menu chooseShape = new Menu("Rysuj");

        MenuItem triangleItem = new MenuItem("Trójkąt");
        MenuItem rectangleItem = new MenuItem("Prostokąt");
        MenuItem circleItem = new MenuItem("Koło");

        class MyShapeChangeHandler implements EventHandler<ActionEvent>{
            private PossibleShapes shape;
            private CanvasPane canvasPane;

            public MyShapeChangeHandler(PossibleShapes chosenShape, CanvasPane cp)
            {
                shape = chosenShape;
                canvasPane = cp;
            }

            @Override
            public void handle(ActionEvent arg0) {
                canvasPane.setShape(shape);
            }

        }

        triangleItem.setOnAction(new MyShapeChangeHandler(PossibleShapes.Triangle, canvasPane));
        rectangleItem.setOnAction(new MyShapeChangeHandler(PossibleShapes.Rectangle, canvasPane));
        circleItem.setOnAction(new MyShapeChangeHandler(PossibleShapes.Circle, canvasPane));

        //TODO Zrobic interfejs zeby Chooseshape? i wtedy cos takiego przyjmowac?
        chooseShape.getItems().addAll(circleItem,rectangleItem,triangleItem);
        this.getMenus().add(chooseShape);
    }
}
