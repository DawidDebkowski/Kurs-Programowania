// Klasa elementu stosu o dowolnym typie generycznym T
class StackElem<T> {
  final T elem;
  final StackElem<T> next;
  StackElem(T elem, StackElem<T> next)
  {
    this.elem = elem;
    this.next = next;
  }
}
class EmptyStack extends Exception{}

// Klasa obslugujaca typ generyczny T
public class Stack<T> {
  private StackElem<T> top;
  public Stack() { top = null; }
  public boolean empty() { return top==null; }
  
  // Dodawanie elementu do stosu
  public void push(T elem) { top = new StackElem<T>(elem,top); }
  
  // Pobieranie elementu ze stosu
  public T pop() throws EmptyStack
  {
    if( empty() ) throw new EmptyStack();
    T wynik = top.elem;
    top = top.next;
    return wynik;
  }
}
