/**
 * Dowolna klasa implementująca Comparable oraz posiadająca konwersję z napisu na siebie.
 * 
 * <h2>
 * Jak dodać nowy typ drzewa:
 * </h2>
 * <ul>
 * <li> Stworzenie klasy implementującej Comparable.
 * <li> Dodanie zmiennej drzewa na serwer.
 * <li> Dodanie do możliwych typów drzewa. (enum)
 * <li> Dodanie zmiennej drzewa na ServerThread.
 * <li> Przetwarzanie napisu na nasz typ danych. ({@link ServerThread#executeCommand executeCommand} w ServerThread)
 * </ul>
 */
public class MyDataType implements Comparable<MyDataType>{
    int key;
    String name;

    public MyDataType(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int compareTo(MyDataType o) {
        if(key < o.key) {
            return -1;
        } else if(key == o.key) {
            return name.compareTo(o.name);
        }

        return 1;
    }
    
    public String toString() {
        return key + " " + name;
    }

    public static MyDataType parseFromString(String key, String name) throws NumberFormatException{
        return new MyDataType(Integer.parseInt(key), name);
    }
}
