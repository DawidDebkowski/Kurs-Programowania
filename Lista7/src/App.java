import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        Server<Integer> server = new Server<Integer>(10);
        BinaryTree<Integer> bartoszSlowik = new BinaryTree<Integer>();
        bartoszSlowik.insert(5);
        bartoszSlowik.insert(10);
        bartoszSlowik.insert(20);
        bartoszSlowik.insert(15);
        for (int i = 0; i <= 10; i++) {
            bartoszSlowik.insert(i);
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane content = new BorderPane();
        content.setPrefSize(400, 400);

        HBox buttonBox = new HBox();
        buttonBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        buttonBox.setPadding(new Insets(20));
        buttonBox.setSpacing(10);
        Button searchButton = new Button("Search");
        Button insertButton = new Button("Insert");
        Button deleteButton = new Button("Delete");
        Button drawButton = new Button("Draw");
        TextField inputField = new TextField();
        
        searchButton.setPrefWidth(Integer.MAX_VALUE);
        insertButton.setPrefWidth(Integer.MAX_VALUE);
        deleteButton.setPrefWidth(Integer.MAX_VALUE);
        drawButton.setPrefWidth(Integer.MAX_VALUE);
        inputField.setPrefWidth(Integer.MAX_VALUE);
        buttonBox.setHgrow(inputField, Priority.ALWAYS);

        buttonBox.getChildren().addAll(inputField, searchButton,
                insertButton, deleteButton, drawButton);

        content.setTop(buttonBox);
        
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setTitle("Drzewo Binarne");
        stage.show();
    }

}
