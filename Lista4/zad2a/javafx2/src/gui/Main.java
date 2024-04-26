package gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        //INPUT
        InputBox inputBox = new InputBox();
        
        //TRIANGLE
        OutputBox outputBox = new OutputBox();
        // outputBox.showTriangle(4);
        inputBox.setButtonFunction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent arg0) {
                int triangleSize = 0;
                try {
                    triangleSize = inputBox.getTriangleSize();
                    outputBox.showTriangle(triangleSize);
                } catch (NumberFormatException e) {
                    outputBox.showError("Nieprawidlowa liczba");
                }
                stage.sizeToScene();
            }
            
        });
        
        BorderPane root = new BorderPane();
        //root.setSize(400, 400);
        root.setTop(inputBox);
        root.setCenter(outputBox);

        Scene scene = new Scene(root);
        stage.setMinHeight(200);
        stage.setMinWidth(200);
        stage.setScene(scene);
        stage.setTitle("Trojkat Pascala");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}