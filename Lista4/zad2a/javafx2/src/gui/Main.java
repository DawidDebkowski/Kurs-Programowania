package gui;
import javafx.application.Application;
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
        int inputMaxWidth = 100;
        int inputMaxHeigth = 40;
        HBox inputBox = new HBox();
        Button mkButton = new Button("Stworz");
        TextArea inputTextArea = new TextArea();
        inputTextArea.setMaxSize(inputMaxWidth, inputMaxHeigth);
        mkButton.setPrefSize(inputMaxWidth, inputMaxHeigth);

        inputBox.setPadding(new Insets(10));
        inputBox.setSpacing(10);
        inputBox.setStyle("-fx-background-color: #336699;");
        inputBox.getChildren().addAll(inputTextArea, mkButton);
        inputBox.setAlignment(Pos.CENTER);


        //TRIANGLE
        OutputBox outputBox = new OutputBox();
        //outputBox.test(4);
        try {

            outputBox.showTriangle(4);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        BorderPane root = new BorderPane();
        root.setPrefSize(400, 400);
        root.setTop(inputBox);
        root.setCenter(outputBox);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Trojkat Pascala");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}