package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        //INPUT
        InputBox inputBox = new InputBox();
    
        //TRIANGLE
        OutputBox outputBox = new OutputBox();
        // outputBox.showTriangle(4);
        
        BorderPane root = new BorderPane();
        //root.setSize(400, 400);
        root.setTop(inputBox);
        root.setCenter(outputBox);

        Scene scene = new Scene(root);
        stage.setMinHeight(200);
        stage.setMinWidth(200);
        stage.setScene(scene);


        stage.setTitle("Trojkat Pascala - 3");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
