//https://jenkov.com/tutorials/javafx/concurrency.html#pageToc

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import java.util.Random;

class MyThread extends Thread{

  ProgressBar progressBar;
  // Progress bar to write the progress (simulation of the access to the other field)
  ProgressBar progressBarToGet;
  int number;
  Random r = new Random();
  double progress = 0;

  public MyThread(ProgressBar progressBar, int number, ProgressBar progressBarToGet){
    this.progressBar = progressBar;
    this.progressBarToGet = progressBarToGet;
    this.number=number;

  }

  private void method(){
    try {
      Thread.sleep(30);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {

    for(int i=0; i<10; i++){

      try {
        int delay = r.nextInt(1000); 
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      progress += 0.1;
      final double reportedProgress = progress;

      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          System.out.println("Start: T: "+ number);
          progressBar.setProgress(reportedProgress);
          // Simulating thread sleep
          method();
          // Progress of the current progress bar
          System.out.println("Progress: T: "+ number +" :"+ progressBar.getProgress());
          // Progress of the progress bar 0 (simulation of the access to the other object)
          System.out.println("Progress: T0: "+  progressBarToGet.getProgress());
          System.out.println("End: T: "+ number);
        }
      });
    }
  }
}


public class FXThreadSimulation extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("JavaFX App");

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(0, 10, 0, 10));


    ProgressBar progressBar = new ProgressBar(0);
    ProgressBar progressBar1 = new ProgressBar(0);
    ProgressBar progressBar2 = new ProgressBar(0);
    ProgressBar progressBar3 = new ProgressBar(0);


    grid.add(progressBar, 1, 0);
    grid.add(progressBar1, 2, 0);
    grid.add(progressBar2, 1, 1);
    grid.add(progressBar3, 2, 1);


    primaryStage.setScene(new Scene(grid, 300, 250));

    primaryStage.show();

    MyThread myT = new MyThread(progressBar, 0, progressBar);
    MyThread myT1 = new MyThread(progressBar1, 1, progressBar);
    MyThread myT2 = new MyThread(progressBar2, 2, progressBar);
    MyThread myT3 = new MyThread(progressBar3, 3, progressBar);

    myT.start();
    myT1.start();
    myT2.start();
    myT3.start();
  }
}