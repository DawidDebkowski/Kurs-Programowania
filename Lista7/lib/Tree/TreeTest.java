public class TreeTest 
{
  public static void main(String[] args)
  {
    // Drzewo dla elementow typu String  
    Tree<String> d = new Tree<String>();

    d.insert("Maciek"); d.insert("Ala"); d.insert("Kot"); d.insert("Zbyszek");

    System.out.println("Sprawdzenie czy wystepuje Ala");
    System.out.println(d.isElement("Ala"));

    System.out.println("Sprawdzenie czy wystepuje ma");
    System.out.println(d.isElement("ma"));

    System.out.println("Wypisywanie elementow drzewa");
    System.out.println(d);
  }
}
