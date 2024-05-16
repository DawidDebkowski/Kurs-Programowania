// Klasa watku
class DisplayMessage implements Runnable {
  private String message;
  public DisplayMessage(String message) {
    this.message = message;
  }
  // Glowna metoda watku
  public void run() {
    // Petla nieskonczona. Watek sie nie konczy  
    while(true) {
      System.out.println(message);
    }
  }
}

public class RunnableDemo {
  public static void main(String [] args) {
    
    // Tworzenie i start watku 1  
    System.out.println("Creating the hello thread...");
    DisplayMessage hello = new DisplayMessage("Hello");
    Thread thread1 = new Thread(hello);
    System.out.println("Starting the hello thread...");
    thread1.start();
    
    // Tworzenie i start watku 2    
    System.out.println("Creating the goodbye thread...");
    DisplayMessage bye = new DisplayMessage("Goodbye");
    Thread thread2 = new Thread(bye);
    System.out.println("Starting the goodbye thread...");
    thread2.start();
    
    // Watki nie sa zsynchronizowane. Wypisywanie wiadomosci Hello i Goodbye 
    // tez nie jest zsynchronizowane
  }
}

