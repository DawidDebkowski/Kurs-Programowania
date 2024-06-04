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
        new Generator();
        // String[] args = getParameters();

        MGridPane grid = new MGridPane(20,20, 100, 0);
        grid.setPrefSize(400, 400);
        
        Scene scene = new Scene(grid);
        
        stage.setScene(scene);
        stage.show();   
        grid.startThreads();
    }
}
