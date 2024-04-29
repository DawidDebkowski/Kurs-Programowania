import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        MainMenu mainMenu = new MainMenu();
        root.setTop(mainMenu);
        
        CanvasPane canvasPane = new CanvasPane();
        root.setCenter(canvasPane);
        
        Scene scene = new Scene(root);
        stage.setMinHeight(200);
        stage.setMinWidth(200);
        stage.setScene(scene);

        stage.setTitle("Paint 2.0");
        stage.show();
    }
}
