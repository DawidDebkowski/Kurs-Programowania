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

        MGridPane grid = new MGridPane(4,4, 5000, 0.1);
        grid.setPrefSize(400, 400);
        
        Scene scene = new Scene(grid);
        
        stage.setScene(scene);
        stage.show();   
        grid.startThreads();
    }
}
