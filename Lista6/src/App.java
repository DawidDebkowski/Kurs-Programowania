import javafx.application.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //inicjalizacja globalnego generatora
        Generator generator = new Generator();

        MGridPane grid = new MGridPane(1, 10, 1000, 0);
        grid.startThreads();

        Scene scene = new Scene(grid);
        
        stage.setScene(scene);
        stage.show();   
    }
}
