import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javafx.event.*;

/**
 * Klasa odpowiadająca za menu
 */

public class MainMenu extends MenuBar {
    private CanvasPane canvasPane;

    public MainMenu(CanvasPane canvasPane) {
        this.canvasPane = canvasPane;

        InitShapeMenu();
        InitSaveMenu();
        InitInfoMenu();
    }

    private void InitSaveMenu() {
        Menu saveMenu = new Menu("Plik");
        MenuItem saveItem = new MenuItem("Zapisz");
        MenuItem loadItem = new MenuItem("Wczytaj");
        saveItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                List<SaveableShape> shapes = new ArrayList<SaveableShape>();
                System.out.println(canvasPane.getChildren());
                for (Node node : canvasPane.getChildren()) {
                    try {
                        shapes.add((SaveableShape) node);
                    } catch (ClassCastException e) {
                        System.out.println("Inne dziecko - " + node);
                    }
                }
                FileHandler.saveAll(shapes, "./shape");
            }
        });

        loadItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("laduje");
                FileHandler.loadAll("./shape", canvasPane);
            }
        });

        saveMenu.getItems().addAll(saveItem, loadItem);

        this.getMenus().addAll(saveMenu);
    }

    private void InitInfoMenu() {
        Menu infoMenu = new Menu("Info");

        //Pomocnicza klasa aby łatwiej tworzyć proste okienko dialogowe
        class DialogMenuItem extends MenuItem {
            DialogMenuItem(String menuTitle, String dialogTitle, String dialogText, double width, double height)
            {
                super(menuTitle);
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle(dialogTitle);
                ButtonType type = new ButtonType("Ok", ButtonData.CANCEL_CLOSE);
                dialog.setContentText(dialogText);
                dialog.getDialogPane().setMinSize(width, height);
                dialog.getDialogPane().getButtonTypes().add(type);
                
                this.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        dialog.showAndWait();
                    }
                });
            }
        }
        String infoDialogText = 
        """     
                Nazwa: Paint 2.0
                Przeznaczenie: Program służy do generowania, obracania i przesuwania prostych figur geometrycznych.
                Autor: Dawid Dębkowski""";
        String instructionDialogText = """
                1. Rysowanie figur
                    Menu Głowne -> Rysuj
                        Tutaj możesz wybrać figurę. Aby ją narysować zacznij przeciągać po planszy do rysowania.
                2. Edytowanie figury
                    Aby oznaczyć figuę jako aktywną kliknij w nią lewym przyciskiem myszy.
                    Aktywną figurę można:
                        a) przesunąć za pomocą przyciśnięcia a potem poruszania myszą
                        b) zmniejszyć lub zwiększyć za pomocą scrolla
                        c) obrócić za pomocą menu kontekstowego dostępnego pod prawym przyciskiem myszy
                        d) zmienić jej kolor za pomocą menu kontekstowego dostępnego pod prawym przyciskiem myszy
                3. Zapisywanie narysowanych figur
                    Menu Głowne -> Plik -> Zapisz 
                        Zapisuje stan całej planszy do poźniejszego wczytania.
                    Menu Głowne -> Plik -> Wczytaj
                        Pozwala wybrać plik do wczytania na planszy. 
                    To co zostało już narysowane nie będzie usunięte w żadnym przypadku.
                """;
        DialogMenuItem info = new DialogMenuItem("O Programie", "Informacje", infoDialogText, 100, 200);
        DialogMenuItem instruction = new DialogMenuItem("Instrukcja", "Instrukcja", instructionDialogText, 700, 400);
        
        infoMenu.getItems().addAll(info, instruction);
        this.getMenus().add(infoMenu);
    }

    // Inicjuje menu do wybierania kształtu
    private void InitShapeMenu() {
        Menu chooseShape = new Menu("Rysuj");

        MenuItem triangleItem = new MenuItem("Trójkąt");
        MenuItem rectangleItem = new MenuItem("Prostokąt");
        MenuItem circleItem = new MenuItem("Koło");

        class MyShapeChangeHandler implements EventHandler<ActionEvent> {
            private MShapeTypes shape;
            private CanvasPane canvasPane;

            public MyShapeChangeHandler(MShapeTypes chosenShape, CanvasPane cp) {
                shape = chosenShape;
                canvasPane = cp;
            }

            @Override
            public void handle(ActionEvent arg0) {
                canvasPane.setShapeType(shape);
            }

        }

        triangleItem.setOnAction(new MyShapeChangeHandler(MShapeTypes.Triangle, canvasPane));
        rectangleItem.setOnAction(new MyShapeChangeHandler(MShapeTypes.Rectangle, canvasPane));
        circleItem.setOnAction(new MyShapeChangeHandler(MShapeTypes.Circle, canvasPane));

        // TODO Zrobic interfejs zeby Chooseshape? i wtedy cos takiego przyjmowac?
        chooseShape.getItems().addAll(circleItem, rectangleItem, triangleItem);
        this.getMenus().add(chooseShape);
    }
}
