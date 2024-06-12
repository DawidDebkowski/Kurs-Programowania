public class StackTest {
  public static void main(String[] args) {
    // Tworzenie dwoch stosow o roznych typach
    Stack<Integer> a = new Stack<Integer>();
    Stack<String> b = new Stack<String>();

    // Wstawianie elementow
    a.push(2);
    a.push(3);
    try {
      // Pobieranie elementow ze stosu
      System.out.println("Wypisywanie elementow stosu Integer:");
      System.out.println(a.pop() + " " + a.pop());
      System.out.println(a.pop() + " " + a.pop());
    } catch (EmptyStack e) {
      System.out.println("PustyStos!");
    }
    // Wstawianie elementow
    b.push("Maciek");
    b.push("Ala");
    try {
      System.out.println("Wypisywanie elementow stosu String:");
      while (!b.empty())
        // Pobieranie elementu ze stosu
        System.out.println(b.pop());
    } catch (EmptyStack e) {
      System.out.println("PustyStos!");
    }

  }
}
