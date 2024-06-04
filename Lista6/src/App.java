import javafx.application.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    private static int n = 20;
    private static int m = 10;
    private static double k = 100;
    private static double p = 0.05;

    private MGridPane grid;

    public static void main(String[] args) throws Exception {
        try {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
            k = Double.parseDouble(args[2]);
            p = Double.parseDouble(args[3]);
            if(n <= 0 || m <= 0 || k <= 0 || p <= 0) {
                System.out.println("Argumenty muszą być liczbami dodatnimi");
                return;
            }
        }
        catch(NumberFormatException e) {
            System.out.println("Dane muszą być liczbami");
            return;
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Za mało argumentów");
            // return; TODO: ADD RETURN
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //inicjalizacja globalnego generatora
        new Generator();
        // String[] args = getParameters();

        grid = new MGridPane(n,m, k, p);
        grid.setPrefSize(400, 400);
        
        Scene scene = new Scene(grid);
        
        stage.setScene(scene);
        stage.show();   
        grid.startThreads();
    }

    @Override
    public void stop() {
        grid.stopThreads();
    }
}
