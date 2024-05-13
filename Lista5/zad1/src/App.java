import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        CanvasPane canvasPane = new CanvasPane();
        root.setCenter(canvasPane);

        MainMenu mainMenu = new MainMenu(canvasPane);
        root.setTop(mainMenu);

        Scene scene = new Scene(root);
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        stage.setScene(scene);

        stage.setTitle("Paint 2.0");
        stage.show();
    }
}
