import javafx.application.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Głowna klasa uruchamiająca aplikację
 */
public class App extends Application {
    private static int n = 6;
    private static int m = 10;
    private static double k = 100;
    private static double p = 0.005;

    private MGridPane grid;

    /**
     * Funkcja main
     * 
     * @param args lista argumentów: szerokość, wysokość, interwał, szansa na zmianę
     */
    public static void main(String[] args) throws Exception {
        try {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
            k = Double.parseDouble(args[2]);
            p = Double.parseDouble(args[3]);
            if (n <= 0 || m <= 0 || k <= 0 || p <= 0) {
                System.out.println("Argumenty muszą być liczbami dodatnimi");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Dane muszą być liczbami");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Za mało argumentów");
            // return; TODO: ADD RETURN
        }
        launch(args);
    }

    /**
     * Metoda tworząca scenę oraz grid, a następnie uruchamiająca wątki
     */
    @Override
    public void start(Stage stage) throws Exception {
        grid = new MGridPane(n, m, k, p);
        grid.setPrefSize(400, 400);

        Scene scene = new Scene(grid);

        stage.setScene(scene);
        stage.show();
        grid.startThreads();
    }

    /**
     * Zatrzymanie pracy wątków po zamknięciu okna
     */
    @Override
    public void stop() {
        grid.stopThreads();
    }
}
