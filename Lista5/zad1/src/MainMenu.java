import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.event.*;
/**
 * MainMenu
 * Klasa odpowiadająca za menu
 */

public class MainMenu extends MenuBar{
    public MainMenu()
    {
        InitShapeMenu();
        InitInfoMenu();
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

        MenuItem circleItem = new MenuItem("Koło");
        MenuItem rectangleItem = new MenuItem("Prostokąt");
        MenuItem triangleItem = new MenuItem("Trójkąt");

        //TODO Zrobic interfejs zeby Chooseshape? i wtedy cos takiego przyjmowac?
        chooseShape.getItems().addAll(circleItem,rectangleItem,triangleItem);
        this.getMenus().add(chooseShape);
    }
}
