import javafx.application.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MGridPane grid = new MGridPane(5, 10, 0, 0);

        Scene scene = new Scene(grid);

        stage.setScene(scene);
        stage.show();   
    }
}
