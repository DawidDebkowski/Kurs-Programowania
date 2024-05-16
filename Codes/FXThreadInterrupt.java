// Based on:
// https://docs.oracle.com/javase/7/docs/technotes/guides/concurrency/threadPrimitiveDeprecation.html


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

// Klasa watku
class MyThread implements Runnable {
    private int number;
    public volatile boolean threadSuspended;
    public MyThread(int number) {
      this.number = number;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Thread" + number + " working");
                if (threadSuspended) {
                    synchronized(this) {
                        while (threadSuspended)
                            wait();
                    }
               }
            } catch (InterruptedException e){}
        }
    }

    public synchronized void Suspend(){
        threadSuspended = !threadSuspended;
        if (!threadSuspended)
            notify();
    }
  }

 
public class FXThreadInterrupt extends Application {

    MyThread myThread1, myThread2, myThread3;
    Thread thread1, thread2, thread3;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        
        Button l1 = new Button("Init");
        Button l2 = new Button("Thread1");
        Button l3 = new Button("Thread2");
        Button l4 = new Button("Thread3");


        l1.setOnAction(new EventHandler<ActionEvent>() {
    
            @Override
            public void handle(ActionEvent event) {
                startingThreads();
            }
        });

        l2.setOnAction(new EventHandler<ActionEvent>() {
    
            @Override
            public void handle(ActionEvent event) {
                myThread1.Suspend();
            }
        });

        l3.setOnAction(new EventHandler<ActionEvent>() {
    
            @Override
            public void handle(ActionEvent event) {
                myThread2.Suspend();
            }
        });
        l4.setOnAction(new EventHandler<ActionEvent>() {
    
            @Override
            public void handle(ActionEvent event) {
                myThread3.Suspend();
            }
        });


        grid.add(l1, 0, 0); 
        grid.add(l2, 1, 0);
        grid.add(l3, 2, 0);
        grid.add(l4, 3, 0);
 


        primaryStage.setScene(new Scene(grid, 300, 250));
        primaryStage.show();
    }

    void startingThreads(){

        myThread1 = new MyThread(1);
        thread1 = new Thread(myThread1);
        thread1.start();

        myThread2 = new MyThread(2);
        thread2 = new Thread(myThread2);
        thread2.start();

        myThread3 = new MyThread(3);
        thread3 = new Thread(myThread3);
        thread3.start();
    }
}